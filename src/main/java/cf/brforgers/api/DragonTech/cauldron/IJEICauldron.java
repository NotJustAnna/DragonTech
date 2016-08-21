package cf.brforgers.api.DragonTech.cauldron;

import net.minecraft.item.ItemStack;

public interface IJEICauldron {
    ItemStack getJEIInput();

    ItemStack getJEIOutput();

    int getJEIEssentia();
}
