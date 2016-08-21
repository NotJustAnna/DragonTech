package cf.brforgers.api.DragonTech.cauldron;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

public interface IJEICauldron extends IForgeRegistryEntry<IJEICauldron> {
    ItemStack getJEIInput();

    ItemStack getJEIOutput();

    int getJEIEssentia();
}
