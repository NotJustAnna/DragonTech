package cf.brforgers.mods.DragonTech.common.world.blocks;

import cf.brforgers.mods.DragonTech.common.DT;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockDragonCrystal extends Block {
    public static final Material CRYSTAL = new Material(MapColor.LAPIS).setTranslucent().setRequiresTool().setNoPushMobility();
    public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);
    private Random rand = new Random();

    public BlockDragonCrystal() {
        super(CRYSTAL);
        this.setHardness(4.0F);
    }

    public static int getRotationMeta(double rotationYaw) {
        return MathHelper.floor_double((double) ((rotationYaw * 4F) / 360F) + 0.5D);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer.Builder(this).add(ROTATION).build();
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
    }

    @Override
    public int quantityDropped(Random rand) {
        return MathHelper.getRandomIntegerInRange(rand, 1, 4);
    }

    @Nullable
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return DT.DRAGON_ESSENCE_SHARD;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(ROTATION, getRotationMeta(placer.rotationYaw)));
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        return MathHelper.getRandomIntegerInRange(rand, 3, 7);
    }

    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ROTATION, meta);
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(ROTATION);
    }
}
