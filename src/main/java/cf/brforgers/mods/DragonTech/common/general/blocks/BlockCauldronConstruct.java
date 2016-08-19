package cf.brforgers.mods.DragonTech.common.general.blocks;

import cf.brforgers.mods.DragonTech.common.general.blocks.tile.TileCauldronConstruct;
import cf.brforgers.mods.DragonTech.common.world.blocks.BlockDragonCrystal;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCauldronConstruct extends BlockContainer {
	public static final PropertyInteger ROTATION = BlockDragonCrystal.ROTATION;
	public BlockCauldronConstruct() {
		super(Material.IRON);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(ROTATION, BlockDragonCrystal.getRotationMeta(placer.rotationYaw)));
	}

	@Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileCauldronConstruct();
	}
}
