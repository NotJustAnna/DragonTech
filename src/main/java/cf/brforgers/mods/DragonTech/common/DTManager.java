package cf.brforgers.mods.DragonTech.common;

import cf.brforgers.api.DragonTech.DragonTechAPI;
import cf.brforgers.core.lib.ez.ItemHelper;
import cf.brforgers.core.lib.ez.mods.FastFactory;
import cf.brforgers.core.lib.ez.mods.ModRegister;
import cf.brforgers.mods.DragonTech.DragonTech;
import cf.brforgers.mods.DragonTech.Lib;
import cf.brforgers.mods.DragonTech.common.general.blocks.BlockCauldronConstruct;
import cf.brforgers.mods.DragonTech.common.general.blocks.BlockModCauldron;
import cf.brforgers.mods.DragonTech.common.general.blocks.tile.TileCauldronConstruct;
import cf.brforgers.mods.DragonTech.common.general.items.*;
import cf.brforgers.mods.DragonTech.common.virus.blocks.BlockVirusBase;
import cf.brforgers.mods.DragonTech.common.virus.blocks.DraconyLeaves;
import cf.brforgers.mods.DragonTech.common.virus.blocks.DraconyLog;
import cf.brforgers.mods.DragonTech.common.virus.blocks.DragonGrass;
import cf.brforgers.mods.DragonTech.common.world.blocks.BlockDragonCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static cf.brforgers.mods.DragonTech.common.DT.*;
import static net.minecraft.block.material.Material.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

/**
 * DragonTech Manager
 */
public class DTManager {
    public static final FastFactory FACTORY = FastFactory.newFactory(Lib.MOD, DragonTech.tabDragonTech, ROCK);
    public static final ModRegister REGISTER = ModRegister.fromMod(Lib.MOD);

    private static void registerMaterialHandling() {
        DRAGONMETAL_TOOL_MATERIAL.setRepairItem(ItemHelper.toStack(DRAGON_METAL));
        DRAGONSCALES_ARMOR_MATERIAL.customCraftingMaterial = DRAGON_SCALE;
    }

	public static void registerBlocks() {
        DRAGON_BRICKS = FACTORY.newBlock("DRAGON_BRICKS").setHardness(2.0F).setResistance(10.0F);//setStepSound(DRAGON_BRICKS.soundTypePiston);

        REGISTER.register(DRAGON_BRICKS);

        DRAGON_SCALE_BLOCK = FACTORY.newBlock("DRAGON_SCALE_BLOCK").setHardness(0.8F);//.setStepSound(DRAGON_SCALE_BLOCK.soundTypeCloth);
        REGISTER.register(DRAGON_SCALE_BLOCK);

        //dragonEssenceOre = ModBlock.process(new BlockDragonEssenceOre().setHardness(3.0F).setResistance(5.0F).setStepSound(DRAGON_BRICKS.soundTypePiston), "dragonEssenceOre");
        //REGISTER.REGISTER(dragonEssenceOre, "dragonEssenceOre");

        CAULDRON = new BlockModCauldron().setRegistryName(REGISTER.MOD.getLocation("cauldron"));
        REGISTER.register(CAULDRON);

        CAULDRON_CONSTRUCT = new BlockCauldronConstruct();
        REGISTER.register(CAULDRON_CONSTRUCT);
        GameRegistry.registerTileEntity(TileCauldronConstruct.class, "Tile" + Lib.MODID + "ModelCauldronConstruct");

        DRAGON_CRYSTAL = FACTORY.processBlock(new BlockDragonCrystal(), "DRAGON_CRYSTAL");
        REGISTER.register(DRAGON_CRYSTAL);

        DRAGON_STONE = FACTORY.processBlock(new BlockVirusBase(ROCK), "DRAGON_STONE");
        REGISTER.register(DRAGON_STONE);

        DRAGON_DIRT = FACTORY.processBlock(new BlockVirusBase(GROUND).setHardness(0.6F).setHardness(0.5F), "DRAGON_DIRT");
        REGISTER.register(DRAGON_DIRT);

        DRAGON_GRASS = FACTORY.processBlock(new DragonGrass(), "DRAGON_GRASS");
        REGISTER.register(DRAGON_GRASS);

        DRACONY_LEAVES = FACTORY.processBlock(new DraconyLeaves(), "DRACONY_LEAVES");
        REGISTER.register(DRACONY_LEAVES);

        DRACONY_LOG = FACTORY.processBlock(new DraconyLog(), "DRACONY_LOG");
        REGISTER.register(DRACONY_LOG);

        //DRACONY_SAPLING = FACTORY.processBlock(new DraconySapling(), "DRACONY_SAPLING");
        //REGISTER.register(DRACONY_SAPLING);

        DRACONY_PLANKS = FACTORY.processBlock(new BlockVirusBase(WOOD).setHardness(2.0F).setResistance(5.0F), "DRACONY_PLANKS");
        REGISTER.register(DRACONY_PLANKS);

        DRAGON_ESSENCE_BLOCK = FACTORY.processBlock(new BlockVirusBase(IRON).setHardness(5.0F).setResistance(10.0F), "DRAGON_ESSENCE_BLOCK");
        REGISTER.register(DRAGON_ESSENCE_BLOCK);
    }

