package cf.brforgers.mods.DragonTech.common.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ArmorUtils {
    /**
     * This will return the player armor, where [0]=> Boots and [3]=> Helmet
     *
     * @param player the Player
     * @return the Armor Array
     */
    public static ItemStack[] getCurrentArmor(EntityPlayer player) {
        return player.inventory.armorInventory;
    }
}
