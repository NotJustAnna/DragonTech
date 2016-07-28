package cf.brforgers.api.DragonScalesEX.cauldron;

import cf.brforgers.core.lib.world.WorldBlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class DummyCauldronRecipe implements ICauldronRecipe, IJEICauldron {
    protected final ItemStack input;
    protected final ItemStack output;
    protected final int essentiaCost;

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
    public boolean isValidInput(WorldBlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        return this.input.isItemEqual(input) && (getItemCost(pos, heldItem, hand, essentiaLevel) <= input.stackSize) && (getEssentiaCost(pos, heldItem, hand, essentiaLevel) <= essentiaLevel);
    }

    @Override
    public ItemStack getOutput(WorldBlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        return null;
    }

    @Override
    public int getEssentiaCost(WorldBlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        return essentiaCost;
    }

    @Override
    public int getItemCost(WorldBlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        return input.stackSize;
    }
}
