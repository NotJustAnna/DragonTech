package cf.brforgers.mods.DragonScalesEX.common;

import cf.brforgers.api.DragonScalesEX.DragonScalesAPI;
import cf.brforgers.api.DragonScalesEX.DragonScalesAPI.CauldronRecipe;
import cf.brforgers.core.lib.utils.DoubleReturn;
import cf.brforgers.mods.DragonScalesEX.DragonScalesEX;
import cf.brforgers.mods.DragonScalesEX.common.blocks.BlockModCauldron;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.RegistryDefaulted;
import net.minecraft.world.World;

public class CauldronAPIHandler {
    public static void processDispensingBehaviour() {
        DragonScalesEX.logger.info("Reflecting Dispenser...");
        RegistryDefaulted dispenserRegistry = (RegistryDefaulted) BlockDispenser.dispenseBehaviorRegistry;
        final IBehaviorDispenseItem originalBehaviour = (IBehaviorDispenseItem) ObfuscationReflectionHelper.getPrivateValue(RegistryDefaulted.class, dispenserRegistry, "defaultObject");
        DragonScalesEX.logger.info("Overwriting Dispenser Default Behaviour...");
        ObfuscationReflectionHelper.setPrivateValue(RegistryDefaulted.class, dispenserRegistry, new IBehaviorDispenseItem() {
            @Override
            public ItemStack dispense(IBlockSource blockSource, ItemStack stack) {
                    DoubleReturn<ItemStack, Boolean> returned = performDispenserInteraction(blockSource, stack);
                if (!returned.object2) return originalBehaviour.dispense(blockSource, stack);
                    return returned.object1;
                }
        }, "defaultObject");
        DragonScalesEX.logger.info("Done");
    }

    public static boolean performCauldronInteraction(Block block, World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
		//Fix the Cauldron if it is with no Water
		int thisBlockMeta = world.getBlockMetadata(x, y, z);
		if (thisBlockMeta == 0)
		{
			world.setBlock(x, y, z, Blocks.cauldron, 0, 3);
			BlockModCauldron.setMetadataProperly(world, x, y, z, 0, block);
			return Blocks.cauldron.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
		}
		
		if (world.getBlock(x, y+1, z).equals(DragonScalesHandler.cauldronConstruct))
			return true;
		
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            ItemStack stack = player.inventory.getCurrentItem();

            if (stack != null)
            {
            	if(stack.getItem().equals(DragonScalesHandler.dragonEssenceBottle)) {
            		return false;
                } else {
                	tryPerformCauldronRecipe(block, world, x, y, z, thisBlockMeta, player, stack);
                }
            }
            else {
            	
            }
        }
        
        return true;
    }
	
	public static void tryPerformCauldronRecipe(Block block, World world, int x, int y, int z, int meta, EntityPlayer player, ItemStack stack)
	{
		int essentiaLevel = BlockModCauldron.func_150027_b(meta);
        
        CauldronRecipe recipe = DragonScalesAPI.getValidRecipe(stack, essentiaLevel, world, x, y, z, player);
        
        if (recipe != null)
        {
        	ItemStack out = recipe.getOutput(stack, essentiaLevel, world, x, y, z, player);
        	if (!player.inventory.addItemStackToInventory(out))
            {
                if (out != null)
                    world.spawnEntityInWorld(new EntityItem(world, (double) x + 0.5D, (double) y + 1.5D, (double) z + 0.5D, recipe.getOutput(stack, essentiaLevel, world, x, y, z, player)));
            }
            else if (player instanceof EntityPlayerMP)
            {
                ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
            }

            stack.stackSize -= recipe.getItemCost(stack, essentiaLevel, world, x, y, z, player);

            if (stack.stackSize <= 0)
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);

            BlockModCauldron.setMetadataProperly(world, x, y, z, essentiaLevel - recipe.getEssentiaCost(stack, essentiaLevel, world, x, y, z, player), block);
        }
	}

    public static DoubleReturn<ItemStack, Boolean> performDispenserInteraction(IBlockSource blockSource, ItemStack stack) {
        EnumFacing facing = BlockDispenser.func_149937_b(blockSource.getBlockMetadata()); //Get Facing
        int x = blockSource.getXInt() + facing.getFrontOffsetX(); //Get Cauldron Coords
        int y = blockSource.getYInt() + facing.getFrontOffsetY();
        int z = blockSource.getZInt() + facing.getFrontOffsetZ();
        World world = blockSource.getWorld();

        if (world.getBlock(x, y, z) != DragonScalesHandler.modCauldron) {
            return new DoubleReturn<ItemStack, Boolean>(stack, false);
        }

        //Fix the Cauldron if it is with no Water
        int thisBlockMeta = world.getBlockMetadata(x, y, z);
        if (thisBlockMeta == 0) {
            world.setBlock(x, y, z, Blocks.cauldron, 0, 3);
            BlockModCauldron.setMetadataProperly(world, x, y, z, 0, world.getBlock(x, y, z));
            return new DoubleReturn<ItemStack, Boolean>(stack, false);
        }
        if (world.getBlock(x, y + 1, z).equals(DragonScalesHandler.cauldronConstruct))
            return new DoubleReturn<ItemStack, Boolean>(stack, false);
        if (world.isRemote) {
            return new DoubleReturn<ItemStack, Boolean>(stack, false);
        } else if (stack != null) {
            if (stack.getItem().equals(DragonScalesHandler.dragonEssenceBottle)) {
                return new DoubleReturn<ItemStack, Boolean>(stack, false);
            } else {
                int essentiaLevel = BlockModCauldron.func_150027_b(thisBlockMeta);
                CauldronRecipe recipe = DragonScalesAPI.getValidRecipe(stack, essentiaLevel, world, x, y, z, null);
                if (recipe != null) {
                    ItemStack out = recipe.getOutput(stack, essentiaLevel, world, x, y, z, null);

                    stack.stackSize = Math.max(0, stack.stackSize - recipe.getItemCost(stack, essentiaLevel, world, x, y, z, null));

                    BlockModCauldron.setMetadataProperly(world, x, y, z, essentiaLevel - recipe.getEssentiaCost(stack, essentiaLevel, world, x, y, z, null), world.getBlock(x, y, z));

                    if (out != null && out.stackSize > 0) {
                        TileEntityDispenser tileDispenser = (TileEntityDispenser) blockSource.getBlockTileEntity();

                        for (int i = 0; i <= 9; i++) {
                            if (tileDispenser.getStackInSlot(i) == null) {
                                tileDispenser.setInventorySlotContents(i, out);
                                return new DoubleReturn<ItemStack, Boolean>(stack, true);
                            }
                        }

                        float mm = 0.3F;
                        EntityItem item = new EntityItem(world, x, y + 1, z, out);
                        item.motionX = mm * ((((float) world.rand.nextInt(100)) / 100F) - 0.5F);
                        item.motionY = mm * ((((float) world.rand.nextInt(100)) / 100F) - 0.5F);
                        item.motionZ = mm * ((((float) world.rand.nextInt(100)) / 100F) - 0.5F);
                        world.spawnEntityInWorld(item);
                    }
                    DragonScalesEX.logger.info("Success");
                    return new DoubleReturn<ItemStack, Boolean>(stack, true);
                }
            }
        }
        return new DoubleReturn<ItemStack, Boolean>(stack, false);
    }
}
