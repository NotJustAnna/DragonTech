package cf.brforgers.api.DragonTech.cauldron;

import cf.brforgers.core.lib.ez.mods.ModDefinition;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DummyCauldronRecipe implements ICauldronRecipe, IJEICauldron {
    protected final ItemStack input;
    protected final ItemStack output;
    protected final int essentiaCost;

    public DummyCauldronRecipe(ItemStack input, int essentiaCost, ItemStack output) {
        this.input = input;
        this.output = output;
        this.essentiaCost = essentiaCost;
    }

    public String procedurallyGenerateName(ModDefinition definition) {
        return (definition.getLocation(input.getDisplayName() + "To" + output.getDisplayName() + "Costing" + essentiaCost + "Essentia"));
    }

    @Override
    public boolean isValidInput(World world, BlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        return this.input.isItemEqual(input) && (getItemCost(world, pos, heldItem, hand, essentiaLevel) <= input.stackSize) && (getEssentiaCost(world, pos, heldItem, hand, essentiaLevel) <= essentiaLevel);
    }

    @Override
    public ItemStack getOutput(World world, BlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        return output;
    }

    @Override
    public int getEssentiaCost(World world, BlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        return essentiaCost;
    }

    @Override
    public int getItemCost(World world, BlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        return input.stackSize;
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
}
