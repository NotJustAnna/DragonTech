package cf.adriantodt.mods.DragonScales.common;

import java.util.ArrayList;
import java.util.List;

import cf.adriantodt.mods.DragonScales.api.DragonScalesAPI;
import cf.adriantodt.mods.DragonScales.api.DragonScalesAPI.CauldronRecipe;
import cf.adriantodt.mods.DragonScales.common.blocks.BlockModCauldron;
import cf.adriantodt.mods.DragonScales.common.items.ModItemEssenceBottle;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import scala.reflect.internal.Trees.ThisSubstituter;

public class CauldronAPIHandler {
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
        
        CauldronRecipe recipe = DragonScalesAPI.getValidRecipe(stack, essentiaLevel);
        
        if (recipe != null)
        {
        	if (!player.inventory.addItemStackToInventory(recipe.getOutput(stack, essentiaLevel)))
            {
                world.spawnEntityInWorld(new EntityItem(world, (double)x + 0.5D, (double)y + 1.5D, (double)z + 0.5D, recipe.getOutput(stack, essentiaLevel)));
            }
            else if (player instanceof EntityPlayerMP)
            {
                ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
            }
        	
        	stack.stackSize -= recipe.getItemCost(stack, essentiaLevel);

            if (stack.stackSize <= 0)
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            
            BlockModCauldron.setMetadataProperly(world, x, y, z, essentiaLevel - recipe.getEssentiaCost(stack, essentiaLevel), block);
        }
	}
}
