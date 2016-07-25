package cf.brforgers.mods.DragonScalesEX.client;

import cf.brforgers.core.lib.client.Armor3DRenderer;
import cf.brforgers.mods.DragonScalesEX.client.models.ModelDragonChestplate;
import cf.brforgers.mods.DragonScalesEX.client.renderers.ItemTileEntityRenderer;
import cf.brforgers.mods.DragonScalesEX.client.renderers.TileCauldronConstructRenderer;
import cf.brforgers.mods.DragonScalesEX.client.renderers.TileCrystalRenderer;
import cf.brforgers.mods.DragonScalesEX.common.CommonProxy;
import cf.brforgers.mods.DragonScalesEX.common.DSEXManager;
import cf.brforgers.mods.DragonScalesEX.common.general.blocks.tile.TileCauldronConstruct;
import cf.brforgers.mods.DragonScalesEX.common.general.blocks.tile.TileCrystal;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;

public class ClientProxy extends CommonProxy {
	private static final ModelBiped dragonChestplate = new ModelDragonChestplate(1.0f);
	private static final ModelBiped dragonLeggings = new ModelBiped(0.45f);
	private static final ModelBiped dragonBoots = new ModelBiped(0.9f);
	
	private static int cauldronRenderType = -1;
	
	public void preInit(){
		super.preInit();
		
		//Tweak to Remove the Cauldron from NEI
		if (Loader.isModLoaded("NotEnoughItems"));
        codechicken.nei.api.API.hideItem(new ItemStack(DSEXManager.modCauldron));

	}
	
	public void registerRenderThings(){
		//Register TileEntity Renderers
		ClientRegistry.bindTileEntitySpecialRenderer(TileCrystal.class, new TileCrystalRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCauldronConstruct.class, new TileCauldronConstructRenderer());
		
		
		//Tweak to Render at Inventory
        ItemTileEntityRenderer.newItemTileRenderer(DSEXManager.dragonCrystal);
        ItemTileEntityRenderer.newItemTileRenderer(DSEXManager.cauldronConstruct);

        //Register Cauldron Renderer
		cauldronRenderType = RenderingRegistry.getNextAvailableRenderId();
		ISimpleBlockRenderingHandler cauldronRenderer = new BlockModCauldronRenderer();
		RenderingRegistry.registerBlockHandler(cauldronRenderType, cauldronRenderer);
		
		//Register the Armor
        Armor3DRenderer.register(DSEXManager.scalesBoots);
        Armor3DRenderer.register(DSEXManager.scalesChestplate);
        Armor3DRenderer.register(DSEXManager.scalesHelm);
        Armor3DRenderer.register(DSEXManager.scalesLeggings);

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
        if (name.equals("CAULDRON"))
            return cauldronRenderType;
		
		return 0;
	}
}
