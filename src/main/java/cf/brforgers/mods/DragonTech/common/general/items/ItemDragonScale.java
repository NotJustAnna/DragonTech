package cf.brforgers.mods.DragonTech.common.general.items;

import cf.brforgers.mods.DragonTech.common.general.CauldronHandler;
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

public class ItemDragonScale extends Item {
    public ItemStack returnItemstack = null;

    public ItemDragonScale(ItemStack returnedItemStackOnUse) {
        super();
        returnItemstack = returnedItemStackOnUse;
    }

    @Override
    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (!player.canPlayerEdit(pos, side, stack))
            return EnumActionResult.FAIL;

        if (world.getBlockState(pos).getBlock() != Blocks.CAULDRON || world.getBlockState(pos).getValue(BlockCauldron.LEVEL) != 3)
            return EnumActionResult.FAIL;

        stack.stackSize -= 1;

        if (stack.stackSize <= 0)
            player.inventory.setInventorySlotContents(player.inventory.currentItem, null);

        CauldronHandler.setWaterLevel(world, pos, 3, true);

        ItemStack returnStack = returnItemstack.copy();

        if (!player.inventory.addItemStackToInventory(returnStack))
            world.spawnEntityInWorld(new EntityItem(world, (double) pos.getX() + 0.5D, pos.getY() + 1.5D, (double) pos.getZ() + 0.5D, returnStack));
        else if (player instanceof EntityPlayerMP)
                ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
        return EnumActionResult.SUCCESS;
    }
}
