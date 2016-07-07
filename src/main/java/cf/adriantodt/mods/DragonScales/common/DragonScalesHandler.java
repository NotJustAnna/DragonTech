package cf.adriantodt.mods.DragonScales.common;

import cf.brforgers.core.lib.FastFactory;
import cf.brforgers.core.lib.ItemHelper;

import javax.xml.stream.FactoryConfigurationError;

import cf.adriantodt.api.DragonScales.DragonScalesAPI;
import cf.adriantodt.api.DragonScales.DragonScalesAPI.CauldronRecipe;
import cf.adriantodt.mods.DragonScales.DragonScales;
import cf.adriantodt.mods.DragonScales.Lib;
import cf.adriantodt.mods.DragonScales.common.blocks.*;
import cf.adriantodt.mods.DragonScales.common.blocks.tile.TileCauldronConstruct;
import cf.adriantodt.mods.DragonScales.common.blocks.tile.TileCombiner;
import cf.adriantodt.mods.DragonScales.common.blocks.tile.TileCrystal;
import cf.adriantodt.mods.DragonScales.common.blocks.tile.TileEntityDragonChest;
import cf.adriantodt.mods.DragonScales.common.blocks.world.DraconyLeaves;
import cf.adriantodt.mods.DragonScales.common.blocks.world.DraconyLog;
import cf.adriantodt.mods.DragonScales.common.blocks.world.DraconySapling;
import cf.adriantodt.mods.DragonScales.common.blocks.world.DragonGrass;
import cf.adriantodt.mods.DragonScales.common.items.*;
import cf.adriantodt.mods.DragonScales.common.world.DragonScalesWorldGenerator;
import cf.adriantodt.mods.DragonScales.common.world.WorldGenTree;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Dragon Scales Main Handler
 */
public class DragonScalesHandler {
	public static final ToolMaterial DRAGONALLOY_TOOL_MATERIAL = 
			EnumHelper.addToolMaterial("DRAGONSCALE", 10, 2000, 50.0F, 16.0F, 35);
	public static final ArmorMaterial DRAGONSCALES_ARMOR_MATERIAL = 
			EnumHelper.addArmorMaterial("DRAGONSCALE", 50, new int[] { 5, 16, 12, 6 }, 35);
	public static FastFactory factory;
	
	// All Items
	public static Item dragonScale, dragonEssenceShard, dragonMetal, dragonEssenceBottle, infusedStick,
		dragonSword, dragonMultiTool, dragonScepter;
	
	public static ItemArmor scalesHelm, scalesChestplate, scalesLeggings, scalesBoots;
	
	// All Blocks
	public static Block modCauldron, cauldronConstruct, essenceCombiner, dragonBricks, dragonChest, dragonScaleBlock,
		dragonCrystal, dragonGrass, dragonDirt, dragonStone, draconyLeaves, draconyLog, draconySapling, draconyPlanks;
	
	public static void registerAll()
	{
		factory = FastFactory.newFactory(DragonScales.tabDragonScales, Lib.TEXTURE_PATH, Material.rock);
		
		registerBlocks();
		registerItems();
		registerMaterialHandling();
		registerEntities();
		GameRegistry.registerWorldGenerator(new DragonScalesWorldGenerator(), 20);
	}
	
	private static void registerMaterialHandling() {
		DRAGONALLOY_TOOL_MATERIAL.setRepairItem(ItemHelper.toStack(dragonMetal));
		DRAGONSCALES_ARMOR_MATERIAL.customCraftingMaterial = dragonScale;
	}

