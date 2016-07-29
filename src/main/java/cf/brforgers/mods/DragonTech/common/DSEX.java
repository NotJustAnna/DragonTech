package cf.brforgers.mods.DragonTech.common;

import cf.brforgers.mods.DragonTech.DragonTech;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import org.apache.logging.log4j.Logger;

/**
 * All Static Blocks, Items, etc
 */
public class DSEX {
    public static final Item.ToolMaterial DRAGONMETAL_TOOL_MATERIAL =
            EnumHelper.addToolMaterial("dragon_metal", 10, 2000, 50.0F, 16.0F, 35);
    public static final ItemArmor.ArmorMaterial DRAGONSCALES_ARMOR_MATERIAL =
            EnumHelper.addArmorMaterial("dragon_scales", "scales", 50, new int[]{5, 16, 12, 6}, 35, null, 3.0f);
    public static final Logger LOGGER = DragonTech.logger;

    // All Items
    public static Item DRAGON_SCALE, DRAGON_ESSENCE_SHARD, DRAGON_METAL, DRAGON_ESSENCE_BOTTLE, INFUSED_STICK,
            DRAGON_SWORD, DRAGON_MULTI, DRAGON_SCEPTER;

    public static ItemArmor SCALES_HELM, SCALES_CHESTPLATE, SCALES_LEGGINGS, SCALES_BOOTS;

    // All Blocks
    public static Block CAULDRON, CAULDRON_CONSTRUCT, DRAGON_BRICKS, DRAGON_SCALE_BLOCK,
            DRAGON_CRYSTAL, DRAGON_GRASS, DRAGON_DIRT, DRAGON_STONE, DRACONY_LEAVES, DRACONY_LOG, DRACONY_SAPLING, DRACONY_PLANKS,
            DRAGON_ESSENCE_BLOCK;
}
