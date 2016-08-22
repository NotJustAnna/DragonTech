package cf.brforgers.mods.DragonTech.common.virus.blocks;

import cf.brforgers.mods.DragonTech.common.DT;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class DragonGrass extends BlockVirusBase implements IGrowable {
    public DragonGrass() {
        super(Material.GRASS);
        this.setTickRandomly(true);
        this.setSoundType(SoundType.PLANT);
        this.setHardness(0.6F);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.isRemote) {
            if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockState(pos.up()).getLightOpacity(world, pos.up()) > 2) {
                world.setBlockState(pos, DT.DRAGON_DIRT.getDefaultState());
            } else if (world.getLightFromNeighbors(pos.up()) >= 9) {
                super.updateTick(world, pos, state, rand);
            }
        }
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return DT.DRAGON_DIRT.getItemDropped(DT.DRAGON_DIRT.getDefaultState(), rand, fortune);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        super.updateTick(worldIn, pos, state, rand);
    }
}