	private static void registerBlocks()
	{		
		dragonBricks = factory.newBlock("dragonBricks").setHardness(2.0F).setResistance(10.0F).setStepSound(dragonBricks.soundTypePiston);
		GameRegistry.registerBlock(dragonBricks, "dragonBricks");
		
		dragonScaleBlock = factory.newBlock("dragonScaleBlock").setHardness(0.8F).setStepSound(dragonScaleBlock.soundTypeCloth);
		GameRegistry.registerBlock(dragonScaleBlock, "dragonScaleBlock");
		
		//dragonEssenceOre = ModBlock.process(new BlockDragonEssenceOre().setHardness(3.0F).setResistance(5.0F).setStepSound(dragonBricks.soundTypePiston), "dragonEssenceOre");
		//GameRegistry.registerBlock(dragonEssenceOre, "dragonEssenceOre");
		
		modCauldron = new BlockModCauldron();
		GameRegistry.registerBlock(modCauldron, "modCauldron");
		
		cauldronConstruct = new BlockCauldronConstruct();
		GameRegistry.registerBlock(cauldronConstruct, "cauldronConstruct");
		GameRegistry.registerTileEntity(TileCauldronConstruct.class, "Tile"+Lib.MODID+"ModelCauldronConstruct");
		
		essenceCombiner = new BlockCombiner();
		GameRegistry.registerBlock(essenceCombiner, "essenceCombiner");
		GameRegistry.registerTileEntity(TileCombiner.class, "Tile"+Lib.MODID+"Combiner");
		
		//dragonChest = ModBlock.process(new BlockDragonChest(), "dragonChest");
		//GameRegistry.registerBlock(dragonChest, ItemBlock.class, "dragonChest");
		//GameRegistry.registerTileEntity(TileEntityDragonChest.class, "dragonchestTileEntity");
		
		dragonCrystal = factory.processBlock(new BlockDragonCrystal(), "dragonCrystal");
		GameRegistry.registerBlock(dragonCrystal, "dragonCrystal");
		GameRegistry.registerTileEntity(TileCrystal.class, "Tile"+Lib.MODID+"DragonCrystal");
		
		dragonStone = factory.newBlock("dragonStone");
		GameRegistry.registerBlock(dragonStone, "dragonStone");
		
		factory.defaultMaterial = Material.ground;
		dragonDirt = factory.newBlock("dragonDirt");
		GameRegistry.registerBlock(dragonDirt, "dragonDirt");
		factory.defaultMaterial = Material.rock;
		
		dragonGrass = factory.processBlock(new DragonGrass(), "dragonGrass");
		GameRegistry.registerBlock(dragonGrass, "dragonGrass");
		
		draconyLeaves = factory.processBlock(new DraconyLeaves(), "draconyLeaves");
		GameRegistry.registerBlock(draconyLeaves, "draconyLeaves");
		
		draconyLog = factory.processBlock(new DraconyLog(), "draconyLog");
		GameRegistry.registerBlock(draconyLog, "draconyLog");
		
		draconySapling = factory.processBlock(new DraconySapling(), "draconySapling");
		GameRegistry.registerBlock(draconySapling, "draconySapling");
		
		factory.defaultMaterial = Material.wood;
		draconyPlanks = factory.newBlock("draconyPlanks").setHardness(2.0F).setResistance(5.0F).setStepSound(draconyLog.soundTypeWood);
		GameRegistry.registerBlock(draconyPlanks, "draconyPlanks");
		factory.defaultMaterial = Material.rock;
	}
	
