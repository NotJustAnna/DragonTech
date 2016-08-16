package cf.brforgers.api.DragonTech.cauldron;

import cf.brforgers.api.DragonTech.IProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

public class DummyCauldronRecipe extends IForgeRegistryEntry.Impl<ICauldronRecipe> implements ICauldronRecipe, IProvider<IJEICauldron> {
    protected final ItemStack input;
    protected final ItemStack output;
    protected final int essentiaCost;
    private final IJEICauldron jei;

    public DummyCauldronRecipe(ItemStack input, int essentiaCost, ItemStack output) {
        this.input = input;
        this.output = output;
        this.essentiaCost = essentiaCost;
        jei = new DummyJEIRecipe(input, essentiaCost, output);
    }

    @Override
    public IJEICauldron provide() {
        return jei;
    }

    @Override
    public boolean isValidInput(World world, BlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        return this.input.isItemEqual(input) && (getItemCost(world, pos, heldItem, hand, essentiaLevel) <= input.stackSize) && (getEssentiaCost(world, pos, heldItem, hand, essentiaLevel) <= essentiaLevel);
    }

    @Override
    public ItemStack getOutput(World world, BlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        return null;
    }

    @Override
    public int getEssentiaCost(World world, BlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        return essentiaCost;
    }

    @Override
    public int getItemCost(World world, BlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        return input.stackSize;
    }

    public static class DummyJEIRecipe extends IForgeRegistryEntry.Impl<IJEICauldron> implements IJEICauldron {
        protected final ItemStack input;
        protected final ItemStack output;
        protected final int essentiaCost;

        public DummyJEIRecipe(ItemStack input, int essentiaCost, ItemStack output) {
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
    }
}
