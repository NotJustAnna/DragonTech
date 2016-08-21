package cf.brforgers.mods.DragonTech.common.general.items;

import cf.brforgers.mods.DragonTech.common.DT;
import cf.brforgers.mods.DragonTech.common.general.CauldronHandler;
import cf.brforgers.mods.DragonTech.common.general.blocks.BlockModCauldron;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemEssenceBottle extends Item {
//	private static IBehaviorDispenseItem dispenserBehavior = new IBehaviorDispenseItem() {
//		@Override
//		public ItemStack dispense(IBlockSource blockSource, ItemStack stack) {
//			IBlockState state = blockSource.getWorld().getBlockState(blockSource.getBlockPos());
//			EnumFacing f = state.getValue(BlockDirectional.FACING);
//			BlockPos pos = blockSource.getBlockPos().offset(f, 1);
//
//			Block block = blockSource.getWorld().getBlockState(pos).getBlock();
//			int meta = blockSource.getWorld().getBlockMetadata(x, y, z);
//
//            if (block == DTManager.modCauldron && meta < 3 || block == Blocks.cauldron && meta == 0) {
//                meta++;
//				meta &= 3;
//                blockSource.getWorld().setBlock(x, y, z, DTManager.modCauldron, meta, 3);
//                ((BlockModCauldron) DTManager.modCauldron).setMetadataProperly(blockSource.getWorld(), x, y, z, meta, DTManager.modCauldron);
//            } else {
//				return stack;
//			}
//			stack.stackSize -= 1;
//			return null;
//		}
//	};

	public ItemStack returnItemstack = null;

	public ItemEssenceBottle(ItemStack returnedItemStackOnUse) {
		super();
		returnItemstack = returnedItemStackOnUse;
		//BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, dispenserBehavior);
	}

	@Override
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		if (!player.canPlayerEdit(pos, side, stack))
			return EnumActionResult.FAIL;

		if (!(world.getBlockState(pos).getBlock() == Blocks.CAULDRON && world.getBlockState(pos).getValue(BlockCauldron.LEVEL) == 0 || world.getBlockState(pos).getBlock() == DT.CAULDRON && world.getBlockState(pos).getValue(BlockModCauldron.LEVEL) == 3))
			return EnumActionResult.FAIL;

		stack.stackSize -= 1;

		if (stack.stackSize <= 0)
			player.inventory.setInventorySlotContents(player.inventory.currentItem, null);

		int meta = CauldronHandler.getWaterLevel(world, pos);

		if (meta < 3) {
			meta++;
			CauldronHandler.setWaterLevel(world, pos, meta, true);
		} else {
			return EnumActionResult.FAIL;
		}

		ItemStack returnStack = returnItemstack.copy();

		if (!player.inventory.addItemStackToInventory(returnStack))
			world.spawnEntityInWorld(new EntityItem(world, (double) pos.getX() + 0.5D, pos.getY() + 1.5D, (double) pos.getZ() + 0.5D, returnStack));
		else if (player instanceof EntityPlayerMP)
			((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
		return EnumActionResult.SUCCESS;
	}
}
