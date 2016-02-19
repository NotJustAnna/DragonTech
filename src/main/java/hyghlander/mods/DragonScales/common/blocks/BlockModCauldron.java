package hyghlander.mods.DragonScales.common.blocks;

import hyghlander.mods.DragonScales.common.DragonScalesHandler;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockModCauldron extends BlockCauldron {
	public BlockModCauldron()
	{
		super();
		this.setHardness(2.0F).setBlockName("essentiaCauldron").setBlockTextureName("cauldron");
	}
	/**
	 * Overrides to default function because this Cauldron is Different
	 */
	public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {}
	
	/**
	 * Overrides to default function because this Cauldron is Different
	 */
	public void fillWithRain(World p_149639_1_, int p_149639_2_, int p_149639_3_, int p_149639_4_) {}
	
	/**
	 * Overrides for Dragon Essentia
	 */
	public boolean onBlockActivated(World theWorld, int  x, int y, int z, EntityPlayer player, int ignored1, float ignored2, float ignored3, float ignored4)
    {
		//Fix the Cauldron if it is with no Water
		int thisBlockMeta = theWorld.getBlockMetadata(x, y, z);
		if (thisBlockMeta == 0)
		{
			theWorld.setBlock(x, y, z, Blocks.cauldron, 0, 3);
			this.func_150024_a(theWorld, x, y, z, 0);
			return false;
		}
		
        if (theWorld.isRemote)
        {
            return true;
        }
        else
        {
            ItemStack itemstack = player.inventory.getCurrentItem();

            if (itemstack == null)
            {
                return true;
            }
            else
            {
                int essentiaLevel = func_150027_b(thisBlockMeta);

                if (itemstack.getItem() == Items.leather)
                {
                    if (essentiaLevel == 3)
                    {
                    	player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(DragonScalesHandler.dragonScale));
                        this.func_150024_a(theWorld, x, y, z, 0);
                    }

                    return true;
                }
                else
                {
                    if ((itemstack.getItem() == Items.glass_bottle) && (essentiaLevel > 0))
                    {
                        if (!player.capabilities.isCreativeMode)
                        {
                            ItemStack itemstack1 = new ItemStack(Items.potionitem, 1, 0);

                            if (!player.inventory.addItemStackToInventory(itemstack1))
                            {
                                theWorld.spawnEntityInWorld(new EntityItem(theWorld, (double)x + 0.5D, (double)y + 1.5D, (double)z + 0.5D, itemstack1));
                            }
                            else if (player instanceof EntityPlayerMP)
                            {
                                ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
                            }

                            --itemstack.stackSize;

                            if (itemstack.stackSize <= 0)
                            {
                                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                            }
                            
                            this.func_150024_a(theWorld, x, y, z, essentiaLevel - 1);
                        }
                    }
                    return false;
                }
            }
        }
    }
	
	public void func_150024_a(World theWorld, int x, int y, int z, int meta)
    {
		if (meta == 0)
		{
			theWorld.setBlock(x, y, z, Blocks.cauldron, 0, 2);
		}
		else
		{
			theWorld.setBlockMetadataWithNotify(x, y, z, MathHelper.clamp_int(meta, 0, 3), 2);
        	theWorld.func_147453_f(x, y, z, this);
		}
    }
}
