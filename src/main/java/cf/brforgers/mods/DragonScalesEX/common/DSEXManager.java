package cf.brforgers.mods.DragonScalesEX.common;

import cf.brforgers.api.DragonScalesEX.DragonScalesOldAPI;
import cf.brforgers.core.lib.FastFactory;
import cf.brforgers.core.lib.ItemHelper;
import cf.brforgers.core.lib.RegisterHelper;
import cf.brforgers.mods.DragonScalesEX.DragonScalesEX;
import cf.brforgers.mods.DragonScalesEX.Lib;
import cf.brforgers.mods.DragonScalesEX.common.general.blocks.BlockCauldronConstruct;
import cf.brforgers.mods.DragonScalesEX.common.general.blocks.BlockDragonCrystal;
import cf.brforgers.mods.DragonScalesEX.common.general.blocks.BlockModCauldron;
import cf.brforgers.mods.DragonScalesEX.common.general.blocks.tile.TileCauldronConstruct;
import cf.brforgers.mods.DragonScalesEX.common.general.blocks.tile.TileCrystal;
import cf.brforgers.mods.DragonScalesEX.common.general.items.*;
import cf.brforgers.mods.DragonScalesEX.common.virus.blocks.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static cf.brforgers.mods.DragonScalesEX.common.DSEX.*;
import static net.minecraft.block.material.Material.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

/**
 * Dragon Scales EX Manager
 */
public class DSEXManager {
    public static final FastFactory factory = FastFactory.newFactory(DragonScalesEX.tabDragonScales, Lib.MODID, ROCK);
    public static final RegisterHelper register = RegisterHelper.fromMod(Lib.MODID);

    private static void registerMaterialHandling() {
        DRAGONMETAL_TOOL_MATERIAL.setRepairItem(ItemHelper.toStack(DRAGON_METAL));
        DRAGONSCALES_ARMOR_MATERIAL.customCraftingMaterial = DRAGON_SCALE;
    }

	public static void registerBlocks() {
        DRAGON_BRICKS = factory.newBlock("DRAGON_BRICKS").setHardness(2.0F).setResistance(10.0F);//setStepSound(DRAGON_BRICKS.soundTypePiston);

        register.register(DRAGON_BRICKS);

        DRAGON_SCALE_BLOCK = factory.newBlock("DRAGON_SCALE_BLOCK").setHardness(0.8F);//.setStepSound(DRAGON_SCALE_BLOCK.soundTypeCloth);
        register.register(DRAGON_SCALE_BLOCK);

        //dragonEssenceOre = ModBlock.process(new BlockDragonEssenceOre().setHardness(3.0F).setResistance(5.0F).setStepSound(DRAGON_BRICKS.soundTypePiston), "dragonEssenceOre");
        //register.register(dragonEssenceOre, "dragonEssenceOre");

        CAULDRON = new BlockModCauldron();
        register.register(CAULDRON);

        CAULDRON_CONSTRUCT = new BlockCauldronConstruct();
        register.register(CAULDRON_CONSTRUCT);
        GameRegistry.registerTileEntity(TileCauldronConstruct.class, "Tile" + Lib.MODID + "ModelCauldronConstruct");

        DRAGON_CRYSTAL = factory.processBlock(new BlockDragonCrystal(), "DRAGON_CRYSTAL");
        register.register(DRAGON_CRYSTAL);
        GameRegistry.registerTileEntity(TileCrystal.class, "Tile"+Lib.MODID+"DragonCrystal");

        DRAGON_STONE = factory.processBlock(new BlockVirusBase(ROCK), "DRAGON_STONE");
        register.register(DRAGON_STONE);

        DRAGON_DIRT = factory.processBlock(new BlockVirusBase(GROUND).setHardness(0.6F).setHardness(0.5F), "DRAGON_DIRT");
        register.register(DRAGON_DIRT);

        DRAGON_GRASS = factory.processBlock(new DragonGrass(), "DRAGON_GRASS");
        register.register(DRAGON_GRASS);

        DRACONY_LEAVES = factory.processBlock(new DraconyLeaves(), "DRACONY_LEAVES");
        register.register(DRACONY_LEAVES);

        DRACONY_LOG = factory.processBlock(new DraconyLog(), "DRACONY_LOG");
        register.register(DRACONY_LOG);

        DRACONY_SAPLING = factory.processBlock(new DraconySapling(), "DRACONY_SAPLING");
        register.register(DRACONY_SAPLING);

        DRACONY_PLANKS = factory.processBlock(new BlockVirusBase(WOOD).setHardness(2.0F).setResistance(5.0F), "DRACONY_PLANKS");
        register.register(DRACONY_PLANKS);

        DRAGON_ESSENCE_BLOCK = factory.processBlock(new BlockVirusBase(IRON).setHardness(5.0F).setResistance(10.0F), "DRAGON_ESSENCE_BLOCK");
        register.register(DRAGON_ESSENCE_BLOCK);
    }

	public static void registerItems()
	{
        DRAGON_SCALE = factory.processItem(new ItemDragonScale(new ItemStack(Items.LEATHER)), "DRAGON_SCALE");
        register.register(DRAGON_SCALE);
        DRAGON_ESSENCE_SHARD = factory.processItem(new ItemDragonScale(null), "DRAGON_ESSENCE_SHARD");
        register.register(DRAGON_ESSENCE_SHARD);
        DRAGON_METAL = factory.processItem(new ItemDragonScale(new ItemStack(Items.IRON_INGOT)), "DRAGON_METAL");
        register.register(DRAGON_METAL);
        DRAGON_ESSENCE_BOTTLE = factory.processItem(new ItemEssenceBottle(new ItemStack(Items.GLASS_BOTTLE)), "DRAGON_ESSENCE_BOTTLE");
        register.register(DRAGON_ESSENCE_BOTTLE);
        INFUSED_STICK = factory.newItem("INFUSED_STICK");
        register.register(INFUSED_STICK);

        DRAGON_SWORD = factory.processItem(new ItemDragonSword(DRAGONMETAL_TOOL_MATERIAL), "DRAGON_SWORD");
        register.register(DRAGON_SWORD);
        DRAGON_MULTI = factory.processItem(new ItemDragonMulti(DRAGONMETAL_TOOL_MATERIAL), "DRAGON_MULTI");
        register.register(DRAGON_MULTI);

        DragonScalesOldAPI.setMultitoolCustomSpeed(Blocks.OBSIDIAN, 10f);

        DRAGON_SCEPTER = factory.processItem(new ItemDragonScepter(DRAGONMETAL_TOOL_MATERIAL), "DRAGON_SCEPTER");
        register.register(DRAGON_SCEPTER);

        SCALES_HELM = factory.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, HEAD), "SCALES_HELM");
        register.register(SCALES_HELM);
        SCALES_CHESTPLATE = factory.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, CHEST), "SCALES_CHESTPLATE");
        register.register(SCALES_CHESTPLATE);
        SCALES_LEGGINGS = factory.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, LEGS), "SCALES_LEGGINGS");
        register.register(SCALES_LEGGINGS);
        SCALES_BOOTS = factory.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, FEET), "SCALES_BOOTS");
        register.register(SCALES_BOOTS);
    }
}
