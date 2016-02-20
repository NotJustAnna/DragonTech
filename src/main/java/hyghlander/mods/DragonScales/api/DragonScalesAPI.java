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
		
		public boolean isValidInput(ItemStack input)
		{
			return this.input.isItemEqual(input) && (getItemCost(input) >= input.stackSize);
		}
		
		public ItemStack getOutput(ItemStack input)
		{
			return this.output.copy();
		}
		
		public int getEssentiaCost(ItemStack input)
		{
			return this.essentiaCost;
		}
		
		public int getItemCost(ItemStack input)
		{
			return this.input.stackSize;
		}
	}
	
	public static final List<CauldronRecipe> cauldronRecipes = new ArrayList<CauldronRecipe>();
	
	public static CauldronRecipe getValidRecipe(ItemStack input, int essentiaLevel) {
		for (CauldronRecipe recipe : cauldronRecipes)
		{
			if (recipe.isValidInput(input)) return recipe;
		}
		return null;
	}
}
