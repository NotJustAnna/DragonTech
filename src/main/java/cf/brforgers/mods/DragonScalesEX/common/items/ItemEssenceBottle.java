package cf.brforgers.mods.DragonScalesEX.common.items;

import cf.brforgers.mods.DragonScalesEX.common.DSEXManager;
import cf.brforgers.mods.DragonScalesEX.common.blocks.BlockModCauldron;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemEssenceBottle extends Item {
	private static IBehaviorDispenseItem dispenserBehavior = new IBehaviorDispenseItem() {
		@Override
		public ItemStack dispense(IBlockSource blockSource, ItemStack stack) {
			EnumFacing facing = BlockDispenser.func_149937_b(blockSource.getBlockMetadata()); //Get Facing
			int x = blockSource.getXInt() + facing.getFrontOffsetX();
			int y = blockSource.getYInt() + facing.getFrontOffsetY();
			int z = blockSource.getZInt() + facing.getFrontOffsetZ();

			Block block = blockSource.getWorld().getBlock(x, y, z);
			int meta = blockSource.getWorld().getBlockMetadata(x, y, z);

			if (block == DSEXManager.modCauldron && meta < 3 || block == Blocks.cauldron && meta == 0) {
				meta++;
				meta &= 3;
				blockSource.getWorld().setBlock(x, y, z, DSEXManager.modCauldron, meta, 3);
				((BlockModCauldron) DSEXManager.modCauldron).setMetadataProperly(blockSource.getWorld(), x, y, z, meta, DSEXManager.modCauldron);
			} else {
				return stack;
			}
			stack.stackSize -= 1;
			return stack;
		}
	};

	public ItemStack returnItemstack = null;
	public ItemEssenceBottle(ItemStack returnedItemStackOnUse) {
		super();
		returnItemstack = returnedItemStackOnUse;
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserBehavior);
	}
	
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World theWorld, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		if (!player.canPlayerEdit(x, y, z, side, stack))
            return false;
		Block block = theWorld.getBlock(x, y, z);
		int meta = theWorld.getBlockMetadata(x, y, z);

		if (block == DSEXManager.modCauldron && meta < 3 || block == Blocks.cauldron && meta == 0)
		{
			meta++;
			theWorld.setBlock(x, y, z, DSEXManager.modCauldron, meta & 3, 3);
			((BlockModCauldron) DSEXManager.modCauldron).setMetadataProperly(theWorld, x, y, z, meta & 3, DSEXManager.modCauldron);
		} else {
			return false;
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
