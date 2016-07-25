package cf.brforgers.mods.DragonScalesEX.common.general;

import cf.brforgers.api.DragonScalesEX.DragonScalesOldAPI;
import cf.brforgers.api.DragonScalesEX.DragonScalesOldAPI.CauldronRecipe;
import cf.brforgers.core.lib.world.WorldBlockPos;
import cf.brforgers.mods.DragonScalesEX.common.DSEX;
import cf.brforgers.mods.DragonScalesEX.common.DSEXManager;
import cf.brforgers.mods.DragonScalesEX.common.general.blocks.BlockModCauldron;
import cf.brforgers.mods.DragonScalesEX.common.utils.BlockActivationParams;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CauldronAPIHandler {
    public static boolean performCauldronInteraction(BlockActivationParams activation)
    {
        if (activation.world.isRemote) {
            return true;
        } //Check if World is Local

        WorldBlockPos worldPos = new WorldBlockPos(activation.world, activation.position); //Convert the World+BlockPos to something useful
        IBlockState state = worldPos.getBlockState(); //Also get the State of what we're clicking

        if (!state.getBlock().equals(DSEX.CAULDRON)) { //Something is trolling us
            try { //Let's forge a Exception to get an StackTrace
                throw new IllegalStateException("Block isn't a DSEX Cauldron");
            } catch (Exception e) {
                DSEX.LOGGER.warn("Forged attempt with Cauldron Interaction detected. REPORT THIS TO THE MODDER.", e);
            }

            return false;
        }

        //Fix the Cauldron if it is with no Water
        if (state.getValue(BlockModCauldron.LEVEL) == 0) {
            DSEX.LOGGER.warn("DSEX Cauldron is empty. This should be nothing to worry, but this is being warned if weird behaviour happens. Redirecting Call to Vanilla Cauldron");

            worldPos.setBlockState(Blocks.CAULDRON.getDefaultState());
            return activation.activate(Blocks.CAULDRON);
        }

        if (worldPos.getWorld().getBlockState(worldPos.up()).getBlock().equals(DSEX.CAULDRON_CONSTRUCT)) {
            return true;
        }

        if (activation.player != null && !activation.player.canPlayerEdit(activation.position, activation.side, activation.heldItem)) {
            return false;
        }

        ItemStack stack = activation.heldItem;
        if (stack == null) return true;

        if (stack.getItem().equals(DSEXManager.dragonEssenceBottle)) {
            return false;
        } else {
            tryPerformCauldronRecipe(block, world, x, y, z, thisBlockMeta, player, stack);
        }
        
        return true;
    }


    public static void tryPerformCauldronRecipe(Block block, World world, int x, int y, int z, int meta, EntityPlayer player, ItemStack stack)
	{
		int essentiaLevel = BlockModCauldron.func_150027_b(meta);

        CauldronRecipe recipe = DragonScalesOldAPI.getValidRecipe(stack, essentiaLevel, world, x, y, z, player);
        
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
}
