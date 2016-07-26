package cf.brforgers.api.DragonScalesEX.cauldron;

import cf.brforgers.api.DragonScalesEX.utils.BlockActivationParams;
import net.minecraft.item.ItemStack;

public interface ICauldronRecipe {
    public boolean isValidInput(BlockActivationParams activation, int essentiaLevel);

    public ItemStack getOutput(BlockActivationParams activation, int essentiaLevel);

    public int getEssentiaCost(BlockActivationParams activation, int essentiaLevel);

    public int getItemCost(BlockActivationParams activation, int essentiaLevel);
}
