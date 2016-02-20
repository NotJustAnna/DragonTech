package hyghlander.mods.DragonScales.client.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.ItemList;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect;
import hyghlander.mods.DragonScales.Lib;
import hyghlander.mods.DragonScales.api.DragonScalesAPI;
import hyghlander.mods.DragonScales.api.DragonScalesAPI.CauldronRecipe;
import hyghlander.mods.DragonScales.common.blocks.tile.TileEntityModCauldron;

public class CauldronHandler extends TemplateRecipeHandler{
	
	public String getRecipeName() {
		return "Cauldron Infusion";
	}

	public String getGuiTexture() {
		
		return new ResourceLocation(Lib.MODID, "textures/gui/NEICauldron.png").toString();
	}
	
	public class SmeltingPair extends CachedRecipe
    {
        public SmeltingPair(ItemStack ingred, ItemStack result) {
            
            this.ingred1 = new PositionedStack(ingred, 48, 5);
            
            this.result = new PositionedStack(result, 84, 46);
        }

        public List<PositionedStack> getIngredients() {
        	List<PositionedStack> l = new ArrayList<PositionedStack>();
        	l.add(ingred1);
        	
            return getCycledIngredients(cycleticks / 48, l);
        }

        public PositionedStack getResult() {
            return result;
        }

        public PositionedStack getOtherStack() {
            return afuels.get((cycleticks / 48) % afuels.size()).stack;
        }

        PositionedStack ingred1;
        PositionedStack result;
    }

    public static class FuelPair
    {
        public FuelPair(ItemStack ingred, int burnTime) {
            this.stack = new PositionedStack(ingred, 3, 49, false);
            this.burnTime = burnTime;
        }

        public PositionedStack stack;
        public int burnTime;
    }

    public static ArrayList<FuelPair> afuels;
    public static HashSet<Block> efuels;

    public void loadTransferRects() {
    	transferRects.add(new RecipeTransferRect(new Rectangle(78, 34, 24, 18), "infusion"));
    }

    public Class<? extends GuiContainer> getGuiClass() {
    	return GuiFurnace.class;
    }

    public TemplateRecipeHandler newInstance() {
        if (afuels == null)
            findFuels();
        return super.newInstance();
    }

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("infusion") && getClass() == CauldronHandler.class) {//don't want subclasses getting a hold of this
            List<CauldronRecipe> recipes = DragonScalesAPI.cauldronRecipes;
            for (CauldronRecipe recipe : recipes)
                arecipes.add(new SmeltingPair(recipe.input, recipe.output));
            	
        } else
            super.loadCraftingRecipes(outputId, results);
    }

    public void loadCraftingRecipes(ItemStack result) {
    	 List<CauldronRecipe> recipes = DragonScalesAPI.cauldronRecipes;
    	 for (CauldronRecipe recipe : recipes)
            if (NEIServerUtils.areStacksSameType(recipe.output, result))
            	arecipes.add(new SmeltingPair(recipe.input, recipe.output));
        	
    }

    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (inputId.equals("infusion") && getClass() == CauldronHandler.class)//don't want subclasses getting a hold of this
            loadCraftingRecipes("infusion");
        else
            super.loadUsageRecipes(inputId, ingredients);
    }

    public void loadUsageRecipes(ItemStack ingredient) {
    	List<CauldronRecipe> recipes = DragonScalesAPI.cauldronRecipes;
    	for (CauldronRecipe recipe : recipes)
            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.output, ingredient)) {
                SmeltingPair arecipe = new SmeltingPair(recipe.input, recipe.output);
                
                List l = new ArrayList();
            	l.add(arecipe.ingred1);
            	
                arecipe.setIngredientPermutation(l, ingredient);
                arecipes.add(arecipe);
            }
    }

    public void drawExtras(int recipe) {
    	drawProgressBar(6, 0, 177, 2, 9, 47, 48, 3);
    	drawProgressBar(62, 13, 188, 0, 59, 28, 48, 1);
    }

    private static Set<Item> excludedFuels() {
        Set<Item> efuels = new HashSet<Item>();
        return efuels;
    }

    private static void findFuels() {
        afuels = new ArrayList<FuelPair>();
        Set<Item> efuels = excludedFuels();
        for (ItemStack item : ItemList.items)
            if (!efuels.contains(item.getItem())) {
                int burnTime = 200;
                if (burnTime > 0)
                	afuels.add(new FuelPair(item, burnTime));
            }
    }

    public String getOverlayIdentifier() {
        return "infusion";
    }
}
