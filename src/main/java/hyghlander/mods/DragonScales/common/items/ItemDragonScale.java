package hyghlander.mods.DragonScales.common.items;

import hyghlander.mods.DragonScales.DragonScales;
import hyghlander.mods.DragonScales.common.DragonScalesHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDragonScale extends ModItem {
	public ItemDragonScale(String name) {
		super(name);
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World theWorld, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		if (!player.canPlayerEdit(x, y, z, side, stack))
            return false;
		
		if (theWorld.getBlock(x, y, z) != Blocks.cauldron ||theWorld.getBlockMetadata(x, y, z) != 3)
			return false;
		
		theWorld.setBlock(x, y, z, DragonScalesHandler.essentiaCauldron, 3, 3);
		
		stack.stackSize -= 1;
		
		if(stack.stackSize <= 0)
			player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
        return false;
    }
}
