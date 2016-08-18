package cf.brforgers.api.DragonTech;

import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

public class DragonTechAPI {
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
