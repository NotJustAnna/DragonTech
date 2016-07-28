package cf.brforgers.mods.DragonScalesEX.common.virus.utils;

import cf.brforgers.core.lib.world.WorldBlockPos;
import cf.brforgers.mods.DragonScalesEX.common.DSEX;
import cf.brforgers.mods.DragonScalesEX.common.virus.DraconyVirus;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class DVUtils {
    private DVUtils() {
    }

    public static boolean convertBlock(WorldBlockPos pos) {
        IBlockState state = pos.getBlockState();
        Block block = state.getBlock();
        if (block == DSEX.DRAGON_GRASS || block == Blocks.DIRT || block == Blocks.GRASS) {
            convertBlock(new WorldBlockPos(pos.getWorld(), pos.up()));
            if (pos.getWorld().getLightFromNeighbors(pos.up()) < 4 && pos.getWorld().getBlockState(pos.up()).getLightOpacity(pos.getWorld(), pos.up()) > 2) {
                pos.setBlockState(DSEX.DRAGON_GRASS.getDefaultState());
            } else {
                pos.setBlockState(DSEX.DRAGON_DIRT.getDefaultState());
            }
            return true;
        }

        if (block == Blocks.SNOW_LAYER) {
            if (DraconyVirus.rand.nextInt(10) == 1) {
                pos.setBlockState(DSEX.DRAGON_CRYSTAL.getDefaultState());
            } else {
                pos.setBlockToAir();
            }
            return true;
        }

        if (block == Blocks.STONE || block == Blocks.MONSTER_EGG) {
            pos.setBlockState(DSEX.DRAGON_STONE.getDefaultState());
            return true;
        }

        //TODO Macumba braba
        if (block == Blocks.LOG || block == Blocks.LOG2) { //Well, fuck
            pos.setBlockState(DSEX.DRACONY_LOG, pos.getWorld().getBlockMetadata(pos));
            return true;
        }

        if (block == Blocks.LEAVES || block == Blocks.LEAVES2) { //Well, also fuck
            pos.setBlockState(DSEX.DRACONY_LEAVES.getDefaultState());
            //.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
            return true;
        }
        //END_TODO

        if (block == Blocks.PLANKS) {
            pos.setBlockState(DSEX.DRACONY_PLANKS.getDefaultState());
            return true;
        }

        return false;
    }

    public static boolean canConvertBlock(IBlockState state) {
        Block block = state.getBlock();
        return (
                block == DSEX.DRAGON_GRASS ||
                        block == Blocks.DIRT ||
                        block == Blocks.GRASS ||
                        block == Blocks.SNOW_LAYER ||
                        block == Blocks.STONE ||
                        block == Blocks.MONSTER_EGG ||
                        block == Blocks.LOG ||
                        block == Blocks.LOG2 ||
                        block == Blocks.LEAVES ||
                        block == Blocks.LEAVES2 ||
                        block == Blocks.PLANKS
        );
    }

    /**
     * Procriates the Virus (Assuming the Block Position being the ones that will infect adjacent ones)<br>
     * Should be primarily used for Random Ticks, but can also be used for wands
     *
     * @param pos
     */
    public static void procriateAt(WorldBlockPos pos) {
        if (pos.getWorld().isRemote) return;

        for (int l = 0; l < 4; ++l) {
            convertBlock(new WorldBlockPos(pos.getWorld(), pos.add(DraconyVirus.rand.nextInt(3) - 1, DraconyVirus.rand.nextInt(5) - 3, DraconyVirus.rand.nextInt(3) - 1)));
        }
    }
}
