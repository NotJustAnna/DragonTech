package cf.brforgers.mods.DragonTech.common.virus;

import cf.brforgers.api.DragonTech.providers.IDoubleProvider;
import cf.brforgers.api.DragonTech.providers.ITripleProvider;
import cf.brforgers.core.lib.batch.BatchExecutor;
import cf.brforgers.core.lib.batch.ProfiledRunnable;
import cf.brforgers.mods.DragonTech.common.utils.SimpleProviders;
import cf.brforgers.mods.DragonTech.common.virus.utils.DVUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DTVirus extends ProfiledRunnable {
    public static Random rand = null;
    private final BatchExecutor batchExecutor;
    private List<ITripleProvider<World, BlockPos, Integer>> taggedForBaking = new ArrayList<ITripleProvider<World, BlockPos, Integer>>();
    private List<IDoubleProvider<World, BlockPos>> taggedForTransforming = new ArrayList<IDoubleProvider<World, BlockPos>>();

    private DTVirus(BatchExecutor executor, World world, BlockPos pos, int spread) {
        this.tickTimeout = 1;
        batchExecutor = executor;
        taggedForBaking.add(new SimpleProviders.Triple<World, BlockPos, Integer>(world, pos, spread));
    }

    /**
     * Injects a DTVirus Async Generator instance at the BatchExecutor
     *
     * @param executor
     * @param pos
     * @param spread
     */
    public static void createAt(BatchExecutor executor, World world, BlockPos pos, int spread) {
        if (rand == null) rand = new Random(world.getSeed()); //
        executor.addRunnablesToThisTick(new DTVirus(executor, world, pos, spread));
    }

    @Override
    public void run() {
        Long millis = System.currentTimeMillis();
        while (shouldRunNext(millis) && taggedForBaking.size() > 0) {
            ITripleProvider<World, BlockPos, Integer> bakingEntry = taggedForBaking.get(0);
            int spread = bakingEntry.provideC();

            if (rand.nextInt(10) < spread - 1) {
                spread--;
                World world = bakingEntry.provideA();
                BlockPos pos = bakingEntry.provideB();
                taggedForTransforming.add(new SimpleProviders.Double<World, BlockPos>(world, pos));
                IBlockState state = world.getBlockState(pos);

                for (int l = 0; l < 4; ++l) {
                    final int x = pos.getX() + rand.nextInt(3) - 1;
                    final int y = pos.getY() + rand.nextInt(5) - 3;
                    final int z = pos.getZ() + rand.nextInt(3) - 1;
                    if (DVUtils.canConvertBlock(world.getBlockState(pos))) {
                        taggedForBaking.add(new SimpleProviders.Triple<World, BlockPos, Integer>(world, new BlockPos(x, y, z), spread));
                    }
                }
            }

            taggedForBaking.remove(0);
        }

        while (shouldRunNext(millis) && taggedForTransforming.size() > 0) {
            IDoubleProvider<World, BlockPos> p = taggedForTransforming.get(0);
            DVUtils.convertBlock(p.provideA(), p.provideB());
            taggedForTransforming.remove(0);
        }

        if (taggedForBaking.size() > 0 || taggedForTransforming.size() > 0) batchExecutor.addRunnablesToThisTick(this);
    }
}
