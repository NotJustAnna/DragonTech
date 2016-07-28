package cf.brforgers.api.DragonScalesEX.cauldron;

import cf.brforgers.core.lib.world.WorldBlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public interface ICauldronRecipe {
    public boolean isValidInput(WorldBlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel);

    public ItemStack getOutput(WorldBlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel);

    public int getEssentiaCost(WorldBlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel);

    public int getItemCost(WorldBlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel);
}