	public static void registerItems()
	{
        DRAGON_SCALE = FACTORY.processItem(new ItemDragonScale(new ItemStack(Items.LEATHER)), "DRAGON_SCALE");
        REGISTER.register(DRAGON_SCALE);
        DRAGON_ESSENCE_SHARD = FACTORY.processItem(new ItemDragonScale(null), "DRAGON_ESSENCE_SHARD");
        REGISTER.register(DRAGON_ESSENCE_SHARD);
        DRAGON_METAL = FACTORY.processItem(new ItemDragonScale(new ItemStack(Items.IRON_INGOT)), "DRAGON_METAL");
        REGISTER.register(DRAGON_METAL);
        DRAGON_ESSENCE_BOTTLE = FACTORY.processItem(new ItemEssenceBottle(new ItemStack(Items.GLASS_BOTTLE)), "DRAGON_ESSENCE_BOTTLE");
        REGISTER.register(DRAGON_ESSENCE_BOTTLE);
        INFUSED_STICK = FACTORY.newItem("INFUSED_STICK");
        REGISTER.register(INFUSED_STICK);

        DRAGON_SWORD = FACTORY.processItem(new ItemDragonSword(DRAGONMETAL_TOOL_MATERIAL), "DRAGON_SWORD");
        REGISTER.register(DRAGON_SWORD);
        DRAGON_MULTI = FACTORY.processItem(new ItemDragonMulti(DRAGONMETAL_TOOL_MATERIAL), "DRAGON_MULTI");
        REGISTER.register(DRAGON_MULTI);

        DragonTechAPI.setCustomSpeedForBlock(Blocks.OBSIDIAN, 10f);

        DRAGON_SCEPTER = FACTORY.processItem(new ItemDragonScepter(DRAGONMETAL_TOOL_MATERIAL), "DRAGON_SCEPTER");
        REGISTER.register(DRAGON_SCEPTER);

        SCALES_HELM = FACTORY.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, HEAD), "SCALES_HELM");
        REGISTER.register(SCALES_HELM);
        SCALES_CHESTPLATE = FACTORY.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, CHEST), "SCALES_CHESTPLATE");
        REGISTER.register(SCALES_CHESTPLATE);
        SCALES_LEGGINGS = FACTORY.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, LEGS), "SCALES_LEGGINGS");
        REGISTER.register(SCALES_LEGGINGS);
        SCALES_BOOTS = FACTORY.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, FEET), "SCALES_BOOTS");
        REGISTER.register(SCALES_BOOTS);
    }
}
