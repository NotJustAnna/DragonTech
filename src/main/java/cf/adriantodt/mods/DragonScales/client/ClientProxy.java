package cf.adriantodt.mods.DragonScales.client;

import cf.adriantodt.mods.DragonScales.client.models.ModelDragonChestplate;
import cf.adriantodt.mods.DragonScales.client.renderers.BlockModCauldronRenderer;
import cf.adriantodt.mods.DragonScales.client.renderers.ItemTileEntityRenderer;
import cf.adriantodt.mods.DragonScales.client.renderers.TileCauldronConstructRenderer;
import cf.adriantodt.mods.DragonScales.client.renderers.TileCrystalRenderer;
import cf.adriantodt.mods.DragonScales.common.CommonProxy;
import cf.adriantodt.mods.DragonScales.common.DragonScalesHandler;
import cf.adriantodt.mods.DragonScales.common.blocks.BlockModCauldron;
import cf.adriantodt.mods.DragonScales.common.blocks.tile.TileCauldronConstruct;
import cf.adriantodt.mods.DragonScales.common.blocks.tile.TileCrystal;
import cf.brforgers.core.lib.client.Armor3DRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	private static final ModelBiped dragonChestplate = new ModelDragonChestplate(1.0f);
	private static final ModelBiped dragonLeggings = new ModelBiped(0.45f);
	private static final ModelBiped dragonBoots = new ModelBiped(0.9f);
	
	private static int cauldronRenderType = -1;
	
	public void preInit(){
		super.preInit();
		
		//Tweak to Remove the Cauldron from NEI
		if (Loader.isModLoaded("NotEnoughItems"));
		codechicken.nei.api.API.hideItem(new ItemStack(DragonScalesHandler.modCauldron));
	}
	
	public void registerRenderThings(){
		//Register TileEntity Renderers
		ClientRegistry.bindTileEntitySpecialRenderer(TileCrystal.class, new TileCrystalRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCauldronConstruct.class, new TileCauldronConstructRenderer());
		
		
		//Tweak to Render at Inventory
		ItemTileEntityRenderer.newItemTileRenderer(DragonScalesHandler.dragonCrystal);
		ItemTileEntityRenderer.newItemTileRenderer(DragonScalesHandler.cauldronConstruct);
		
		//Register Cauldron Renderer
		cauldronRenderType = RenderingRegistry.getNextAvailableRenderId();
		ISimpleBlockRenderingHandler cauldronRenderer = new BlockModCauldronRenderer();
		RenderingRegistry.registerBlockHandler(cauldronRenderType, cauldronRenderer);
		
		//Register the Armor
		Armor3DRenderer.Register(DragonScalesHandler.scalesBoots);
		Armor3DRenderer.Register(DragonScalesHandler.scalesChestplate);
		Armor3DRenderer.Register(DragonScalesHandler.scalesHelm);
		Armor3DRenderer.Register(DragonScalesHandler.scalesLeggings);
		
		//Register Dragon Renderer
		//RenderingRegistry.registerEntityRenderingHandler(EntityModDragon.class, new RenderModDragon(new ModelModDragon(), 0.5F));
	}
	
	@Override
	public ModelBiped getArmorModel(int id){
		switch (id) {
		case 0:
			return dragonBoots;
		case 1:
			return dragonChestplate;
		case 2:
			return dragonLeggings;
		case 3:
			return dragonBoots;
		}
		return dragonLeggings;
	}
	
	@Override
	public int getRenderType(String name) {
		if (name.equals("modCauldron"))
			return cauldronRenderType;
		
		return 0;
	}
}
