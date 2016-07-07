package cf.adriantodt.api.DragonScales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DragonScalesAPI {
	public static class CauldronRecipe {
		/**
		 * This is the Default ItemStack that the Recipe use as Input Validator (Except Custom Recipes).
		 * <br>Also, NEI Plugin depends on it.
		 * <br>So, You fuck this up, and NEI fuck you.
		 */
		public final ItemStack input;
		/**
		 * This is the Default ItemStack that the Recipe use as Pattern to Outputs (Except Custom Recipes).
		 * <br>Also, NEI Plugin depends on it.
		 * <br>So, You fuck this up, and NEI fuck you.
		 */
		public final ItemStack output;
		/**
		 * This is the Default ItemStack that the Recipe use as Essentia Cost (Except Custom Recipes).
		 * <br>Also, NEI Plugin depends on it.
		 * <br>So, You fuck this up, and NEI fuck you.
		 */
		public final int essentiaCost;
		
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
	
	private static Map<Block, Float> customMultiToolMiningSpeed = new HashMap<Block, Float>();
	public static void setCustomSpeed(Block block, float speed) {
		customMultiToolMiningSpeed.put(block, speed);
	}
	public static float getSpeed(Block block) {
		Float f = customMultiToolMiningSpeed.get(block);
		if(f==null) return 1.0f;
		return f;
	}
}