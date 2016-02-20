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
import net.minecraft.init.Blocks;
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
import hyghlander.mods.DragonScales.common.DragonScalesHandler;
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
        public SmeltingPair(ItemStack ingred, ItemStack essentia, ItemStack result) {
            
            this.ingred1 = new PositionedStack(ingred, 47, 20);
            this.essentia = new PositionedStack(essentia, 70, 40);
            this.result = new PositionedStack(result, 103, 20);
            this.cauld = new PositionedStack(new ItemStack(Blocks.cauldron), 72, 20);
        }

        public List<PositionedStack> getIngredients() {
        	List<PositionedStack> l = new ArrayList<PositionedStack>();
        	l.add(ingred1);
        	l.add(essentia);
        	l.add(cauld);
        	
            return getCycledIngredients(cycleticks / 48, l);
        }

        public PositionedStack getResult() {
            return result;
        }

        public PositionedStack getOtherStack() {
            return afuels.get((cycleticks / 48) % afuels.size()).stack;
        }

        PositionedStack cauld;
        PositionedStack ingred1;
        PositionedStack essentia;
        PositionedStack result;
    }

    public static class FuelPair
    {
        public FuelPair(ItemStack ingred, int burnTime) {
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
            for (CauldronRecipe recipe : recipes) {
            	switch(recipe.essentiaCost) {
            		case 0:
            			arecipes.add(new SmeltingPair(recipe.input, new ItemStack(Items.glass_bottle), recipe.output));
            			break;
            		default:
            			arecipes.add(new SmeltingPair(recipe.input, new ItemStack(DragonScalesHandler.dragonEssenceBottle, recipe.essentiaCost), recipe.output));
            	}
            }
            	
        } else
            super.loadCraftingRecipes(outputId, results);
    }

    public void loadCraftingRecipes(ItemStack result) {
    	 List<CauldronRecipe> recipes = DragonScalesAPI.cauldronRecipes;
    	 for (CauldronRecipe recipe : recipes)
            if (NEIServerUtils.areStacksSameType(recipe.output, result)) {
            	if(recipe.essentiaCost == 0)
            		arecipes.add(new SmeltingPair(recipe.input, new ItemStack(Items.glass_bottle), recipe.output));
            	else
            		arecipes.add(new SmeltingPair(recipe.input, new ItemStack(DragonScalesHandler.dragonEssenceBottle, recipe.essentiaCost), recipe.output));
            }
        	
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
                SmeltingPair arecipe;
                if(recipe.essentiaCost == 0)
            		arecipe = new SmeltingPair(recipe.input, new ItemStack(Items.glass_bottle), recipe.output);
            	else
            		arecipe = new SmeltingPair(recipe.input, new ItemStack(DragonScalesHandler.dragonEssenceBottle, recipe.essentiaCost), recipe.output);
                
                List l = new ArrayList();
            	l.add(arecipe.ingred1);
            	
                arecipe.setIngredientPermutation(l, ingredient);
                arecipes.add(arecipe);
            }
    }

    public void drawExtras(int recipe) {
    	drawProgressBar(62, 13, 188, 0, 59, 28, 48, 1);// AJEITAR AQ
    }

    private static Set<Item> excludedFuels() {
        Set<Item> efuels = new HashSet<Item>();
        return efuels;
    }

    private static void findFuels() {
        afuels = new ArrayList<FuelPair>();
        List<CauldronRecipe> recipes = DragonScalesAPI.cauldronRecipes;
        for (CauldronRecipe recipe : recipes) {
        	int burnTime = 200;
        	if(recipe.essentiaCost == 0)
        		afuels.add(new FuelPair(new ItemStack(Items.potionitem), burnTime));
        	else
        		afuels.add(new FuelPair(new ItemStack(DragonScalesHandler.dragonEssenceBottle, recipe.essentiaCost), burnTime));
        }
    }

    public String getOverlayIdentifier() {
        return "infusion";
    }
}
