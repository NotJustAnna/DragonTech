package cf.brforgers.mods.DragonScalesEX.common.virus;

import cf.brforgers.core.lib.batch.BatchExecutor;
import cf.brforgers.core.lib.batch.ProfiledRunnable;
import cf.brforgers.core.lib.utils.DoubleReturn;
import cf.brforgers.core.lib.world.WorldBlockPos;
import cf.brforgers.mods.DragonScalesEX.common.virus.utils.DVUtils;
import net.minecraft.block.state.IBlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DraconyVirus extends ProfiledRunnable {
    private static Random rand = null;
    private final BatchExecutor batchExecutor;
    private List<DoubleReturn<WorldBlockPos, Integer>> taggedForBaking = new ArrayList<DoubleReturn<WorldBlockPos, Integer>>();
    private List<WorldBlockPos> taggedForTransforming = new ArrayList<WorldBlockPos>();

    private DraconyVirus(BatchExecutor executor, WorldBlockPos pos, int spread) {
        this.tickTimeout = 1;
        batchExecutor = executor;
        taggedForBaking.add(new DoubleReturn<WorldBlockPos, Integer>(new WorldBlockPos(pos.getWorld(), pos), spread));
    }

    /**
     * Injects a DraconyVirus Async Generator instance at the BatchExecutor
     *
     * @param executor
     * @param pos
     * @param spread
     */
    public static void createAt(BatchExecutor executor, WorldBlockPos pos, int spread) {
        if (rand == null) rand = new Random(pos.getWorld().getSeed()); //
        executor.addRunnablesToThisTick(new DraconyVirus(executor, pos, spread));
    }

    @Override
    public void run() {
        Long millis = System.currentTimeMillis();
        while (shouldRunNext(millis) && taggedForBaking.size()>0) {
            DoubleReturn<WorldBlockPos, Integer> bakingEntry = taggedForBaking.get(0);
            int spread = bakingEntry.object2;

            if (rand.nextInt(10) < spread - 1) {
                spread--;
                WorldBlockPos pos = bakingEntry.object1;
                taggedForTransforming.add(pos);
                IBlockState state = pos.getBlockState();

                for (int l = 0; l < 4; ++l) {
                    final int x = pos.getX() + rand.nextInt(3) - 1;
                    final int y = pos.getY() + rand.nextInt(5) - 3;
                    final int z = pos.getZ() + rand.nextInt(3) - 1;
                    if (DVUtils.canConvertBlock(pos.getBlockState().getBlock())) {
                        taggedForBaking.add(new DoubleReturn<WorldBlockPos, Integer>(new WorldBlockPos(pos.getWorld(), x, y, z), spread));
                    }
                }
            }

            taggedForBaking.remove(0);
        }

        while (shouldRunNext(millis) && taggedForTransforming.size()>0) {
            WorldBlockPos pos = taggedForTransforming.get(0);
            DVUtils.convertBlock(pos);
            taggedForTransforming.remove(0);
        }

        if (taggedForBaking.size() > 0 || taggedForTransforming.size() > 0) batchExecutor.addRunnablesToThisTick(this);
    }
}
