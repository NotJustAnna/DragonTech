package cf.brforgers.mods.DragonTech.common;

import cf.brforgers.api.DragonTech.DragonTechAPI;
import cf.brforgers.core.lib.ez.ItemHelper;
import cf.brforgers.core.lib.ez.mods.FastFactory;
import cf.brforgers.core.lib.ez.mods.ModRegister;
import cf.brforgers.mods.DragonTech.DragonTech;
import cf.brforgers.mods.DragonTech.Lib;
import cf.brforgers.mods.DragonTech.common.general.blocks.BlockModCauldron;
import cf.brforgers.mods.DragonTech.common.general.items.*;
import cf.brforgers.mods.DragonTech.common.virus.blocks.BlockVirusBase;
import cf.brforgers.mods.DragonTech.common.virus.blocks.DraconyLeaves;
import cf.brforgers.mods.DragonTech.common.virus.blocks.DraconyLog;
import cf.brforgers.mods.DragonTech.common.virus.blocks.DragonGrass;
import cf.brforgers.mods.DragonTech.common.world.blocks.BlockDragonCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

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
        DRAGON_BRICKS = FACTORY.newBlock("dragon_bricks").setHardness(2.0F).setResistance(10.0F);//setStepSound(DRAGON_BRICKS.soundTypePiston);

        REGISTER.register(DRAGON_BRICKS);
        REGISTER.registerItemBlock(DRAGON_BRICKS);

        DRAGON_SCALE_BLOCK = FACTORY.newBlock("dragon_scale_block").setHardness(0.8F);//.setStepSound(DRAGON_SCALE_BLOCK.soundTypeCloth);
        REGISTER.register(DRAGON_SCALE_BLOCK);
        REGISTER.registerItemBlock(DRAGON_SCALE_BLOCK);

        //dragonEssenceOre = ModBlock.process(new BlockDragonEssenceOre().setHardness(3.0F).setResistance(5.0F).setStepSound(DRAGON_BRICKS.soundTypePiston), "dragonEssenceOre");
        //REGISTER.REGISTER(dragonEssenceOre, "dragonEssenceOre");

        CAULDRON = new BlockModCauldron().setRegistryName(REGISTER.MOD.getLocation("cauldron"));
        REGISTER.register(CAULDRON);
        //no ItemBlock for you u.u

        //CAULDRON_CONSTRUCT = new BlockCauldronConstruct();
        //REGISTER.register(CAULDRON_CONSTRUCT);
        //GameRegistry.registerTileEntity(TileCauldronConstruct.class, "Tile" + Lib.MODID + "ModelCauldronConstruct");

        DRAGON_CRYSTAL = FACTORY.processBlock(new BlockDragonCrystal(), "dragon_crystal");
        REGISTER.register(DRAGON_CRYSTAL);
        REGISTER.registerItemBlock(DRAGON_CRYSTAL);

        DRAGON_STONE = FACTORY.processBlock(new BlockVirusBase(ROCK), "dragon_stone");
        REGISTER.register(DRAGON_STONE);
        REGISTER.registerItemBlock(DRAGON_STONE);

        DRAGON_DIRT = FACTORY.processBlock(new BlockVirusBase(GROUND).setHardness(0.6F).setHardness(0.5F), "dragon_dirt");
        REGISTER.register(DRAGON_DIRT);
        REGISTER.registerItemBlock(DRAGON_DIRT);

        DRAGON_GRASS = FACTORY.processBlock(new DragonGrass(), "dragon_grass");
        REGISTER.register(DRAGON_GRASS);
        REGISTER.registerItemBlock(DRAGON_GRASS);

        DRACONY_LEAVES = FACTORY.processBlock(new DraconyLeaves(), "dracony_leaves");
        REGISTER.register(DRACONY_LEAVES);
        REGISTER.registerItemBlock(DRACONY_LEAVES);

        DRACONY_LOG = FACTORY.processBlock(new DraconyLog(), "dracony_log");
        REGISTER.register(DRACONY_LOG);
        REGISTER.registerItemBlock(DRACONY_LOG);

        //DRACONY_SAPLING = FACTORY.processBlock(new DraconySapling(), "dracony_sapling");
        //REGISTER.register(DRACONY_SAPLING);

        DRACONY_PLANKS = FACTORY.processBlock(new BlockVirusBase(WOOD).setHardness(2.0F).setResistance(5.0F), "dracony_planks");
        REGISTER.register(DRACONY_PLANKS);
        REGISTER.registerItemBlock(DRACONY_PLANKS);

        DRAGON_ESSENCE_BLOCK = FACTORY.processBlock(new BlockVirusBase(IRON).setHardness(5.0F).setResistance(10.0F), "dragon_essence_block");
        REGISTER.register(DRAGON_ESSENCE_BLOCK);
        REGISTER.registerItemBlock(DRAGON_ESSENCE_BLOCK);
    }

	public static void registerItems()
	{
        DRAGON_SCALE = FACTORY.processItem(new ItemDragonScale(new ItemStack(Items.LEATHER)), "dragon_scale");
        REGISTER.register(DRAGON_SCALE);
        DRAGON_ESSENCE_SHARD = FACTORY.processItem(new ItemDragonScale(null), "dragon_essence_shard");
        REGISTER.register(DRAGON_ESSENCE_SHARD);
        DRAGON_METAL = FACTORY.processItem(new ItemDragonScale(new ItemStack(Items.IRON_INGOT)), "dragon_metal");
        REGISTER.register(DRAGON_METAL);
        DRAGON_ESSENCE_BOTTLE = FACTORY.processItem(new ItemEssenceBottle(new ItemStack(Items.GLASS_BOTTLE)), "dragon_essence_bottle");
        REGISTER.register(DRAGON_ESSENCE_BOTTLE);
        INFUSED_STICK = FACTORY.newItem("infused_stick");
        REGISTER.register(INFUSED_STICK);

        DRAGON_SWORD = FACTORY.processItem(new ItemDragonSword(DRAGONMETAL_TOOL_MATERIAL), "dragon_sword");
        REGISTER.register(DRAGON_SWORD);
        DRAGON_MULTI = FACTORY.processItem(new ItemDragonMulti(DRAGONMETAL_TOOL_MATERIAL), "dragon_multi");
        REGISTER.register(DRAGON_MULTI);

        DragonTechAPI.setCustomSpeedForBlock(Blocks.OBSIDIAN, 10f);

        DRAGON_SCEPTER = FACTORY.processItem(new ItemDragonScepter(DRAGONMETAL_TOOL_MATERIAL), "dragon_scepter");
        REGISTER.register(DRAGON_SCEPTER);

        SCALES_HELM = FACTORY.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, HEAD), "scales_helm");
        REGISTER.register(SCALES_HELM);
        SCALES_CHESTPLATE = FACTORY.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, CHEST), "scales_chestplate");
        REGISTER.register(SCALES_CHESTPLATE);
        SCALES_LEGGINGS = FACTORY.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, LEGS), "scales_leggings");
        REGISTER.register(SCALES_LEGGINGS);
        SCALES_BOOTS = FACTORY.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, FEET), "scales_boots");
        REGISTER.register(SCALES_BOOTS);
    }
}
