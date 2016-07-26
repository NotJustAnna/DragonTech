package cf.brforgers.api.DragonScalesEX.cauldron;

import cf.brforgers.api.DragonScalesEX.utils.BlockActivationParams;
import net.minecraft.item.ItemStack;

public class DummyCauldronRecipe implements ICauldronRecipe, IJEICauldron {
    private final ItemStack input;
    private final ItemStack output;
    private final int essentiaCost;

    public DummyCauldronRecipe(ItemStack input, int essentiaCost, ItemStack output) {
        this.input = input;
        this.output = output;
        this.essentiaCost = essentiaCost;
    }

    @Override
    public ItemStack getJEIInput() {
        return input;
    }

    @Override
    public ItemStack getJEIOutput() {
        return output;
    }

    @Override
    public int getJEIEssentia() {
        return essentiaCost;
    }

    @Override
    public boolean isValidInput(BlockActivationParams activation, int essentiaLevel) {
        return this.input.isItemEqual(input) && (getItemCost(activation, essentiaLevel) <= input.stackSize) && (getEssentiaCost(activation, essentiaLevel) <= essentiaLevel);
    }

    @Override
    public ItemStack getOutput(BlockActivationParams activation, int essentiaLevel) {
        return null;
    }

    @Override
    public int getEssentiaCost(BlockActivationParams activation, int essentiaLevel) {
        return essentiaCost;
    }

    @Override
    public int getItemCost(BlockActivationParams activation, int essentiaLevel) {
        return input.stackSize;
    }
}
