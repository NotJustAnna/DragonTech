package cf.brforgers.api.DragonScalesEX.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IEventArmor {
    public void onArmorWorn(World world, EntityPlayer player, ItemStack stack);

    public void onArmorUnworn(World world, EntityPlayer player, ItemStack stack);
}
