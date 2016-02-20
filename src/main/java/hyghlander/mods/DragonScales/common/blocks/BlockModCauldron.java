package hyghlander.mods.DragonScales.common.blocks;

import java.util.List;
import java.util.Random;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import brazillianforgers.lib.ItemHelper;
import brazillianforgers.lib.MathUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hyghlander.mods.DragonScales.DragonScales;
import hyghlander.mods.DragonScales.common.DragonScalesHandler;
import hyghlander.mods.DragonScales.common.blocks.tile.TileEntityModCauldron;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockModCauldron extends Block/*Cauldron*//*Container*/ {
	@SideOnly(Side.CLIENT)
    private IIcon innerIcon;
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon bottomIcon;

    public BlockModCauldron()
    {
        super(Material.iron);
    	//super();
        this.setHardness(2.0F).setBlockName("essentiaCauldron").setBlockTextureName("cauldron");
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.topIcon : (side == 0 ? this.bottomIcon : this.blockIcon);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.innerIcon = iconRegister.registerIcon(this.getTextureName() + "_" + "inner");
        this.topIcon = iconRegister.registerIcon(this.getTextureName() + "_top");
        this.bottomIcon = iconRegister.registerIcon(this.getTextureName() + "_" + "bottom");
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World theWorld, int x, int y, int z, AxisAlignedBB mask, List list, Entity collidingEntity)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
        super.addCollisionBoxesToList(theWorld, x, y, z, mask, list, collidingEntity);
        float f = 0.125F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        super.addCollisionBoxesToList(theWorld, x, y, z, mask, list, collidingEntity);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        super.addCollisionBoxesToList(theWorld, x, y, z, mask, list, collidingEntity);
        this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(theWorld, x, y, z, mask, list, collidingEntity);
        this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(theWorld, x, y, z, mask, list, collidingEntity);
        this.setBlockBoundsForItemRender();
    }

    @SideOnly(Side.CLIENT)
    public static IIcon getCauldronIcon(String iconName)
    {
        return iconName.equals("inner") ? ((BlockModCauldron) DragonScalesHandler.essentiaCauldron).innerIcon : (iconName.equals("bottom") ? ((BlockModCauldron) DragonScalesHandler.essentiaCauldron).bottomIcon : null);
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Items.cauldron;
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Items.cauldron;
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
    {
        int i1 = p_149736_1_.getBlockMetadata(p_149736_2_, p_149736_3_, p_149736_4_);
        return func_150027_b(i1);
    }

    public static int func_150027_b(int p_150027_0_)
    {
        return p_150027_0_;
    }

    @SideOnly(Side.CLIENT)
    public static float getRenderLiquidLevel(int p_150025_0_)
    {
        int j = MathHelper.clamp_int(p_150025_0_, 0, 3);
        return (float)(6 + 3 * j) / 16.0F;
    }
    
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
			this.setMetadataProperly(theWorld, x, y, z, 0);
			return false;
		}
		
        if (theWorld.isRemote)
        {
            return true;
        }
        else
        {
            ItemStack stack = player.inventory.getCurrentItem();

            if (stack == null)
            {
                return true;
            }
            else
            {
                int essentiaLevel = func_150027_b(thisBlockMeta);
                ItemStack result = null;
                int itemsToRemove = 0, essentiaToRemove = 0;

                if (stack.getItem() == Items.leather && essentiaLevel == 3) //Create a Dragon Scale
                {
                    result = new ItemStack(DragonScalesHandler.dragonScale);
                    itemsToRemove = 1;
                    essentiaToRemove = 3;
                }
                else if (stack.getItem() == ItemHelper.toItem(Blocks.brick_block) && essentiaLevel >= (int)((float)(stack.stackSize / 64) * 3)) //Create Dragon Bricks from the Stack
                {
                    result = new ItemStack(DragonScalesHandler.dragonBricks, stack.stackSize);
                    itemsToRemove = stack.stackSize;
                    essentiaToRemove = MathUtils.clamp((int)((float)(stack.stackSize / 64) * 3), 1, 3);
                }
                else if (stack.getItem() == Items.gold_ingot && essentiaLevel == 3) //Create a Dragon Ingot
                {
                    result = new ItemStack(DragonScalesHandler.dragonMetal);
                    itemsToRemove = 1;
                    essentiaToRemove = 3;
                }
                else if (stack.getItem() == Items.stick && essentiaLevel > 0) //Create a Infused Stick
                {
                    result = new ItemStack(DragonScalesHandler.infusedStick, stack.stackSize);
                    itemsToRemove = stack.stackSize;
                    essentiaToRemove = 0;
                }
                else if (stack.getItem() == Items.emerald && essentiaLevel == 3) //Create a Dragon Crystal
                {
                    result = new ItemStack(DragonScalesHandler.dragonCrystal);
                    itemsToRemove = 1;
                    essentiaToRemove = 3;
                }
                
                if (result != null)
                {
                    if (!player.inventory.addItemStackToInventory(result))
                    {
                        theWorld.spawnEntityInWorld(new EntityItem(theWorld, (double)x + 0.5D, (double)y + 1.5D, (double)z + 0.5D, result));
                    }
                    else if (player instanceof EntityPlayerMP)
                    {
                        ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
                    }

                    stack.stackSize -= itemsToRemove;

                    if (stack.stackSize <= 0)
                    {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                    }
                    
                    this.setMetadataProperly(theWorld, x, y, z, essentiaLevel - essentiaToRemove);
                }
                return false;
            }
        }
    }

	
	public void setMetadataProperly(World theWorld, int x, int y, int z, int meta)
    {
		if (meta < 1)
		{
			theWorld.setBlock(x, y, z, Blocks.cauldron, 0, 2);
		}
		else
		{
			theWorld.setBlockMetadataWithNotify(x, y, z, MathHelper.clamp_int(meta, 0, 3), 2);
        	theWorld.func_147453_f(x, y, z, this);
		}
    }
	
	public int getRenderType()
	{
		return DragonScales.proxy.getRenderType("modCauldron");
	}
}
