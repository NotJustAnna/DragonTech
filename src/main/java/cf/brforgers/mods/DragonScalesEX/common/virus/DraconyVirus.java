package cf.brforgers.mods.DragonScalesEX.common.virus;

import cf.brforgers.core.lib.batch.TickBatchExecutor;
import cf.brforgers.core.lib.utils.DoubleReturn;
import cf.brforgers.core.lib.world.WorldBlockPos;
import cf.brforgers.mods.DragonScalesEX.common.virus.utils.DVUtils;
import net.minecraft.block.state.IBlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DraconyVirus implements Runnable {
    public static Random rand = new Random();
    public static TickBatchExecutor.Server batchExecutor = new TickBatchExecutor.Server();
    public List<DoubleReturn<WorldBlockPos, Integer>> taggedForBaking = new ArrayList<DoubleReturn<WorldBlockPos, Integer>>();
    public List<WorldBlockPos> taggedForTransforming = new ArrayList<WorldBlockPos>();

    @Override
    public void run() {
        while (canRun(taggedForBaking)) {
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
                    if (DVUtils.CanConvertBlock(pos.getBlockState().getBlock())) {
                        taggedForBaking.add(new DoubleReturn<WorldBlockPos, Integer>(new WorldBlockPos(pos.getWorld(), x, y, z), spread));
                    }
                }
            }

            taggedForBaking.remove(0);
        }

        while (canRun(taggedForTransforming)) {
            WorldBlockPos pos = taggedForTransforming.get(0);
            DVUtils.ConvertBlock(pos);
            taggedForBaking.remove(0);
        }
    }

    private boolean canRun(List list) {
        return true;
    }
}
