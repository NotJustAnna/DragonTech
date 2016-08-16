package cf.brforgers.api.DragonTech;

import cf.brforgers.api.DragonTech.cauldron.ICauldronRecipe;
import cf.brforgers.api.DragonTech.cauldron.IJEICauldron;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DragonTechAPI {
    public static final List<ICauldronRecipe> cauldronRecipes = new ArrayList<ICauldronRecipe>();
    public static final List<IJEICauldron> jeiCauldronRecipes = new ArrayList<IJEICauldron>();
    private static final Map<Block, Float> customMultiToolMiningSpeed = new HashMap<Block, Float>();

    private DragonTechAPI() {
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
