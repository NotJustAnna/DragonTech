package cf.brforgers.mods.DragonScalesEX.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import cf.brforgers.mods.DragonScalesEX.Lib;
import cf.brforgers.api.DragonScalesEX.DragonScalesAPI;
import cf.brforgers.api.DragonScalesEX.DragonScalesAPI.CauldronRecipe;
import cf.brforgers.mods.DragonScalesEX.common.DragonScalesHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class CauldronHandler extends TemplateRecipeHandler{
	public class InfusionPair extends CachedRecipe
    {
        PositionedStack inputStack, essentiaBottle, outputStack, cauldronIcon;
        
        public InfusionPair(ItemStack ingred, int essentiaAmount, ItemStack result) {
            essentiaAmount = MathHelper.clamp_int(essentiaAmount, 0, 3);
            this.inputStack = new PositionedStack(ingred, 43, 18);
            this.essentiaBottle = new PositionedStack((essentiaAmount == 0) ? new ItemStack(Items.glass_bottle) : new ItemStack(DragonScalesHandler.dragonEssenceBottle, essentiaAmount), 74, 38);
            this.outputStack = new PositionedStack(result, 105, 18);
            this.cauldronIcon = new PositionedStack(new ItemStack(Items.cauldron), 74, 18);
        }

        public List<PositionedStack> getIngredients() {
        	List<PositionedStack> l = new ArrayList<PositionedStack>();
        	l.add(inputStack);
        	l.add(essentiaBottle);
        	l.add(cauldronIcon);
        	
            return getCycledIngredients(cycleticks / 48, l);
        }

        public PositionedStack getResult() { return outputStack; }
    }
	
	//Main things
	public String getRecipeName() {return StatCollector.translateToLocal("recipe.essenceInfusion"); }
	public String getGuiTexture() {return new ResourceLocation(Lib.MODID, "textures/gui/NEICauldron.png").toString();}
    public String getOverlayIdentifier() {return "essenceInfusion";}
	
	public void loadTransferRects() {transferRects.add(new RecipeTransferRect(new Rectangle(69, 13, 26, 26), "essenceInfusion"));}

    public Class<? extends GuiContainer> getGuiClass() {return GuiFurnace.class;}

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("essenceInfusion") && getClass() == CauldronHandler.class) {//don't want subclasses getting a hold of this
            for (CauldronRecipe recipe : DragonScalesAPI.cauldronRecipes)
            	arecipes.add(new InfusionPair(recipe.input, recipe.essentiaCost, recipe.output));
        } else
            super.loadCraftingRecipes(outputId, results);
    }

    public void loadCraftingRecipes(ItemStack result) {
    	 for (CauldronRecipe recipe : DragonScalesAPI.cauldronRecipes)
            if (NEIServerUtils.areStacksSameType(recipe.output, result))
            	arecipes.add(new InfusionPair(recipe.input, recipe.essentiaCost, recipe.output));
    }

    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (inputId.equals("essenceInfusion") && getClass() == CauldronHandler.class)//don't want subclasses getting a hold of this
            loadCraftingRecipes("essenceInfusion");
        else
            super.loadUsageRecipes(inputId, ingredients);
    }

    public void loadUsageRecipes(ItemStack ingredient) {
    	List<CauldronRecipe> recipes = DragonScalesAPI.cauldronRecipes;
    	for (CauldronRecipe recipe : recipes)
            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.output, ingredient)) {
                InfusionPair arecipe = new InfusionPair(recipe.input, recipe.essentiaCost, recipe.output);
                
                List list = new ArrayList();
            	list.add(arecipe.inputStack);
            	
                arecipe.setIngredientPermutation(list, ingredient);
                arecipes.add(arecipe);
            }
    }

    //public void drawExtras(int recipe) {
    //	drawProgressBar(62, 13, 188, 0, 59, 28, 48, 1);// AJEITAR AQ
    //}
}
