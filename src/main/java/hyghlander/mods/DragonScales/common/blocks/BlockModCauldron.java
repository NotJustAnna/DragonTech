package hyghlander.mods.DragonScales.common.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

public class BlockModCauldron extends BlockContainer {
	@SideOnly(Side.CLIENT)
    private IIcon innerIcon;
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon bottomIcon;

    public BlockModCauldron()
    {
        super(Material.iron);
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
	
    public boolean renderBlockCauldron(BlockModCauldron p_147785_1_, RenderBlocks renderer, int x, int y, int z)
    {
    	renderer.renderStandardBlock(p_147785_1_, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(p_147785_1_.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        int l = p_147785_1_.colorMultiplier(renderer.blockAccess, x, y, z);
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;
        float f4;

        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        tessellator.setColorOpaque_F(f, f1, f2);
        IIcon iicon1 = p_147785_1_.getBlockTextureFromSide(2);
        f4 = 0.125F;
        renderer.renderFaceXPos(p_147785_1_, (double)((float)x - 1.0F + f4), (double)y, (double)z, iicon1);
        renderer.renderFaceXNeg(p_147785_1_, (double)((float)x + 1.0F - f4), (double)y, (double)z, iicon1);
        renderer.renderFaceZPos(p_147785_1_, (double)x, (double)y, (double)((float)z - 1.0F + f4), iicon1);
        renderer.renderFaceZNeg(p_147785_1_, (double)x, (double)y, (double)((float)z + 1.0F - f4), iicon1);
        IIcon iicon2 = BlockCauldron.getCauldronIcon("inner");
        renderer.renderFaceYPos(p_147785_1_, (double)x, (double)((float)y - 1.0F + 0.25F), (double)z, iicon2);
        renderer.renderFaceYNeg(p_147785_1_, (double)x, (double)((float)y + 1.0F - 0.75F), (double)z, iicon2);
        int i1 = renderer.blockAccess.getBlockMetadata(x, y, z);

        if (i1 > 0)
        {
            IIcon iicon = BlockLiquid.getLiquidIcon("water_still");
            renderer.renderFaceYPos(p_147785_1_, (double)x, (double)((float)y - 1.0F + BlockModCauldron.getRenderLiquidLevel(i1)), (double)z, iicon);
        }

        return true;
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityModCauldron();
	}
}
