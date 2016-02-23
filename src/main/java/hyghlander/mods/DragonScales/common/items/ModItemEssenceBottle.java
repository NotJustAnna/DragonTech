package hyghlander.mods.DragonScales.common.items;

import hyghlander.mods.DragonScales.common.DragonScalesHandler;
import hyghlander.mods.DragonScales.common.blocks.BlockModCauldron;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModItemEssenceBottle extends ModItemDragonScale {

	public ModItemEssenceBottle(String name) {
		super(name, new ItemStack(Items.glass_bottle));
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World theWorld, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		if (!player.canPlayerEdit(x, y, z, side, stack))
            return false;
		Block block = theWorld.getBlock(x, y, z);
		int meta = theWorld.getBlockMetadata(x, y, z);
		
		if ((block == Blocks.cauldron && meta > 0) || (block == DragonScalesHandler.modCauldron && meta == 3))
			return false;
			
		if (block == Blocks.cauldron && meta == 0)
			theWorld.setBlock(x, y, z, DragonScalesHandler.modCauldron, 1, 3);
			((BlockModCauldron)DragonScalesHandler.modCauldron).setMetadataProperly(theWorld, x, y, z, 1);
		
		if (block == DragonScalesHandler.modCauldron && meta < 3)
		{
			meta++;
			theWorld.setBlock(x, y, z, DragonScalesHandler.modCauldron, meta, 3);
			((BlockModCauldron)DragonScalesHandler.modCauldron).setMetadataProperly(theWorld, x, y, z, meta);
		}
		stack.stackSize--;
		
		if(stack.stackSize <= 0)
			player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
		
		if (!player.inventory.addItemStackToInventory(returnItemstack.copy()))
            theWorld.spawnEntityInWorld(new EntityItem(theWorld, (double)x + 0.5D, (double)y + 1.5D, (double)z + 0.5D, returnItemstack.copy()));
        else if (player instanceof EntityPlayerMP)
            ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
		
        return false;
    }
}
