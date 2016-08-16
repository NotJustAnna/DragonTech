package cf.brforgers.api.DragonTech.cauldron;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

public interface ICauldronRecipe extends IForgeRegistryEntry<ICauldronRecipe> {
    public boolean isValidInput(World world, BlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel);

    public ItemStack getOutput(World world, BlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel);

    public int getEssentiaCost(World world, BlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel);

    public int getItemCost(World world, BlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel);
}
