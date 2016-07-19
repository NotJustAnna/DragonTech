package cf.brforgers.mods.DragonScalesEX.common.items;

import cf.brforgers.mods.DragonScalesEX.common.DSEXManager;
import cf.brforgers.mods.DragonScalesEX.common.blocks.BlockModCauldron;
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

public class ItemDragonScale extends Item {
	private static IBehaviorDispenseItem dispenserBehavior = new IBehaviorDispenseItem() {
		@Override
		public ItemStack dispense(IBlockSource blockSource, ItemStack stack) {
			EnumFacing facing = BlockDispenser.func_149937_b(blockSource.getBlockMetadata()); //Get Facing
			int x = blockSource.getXInt() + facing.getFrontOffsetX();
			int y = blockSource.getYInt() + facing.getFrontOffsetY();
			int z = blockSource.getZInt() + facing.getFrontOffsetZ();

			if (blockSource.getWorld().getBlock(x, y, z) != Blocks.cauldron || blockSource.getWorld().getBlockMetadata(x, y, z) != 3)
				return stack;

            blockSource.getWorld().setBlock(x, y, z, DSEXManager.modCauldron, 3, 3);
            BlockModCauldron.setMetadataProperly(blockSource.getWorld(), x, y, z, 3, DSEXManager.modCauldron);
            stack.stackSize -= 1;
			return stack;
		}
	};

	public ItemStack returnItemstack = null;
	public ItemDragonScale(ItemStack returnedItemStackOnUse) {
		super();
		returnItemstack = returnedItemStackOnUse;
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserBehavior);
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
