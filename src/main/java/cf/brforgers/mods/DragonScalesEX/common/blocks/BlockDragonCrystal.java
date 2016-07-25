package cf.brforgers.mods.DragonScalesEX.common.blocks;

import cf.brforgers.mods.DragonScalesEX.common.DSEX;
import cf.brforgers.mods.DragonScalesEX.common.blocks.tile.TileCrystal;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDragonCrystal extends BlockContainer {

    private Random rand = new Random();

	public BlockDragonCrystal() {
        super(Material.ROCK);
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

    public Item getItemDropped(int ignored1, Random ignored2, int ignored3)
    {
        return DSEX.DRAGON_ESSENCE_SHARD;
    }

    public int quantityDropped(Random rand)
    {
        return MathHelper.getRandomIntegerInRange(rand, 1, 4);
    }
    
    @Override
    public int getExpDrop(IBlockAccess ignored1, int ignored2, int ignored3)
    {
    	return MathHelper.getRandomIntegerInRange(rand, 3, 7);
    }
}
