package cf.brforgers.api.DragonScalesEX;

import cf.brforgers.api.DragonScalesEX.cauldron.ICauldronRecipe;
import cf.brforgers.api.DragonScalesEX.cauldron.IJEICauldron;
import cf.brforgers.core.lib.world.WorldBlockPos;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DragonScalesEXAPI {
    public static final List<ICauldronRecipe> cauldronRecipes = new ArrayList<ICauldronRecipe>();
    public static final List<IJEICauldron> jeiCauldronRecipes = new ArrayList<IJEICauldron>();
    private static final Map<Block, Float> customMultiToolMiningSpeed = new HashMap<Block, Float>();

    private DragonScalesEXAPI() {
    }

    public static ICauldronRecipe getValidRecipe(WorldBlockPos pos, ItemStack heldItem, EnumHand hand, int essentiaLevel) {
        for (ICauldronRecipe recipe : cauldronRecipes) {
            if (recipe.isValidInput(pos, heldItem, hand, essentiaLevel)) return recipe;
        }
        return null;
    }

    public static void register(Object object) {
        if (object instanceof ICauldronRecipe) {
            cauldronRecipes.add((ICauldronRecipe) object);
        }

        if (object instanceof IJEICauldron) {
            jeiCauldronRecipes.add((IJEICauldron) object);
        }
    }

    public static void setCustomSpeedForBlock(Block block, float speed) {
        customMultiToolMiningSpeed.put(block, speed);
    }

    public static float getCustomSpeedForBlock(Block block) {
        Float f = customMultiToolMiningSpeed.get(block);
        if (f == null) return 1.0f;
        return f;
    }
}
