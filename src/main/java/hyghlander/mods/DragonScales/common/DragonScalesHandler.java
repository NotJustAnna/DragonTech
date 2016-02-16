package hyghlander.mods.DragonScales.common;

import brazillianforgers.lib.EntityHelper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import hyghlander.mods.DragonScales.DragonScales;
import hyghlander.mods.DragonScales.Lib;
import hyghlander.mods.DragonScales.common.blocks.*;
import hyghlander.mods.DragonScales.common.blocks.tile.TileEntityDragonChest;
import hyghlander.mods.DragonScales.common.blocks.tile.TileEntityDragonCrystal;
import hyghlander.mods.DragonScales.common.items.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Dragon Scales Main Handler
 */
public class DragonScalesHandler {
	public static final ToolMaterial DRAGONALLOY_TOOL_MATERIAL = 
			EnumHelper.addToolMaterial("DRAGONSCALE", 10, 2500, 50.0F, 15.0F, 35);
	public static final ArmorMaterial DRAGONSCALES_ARMOR_MATERIAL = 
			EnumHelper.addArmorMaterial("DRAGONSCALE", 1000, new int[] { 50, 80, 60, 30 }, 35);
	
	// All Items
	public static Item dragonScale, tinyDragonScale, dragonPlate, dragonBrick, dragonSword, dragonMultiTool, dragonScepter, scalesHelm,  scalesChestplate, scalesLeggings, scalesBoots;
	
	// All Blocks
	public static Block dragonBricks, dragonChest, dragonScaleBlock, dragonScaleOre, dragonCrystal;
	
	public static void registerAll()
	{
		registerBlocks();
		registerItems();
		registerEntities();
		GameRegistry.registerWorldGenerator(new DragonScalesWorldGenerator(), 1);
	}

	private static void registerBlocks()
	{
		//Define ModBlock pattern
		ModBlock.set(DragonScales.tabDragonScales, Lib.MODID, Material.rock);
		
		dragonBricks = new ModBlock("dragonBricks");
		GameRegistry.registerBlock(dragonBricks, "dragonBricks");
		
		dragonScaleBlock = new ModBlock("dragonScaleBlock");
		GameRegistry.registerBlock(dragonScaleBlock, "dragonScaleBlock");
		
		dragonScaleOre = ModBlock.process(new BlockDragonScaleOre(), "dragonScaleOre");
		GameRegistry.registerBlock(dragonScaleOre, "dragonScaleOre");
		
		//dragonChest = ModBlock.process(new BlockDragonChest(), "dragonChest");
		//GameRegistry.registerBlock(dragonChest, ItemBlock.class, "dragonChest");
		//GameRegistry.registerTileEntity(TileEntityDragonChest.class, "dragonchestTileEntity");
		
		dragonCrystal = ModBlock.process(new BlockDragonCrystal(), "dragonCrystal");
		GameRegistry.registerBlock(dragonCrystal, "dragonCrystal");
		GameRegistry.registerTileEntity(TileEntityDragonCrystal.class, "Tile"+Lib.MODID+"DragonCrystal");
	}
	
	private static void registerItems()
	{
		ModItem.set(DragonScales.tabDragonScales, Lib.MODID);

		
		dragonScale = new ModItem("dragonScale");
		GameRegistry.registerItem(dragonScale, "dragonScale");
		tinyDragonScale = new ModItem("tinyDragonScale");
		GameRegistry.registerItem(tinyDragonScale, "tinyDragonScale");
		dragonBrick = new ModItem("dragonBrick");
		GameRegistry.registerItem(dragonBrick, "dragonBrick");
		
		dragonSword = ModItem.process(new ItemDragonSword(DRAGONALLOY_TOOL_MATERIAL), "dragonSword");
		GameRegistry.registerItem(dragonSword, "dragonSword");
		dragonMultiTool = ModItem.process(new ItemDragonMulti(DRAGONALLOY_TOOL_MATERIAL), "dragonMultiTool");
		GameRegistry.registerItem(dragonMultiTool, "dragonMultiTool");
		dragonScepter = ModItem.process(new ItemDragonScepter(), "dragonScepter");
		GameRegistry.registerItem(dragonScepter, "dragonScepter");
		
		scalesHelm = ModItem.process(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, 0, "scalesHelm"), "scalesHelm");
		GameRegistry.registerItem(scalesHelm, "scalesHelm");
		scalesChestplate = ModItem.process(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, 1, "scalesChestplate"), "scalesChestplate");
		GameRegistry.registerItem(scalesChestplate, "scalesChestplate");
		scalesLeggings = ModItem.process(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, 2, "scalesLeggings"), "scalesLeggings");
		GameRegistry.registerItem(scalesLeggings, "scalesLeggings");
		scalesBoots = ModItem.process(new ItemDragonArmor(DRAGONSCALES_ARMOR_MATERIAL, 3, "scalesBoots"), "scalesBoots");
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
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonScaleBlock,1),
				"DDD","DDD","DDD",
				'D', dragonScale
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonMultiTool,1),
				"DDD","DSD"," S ",
				'D', dragonScale,
				'S', Items.stick
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonSword,1), 
				" D "," D "," S ",
				'D', dragonScale,
				'S', Items.stick
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(scalesHelm,1), 
				"DDD","D D","   ",
				'D', dragonScale,
				'S', Items.diamond
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(scalesChestplate,1), 
				"D D","DSD","DDD",
				'D', dragonScale,
				'S', Items.diamond
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(scalesLeggings,1), 
				"DDD","D D","D D",
				'D', dragonScale,
				'S', Items.diamond
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(scalesBoots,1), 
				"   ","D D","D D",
				'D', dragonScale,
				'S', Items.diamond
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonCrystal,1), 
				" D "," D ","DFD",
				'D', tinyDragonScale,
				'F', dragonScale
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonScepter,1), 
				"  F"," D ","S  ",
				'D', dragonScale,
				'F', Items.diamond,
				'S', Items.blaze_rod
		);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(dragonBricks,1), 
				"DDD","DDD","DDD",
				'D', dragonBrick
		);
		
		GameRegistry.addSmelting(tinyDragonScale, new ItemStack(dragonBrick), 0.1f);
		
		GameRegistry.addShapelessRecipe(
				new ItemStack(dragonScale,9), 
				dragonScaleBlock
		);

		GameRegistry.addShapelessRecipe(
				new ItemStack(tinyDragonScale, 4), 
				dragonScale
		);

		GameRegistry.addShapedRecipe(
				new ItemStack(dragonScale,1),
				"DD","DD",
				'D', tinyDragonScale
		);
	}
}
