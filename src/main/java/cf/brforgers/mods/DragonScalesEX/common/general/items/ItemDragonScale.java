package cf.brforgers.mods.DragonScalesEX.common.general.items;

import cf.brforgers.mods.DragonScalesEX.common.DSEXManager;
import cf.brforgers.mods.DragonScalesEX.common.general.blocks.BlockModCauldron;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDragonScale extends Item {
	public ItemStack returnItemstack = null;
	public ItemDragonScale(ItemStack returnedItemStackOnUse) {
		super();
		returnItemstack = returnedItemStackOnUse;
	}
	
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		if (!player.canPlayerEdit(x, y, z, side, stack))
            return false;
		
		if (world.getBlock(x, y, z) != Blocks.cauldron ||world.getBlockMetadata(x, y, z) != 3)
			return false;

        world.setBlock(x, y, z, DSEXManager.modCauldron, 3, 3);
        BlockModCauldron.setMetadataProperly(world, x, y, z, 3, DSEXManager.modCauldron);

        stack.stackSize -= 1;
		
		if(stack.stackSize <= 0)
			player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
        
        if (returnItemstack != null)
            if (!player.inventory.addItemStackToInventory(returnItemstack.copy()))
                world.spawnEntityInWorld(new EntityItem(world, (double)x + 0.5D, (double)y + 1.5D, (double)z + 0.5D, returnItemstack.copy()));
            else if (player instanceof EntityPlayerMP)
                ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
        return false;
    }
}
