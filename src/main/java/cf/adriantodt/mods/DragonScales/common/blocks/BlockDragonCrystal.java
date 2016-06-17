package cf.adriantodt.mods.DragonScales.common.blocks;

import java.util.Random;

import cf.adriantodt.mods.DragonScales.DragonScales;
import cf.adriantodt.mods.DragonScales.Lib;
import cf.adriantodt.mods.DragonScales.common.DragonScalesHandler;
import cf.adriantodt.mods.DragonScales.common.blocks.tile.TileCrystal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDragonCrystal extends BlockContainer {

	public BlockDragonCrystal() {
		super(Material.rock);
		this.setHardness(4.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World ignored1, int ignored2) {
		return new TileCrystal();
	}
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack){
		int dir = MathHelper.floor_double((double)((player.rotationYaw * 4F)/ 360F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x,y,z,dir,0);
	}
	
	@Override
	public int getRenderType(){
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
		//renders as normal block (false otherwise error)
	}
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	// gets the icon of the block
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register){
		this.blockIcon = register.registerIcon(Lib.TEXTURE_PATH + (this.getUnlocalizedName().substring(5)));
		this.setBlockTextureName(Lib.TEXTURE_PATH +":dragonscaleentity");
	}
	
	public Item getItemDropped(int ignored1, Random ignored2, int ignored3)
    {
        return DragonScalesHandler.dragonEssenceShard;
    }

    public int quantityDropped(Random rand)
    {
        return MathHelper.getRandomIntegerInRange(rand, 1, 4);
    }

    private Random rand = new Random();
    
    @Override
    public int getExpDrop(IBlockAccess ignored1, int ignored2, int ignored3)
    {
    	return MathHelper.getRandomIntegerInRange(rand, 3, 7);
    }
}
