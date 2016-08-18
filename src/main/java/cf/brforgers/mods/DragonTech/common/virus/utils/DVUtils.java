package cf.brforgers.mods.DragonTech.common.virus.utils;

import cf.brforgers.mods.DragonTech.common.DT;
import cf.brforgers.mods.DragonTech.common.virus.DTVirus;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DVUtils {
    private DVUtils() {
    }

    public static boolean convertBlock(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (block == DT.DRAGON_GRASS || block == Blocks.DIRT || block == Blocks.GRASS) {
            convertBlock(world, pos.up());
            if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockState(pos.up()).getLightOpacity(world, pos.up()) > 2) {
                world.setBlockState(pos, DT.DRAGON_GRASS.getDefaultState());
            } else {
                world.setBlockState(pos, DT.DRAGON_DIRT.getDefaultState());
            }
            return true;
        }

        if (block == Blocks.SNOW_LAYER) {
            if (DTVirus.rand.nextInt(10) == 1) {
                world.setBlockState(pos, DT.DRAGON_CRYSTAL.getDefaultState());
            } else {
                world.setBlockToAir(pos);
            }
            return true;
        }

        if (block == Blocks.STONE || block == Blocks.MONSTER_EGG) {
            world.setBlockState(pos, DT.DRAGON_STONE.getDefaultState());
            return true;
        }

        if (block == Blocks.LOG || block == Blocks.LOG2) { //Well, fuck
            world.setBlockState(pos, DT.DRACONY_LOG.getDefaultState());
            return true;
        }

        if (block == Blocks.LEAVES || block == Blocks.LEAVES2) { //Well, also fuck
            world.setBlockState(pos, DT.DRACONY_LEAVES.getDefaultState());
            //.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
            return true;
        }

        if (block == Blocks.PLANKS) {
            world.setBlockState(pos, DT.DRACONY_PLANKS.getDefaultState());
            return true;
        }

        return false;
    }

    public static boolean canConvertBlock(IBlockState state) {
        Block block = state.getBlock();
        return (
                block == DT.DRAGON_GRASS ||
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
    public static void procriateAt(World world, BlockPos pos) {
        if (world.isRemote) return;

        for (int l = 0; l < 4; ++l) {
            convertBlock(world, new BlockPos(pos.add(DTVirus.rand.nextInt(3) - 1, DTVirus.rand.nextInt(5) - 3, DTVirus.rand.nextInt(3) - 1)));
        }
    }
}
