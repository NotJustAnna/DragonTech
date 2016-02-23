package hyghlander.mods.DragonScales.common.items;

import hyghlander.mods.DragonScales.DragonScales;
import hyghlander.mods.DragonScales.common.DragonScalesHandler;
import hyghlander.mods.DragonScales.common.blocks.BlockModCauldron;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModItemDragonScale extends ModItem {
	public ItemStack returnItemstack = null;
	public ModItemDragonScale(String name, ItemStack returnedItemStackOnUse) {
		super(name);
		returnItemstack = returnedItemStackOnUse;
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World theWorld, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		if (!player.canPlayerEdit(x, y, z, side, stack))
            return false;
		
		if (theWorld.getBlock(x, y, z) != Blocks.cauldron ||theWorld.getBlockMetadata(x, y, z) != 3)
			return false;
		
		theWorld.setBlock(x, y, z, DragonScalesHandler.modCauldron, 3, 3);
		((BlockModCauldron)DragonScalesHandler.modCauldron).setMetadataProperly(theWorld, x, y, z, 3);
		
		stack.stackSize -= 1;
		
		if(stack.stackSize <= 0)
			player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
        
        if (returnItemstack != null)
            if (!player.inventory.addItemStackToInventory(returnItemstack.copy()))
                theWorld.spawnEntityInWorld(new EntityItem(theWorld, (double)x + 0.5D, (double)y + 1.5D, (double)z + 0.5D, returnItemstack.copy()));
            else if (player instanceof EntityPlayerMP)
                ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
        return false;
    }
}
