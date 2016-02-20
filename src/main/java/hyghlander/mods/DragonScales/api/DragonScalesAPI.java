package hyghlander.mods.DragonScales.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class DragonScalesAPI {
	public static class CauldronRecipe {
		protected ItemStack input;
		protected ItemStack output;
		protected int essentiaCost;
		
		public CauldronRecipe(ItemStack input, int essentiaCost, ItemStack output)
		{
			this.input = input;
			this.output = output;
			this.essentiaCost = essentiaCost;
		}
		
		public boolean isValidInput(ItemStack input, int essentiaLevel)
		{
			return this.input.isItemEqual(input) && (getItemCost(input, essentiaLevel) <= input.stackSize) && (getEssentiaCost(input,essentiaLevel) <= essentiaLevel);
		}
		
		public ItemStack getOutput(ItemStack input, int essentiaLevel)
		{
			return this.output.copy();
		}
		
		public int getEssentiaCost(ItemStack input, int essentiaLevel)
		{
			return this.essentiaCost;
		}
		
		public int getItemCost(ItemStack input, int essentiaLevel)
		{
			return this.input.stackSize;
		}
	}
	
	public static final List<CauldronRecipe> cauldronRecipes = new ArrayList<CauldronRecipe>();
	
	public static CauldronRecipe getValidRecipe(ItemStack input, int essentiaLevel) {
		for (CauldronRecipe recipe : cauldronRecipes)
		{
			if (recipe.isValidInput(input, essentiaLevel)) return recipe;
		}
		return null;
	}
}