	private static void registerItems()
	{
		dragonScale = factory.processItem(new ItemDragonScale(new ItemStack(Items.leather)), "dragonScale");
		GameRegistry.registerItem(dragonScale, "dragonScale");
		dragonEssenceShard = factory.processItem(new ItemDragonScale(null),"dragonEssenceShard");
		GameRegistry.registerItem(dragonEssenceShard, "dragonEssenceShard");
		dragonMetal = factory.processItem(new ItemDragonScale(new ItemStack(Items.iron_ingot)),"dragonMetal");
		GameRegistry.registerItem(dragonMetal, "dragonMetal");
		dragonEssenceBottle = factory.processItem(new ItemEssenceBottle(new ItemStack(Items.glass_bottle)),"dragonEssenceBottle");
		GameRegistry.registerItem(dragonEssenceBottle, "dragonEssenceBottle");
		infusedStick = factory.newItem("infusedStick");
		GameRegistry.registerItem(infusedStick, "infusedStick");
		
		dragonSword = factory.processItem(new ItemDragonSword(DRAGONALLOY_TOOL_MATERIAL), "dragonSword");
		GameRegistry.registerItem(dragonSword, "dragonSword");
		dragonMultiTool = factory.processItem(new ItemDragonMulti(DRAGONALLOY_TOOL_MATERIAL), "dragonMultiTool");
		GameRegistry.registerItem(dragonMultiTool, "dragonMultiTool");

		DragonScalesAPI.setCustomSpeed(Blocks.obsidian, 10f);
		
		dragonScepter = factory.processItem(new ItemDragonScepter(DRAGONALLOY_TOOL_MATERIAL), "dragonScepter");
		GameRegistry.registerItem(dragonScepter, "dragonScepter");
		
		scalesHelm = factory.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, 0, "scalesHelm"), "scalesHelm");
		GameRegistry.registerItem(scalesHelm, "scalesHelm");
		scalesChestplate = factory.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, 1, "scalesChestplate"), "scalesChestplate");
		GameRegistry.registerItem(scalesChestplate, "scalesChestplate");
		scalesLeggings = factory.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, 2, "scalesLeggings"), "scalesLeggings");
		GameRegistry.registerItem(scalesLeggings, "scalesLeggings");
		scalesBoots = factory.processItem(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, 3, "scalesBoots"), "scalesBoots");
		GameRegistry.registerItem(scalesBoots, "scalesBoots");
	}
	
	private static void registerEntities()
	{
		//int entityID = EntityRegistry.findGlobalUniqueEntityId();
		
		//EntityRegistry.registerGlobalEntityID(EntityModDragon.class, "ModDragon", entityID);
		//EntityRegistry.registerModEntity(EntityModDragon.class, "ModDragon", entityID, Lib.MODID, 1, 1, 1);
		//EntityRegistry.addSpawn(EntityModDragon.class, 2, 1, 2, EnumCreatureType.creature, BiomeGenBase.extremeHills);
		//EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, 0x000000, 0x000000));
	}
	
	public static void registerRecipes()
	{
		DragonScalesAPI.cauldronRecipes.add(new CauldronRecipe(new ItemStack(Items.leather), 3, new ItemStack(dragonScale)));
		DragonScalesAPI.cauldronRecipes.add(new CauldronRecipe(new ItemStack(Items.gold_ingot), 3, new ItemStack(dragonMetal)));
		DragonScalesAPI.cauldronRecipes.add(new CauldronRecipe(new ItemStack(Items.emerald), 3, new ItemStack(dragonCrystal)));
		DragonScalesAPI.cauldronRecipes.add(new CauldronRecipe(new ItemStack(Items.glass_bottle), 1, new ItemStack(dragonEssenceBottle)));
		DragonScalesAPI.cauldronRecipes.add(new CauldronRecipe(new ItemStack(Items.stick), 0, new ItemStack(infusedStick)));
		DragonScalesAPI.cauldronRecipes.add(
			new CauldronRecipe(new ItemStack(Blocks.brick_block), 1, new ItemStack(dragonBricks)) {
				public ItemStack getOutput(ItemStack input, int essentiaLevel) {
					ItemStack output = super.getOutput(input, essentiaLevel);
					output.stackSize = input.stackSize;
					return output;
				}
				
				public int getEssentiaCost(ItemStack input, int essentiaLevel)
				{
					return MathHelper.clamp_int((int)((float)(input.stackSize / 64) * 3)+1, 1, 3);
				}
				
				public int getItemCost(ItemStack input, int essentiaLevel)
				{
					return input.stackSize;
				}
			}
		);
		DragonScalesAPI.cauldronRecipes.add(
				new CauldronRecipe(new ItemStack(Blocks.soul_sand), 1, new ItemStack(Blocks.end_stone)) {
					public ItemStack getOutput(ItemStack input, int essentiaLevel) {
						ItemStack output = super.getOutput(input, essentiaLevel);
						output.stackSize = MathHelper.clamp_int(input.stackSize,0,16);
						return output;
					}
					
					public int getEssentiaCost(ItemStack input, int essentiaLevel)
					{
						return MathHelper.clamp_int((int)((float)(input.stackSize / 16) * 3)+1, 1, 3);
					}
					
					public int getItemCost(ItemStack input, int essentiaLevel)
					{
						return MathHelper.clamp_int(input.stackSize,0,16);
					}
				}
			);
		
		GameRegistry.addShapelessRecipe(new ItemStack(dragonEssenceBottle), new ItemStack(Items.potionitem,1,0), new ItemStack(dragonEssenceShard));
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonScaleBlock,1),
				"DDD","DDD","DDD",
				'D', dragonScale
		);
		
		GameRegistry.addShapelessRecipe(new ItemStack(dragonScale,9), dragonScaleBlock);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonMultiTool,1),
				"MMM","MSM","DSD",
				'M', dragonMetal,
				'S', infusedStick,
				'D', dragonScale
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonSword,1), 
				" M "," MD","DS ",
				'M', dragonMetal,
				'S', infusedStick,
				'D', dragonScale
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(scalesHelm,1), 
				"DDD","DED","   ",
				'D', dragonScale,
				'E', dragonEssenceBottle.setContainerItem(Items.glass_bottle)
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(scalesChestplate,1), 
				"DED","DDD","DDD",
				'D', dragonScale,
				'E', dragonEssenceBottle.setContainerItem(Items.glass_bottle)
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(scalesLeggings,1), 
				"DDD","DED","D D",
				'D', dragonScale,
				'E', dragonEssenceBottle.setContainerItem(Items.glass_bottle)
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(scalesBoots,1), 
				"E E","D D","D D",
				'D', dragonScale,
				'E', dragonEssenceBottle.setContainerItem(Items.glass_bottle)
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonScepter,1), 
				"MMD"," SD","DSE",
				'D', dragonScale,
				'M', dragonMetal,
				'S', infusedStick,
				'E', dragonEssenceBottle.setContainerItem(Items.glass_bottle)
		);
	}
}
