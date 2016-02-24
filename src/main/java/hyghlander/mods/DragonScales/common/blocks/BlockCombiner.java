package hyghlander.mods.DragonScales.common.blocks;

import hyghlander.mods.DragonScales.common.blocks.tile.TileCombiner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCombiner extends BlockContainer {

	protected BlockCombiner(Material p_i45394_1_) {
		super(p_i45394_1_);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack){
		int dir = MathHelper.floor_double((double)((player.rotationYaw * 4F)/ 360F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x,y,z,dir,0);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileCombiner();
	}
}
