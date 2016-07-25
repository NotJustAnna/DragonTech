package cf.brforgers.api.DragonScalesEX;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DragonScalesOldAPI {
	public static final List<CauldronRecipe> recipesAddedToDispenserBehaviour = new ArrayList<CauldronRecipe>();
	public static final List<CauldronRecipe> cauldronRecipes = new ArrayList<CauldronRecipe>();
	private static Map<Block, Float> customMultiToolMiningSpeed = new HashMap<Block, Float>();

	public static void registerDispenserRecipeBehaviour(CauldronRecipe recipe) {
		recipesAddedToDispenserBehaviour.add(recipe);
	}

	public static CauldronRecipe getValidRecipe(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player) {
		for (CauldronRecipe recipe : cauldronRecipes) {
			if (recipe.isValidInput(input, essentiaLevel, world, z, z, z, player)) return recipe;
		}
		return null;
	}

    public static void setMultitoolCustomSpeed(Block block, float speed) {
        customMultiToolMiningSpeed.put(block, speed);
	}

	public static float getSpeed(Block block) {
		Float f = customMultiToolMiningSpeed.get(block);
		if (f == null) return 1.0f;
		return f;
	}

	public static class CauldronRecipe {
		/**
		 * This is the Default ItemStack that the Recipe use as Input Validator (Except Custom Recipes).
		 * <br>In 1.3+, the Default Dispenser Behaviour Generator needs this (You will need to create a custom Dispenser Behaviour by yourself)
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

		/**
		 * Check if it's a valid input (Here you can setup the recipe to have multiple inputs)
		 *
		 * @param input         the ItemStack being holded
		 * @param essentiaLevel Essentia Level in Cauldron
		 * @param world         World where the Cauldron is
		 * @param x             Coordinates of the Cauldron
		 * @param y             Coordinates of the Cauldron
		 * @param z             Coordinates of the Cauldron
		 * @param player        The Player (Null if activated by a Dispenser)
		 * @return if is valid
		 */
		public boolean isValidInput(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player)
		{
			return this.input.isItemEqual(input) && (getItemCost(input, essentiaLevel, world, z, z, z, player) <= input.stackSize) && (getEssentiaCost(input,essentiaLevel, world, z, z, z, player) <= essentiaLevel);
		}

		public ItemStack getOutput(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player)
		{
			return this.output.copy();
		}

		public int getEssentiaCost(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player)
		{
			return this.essentiaCost;
		}

		public int getItemCost(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player) {
			return this.input.stackSize;
		}

		public CauldronRecipe registerDefaultDispenserBehaviour() {
			registerDispenserRecipeBehaviour(this);

			return this;
		}
	}
}
