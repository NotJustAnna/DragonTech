package hyghlander.mods.DragonScales.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import hyghlander.mods.DragonScales.client.models.ModelDragonChestplate;
import hyghlander.mods.DragonScales.client.models.ModelModDragon;
import hyghlander.mods.DragonScales.client.models.RenderModDragon;
import hyghlander.mods.DragonScales.client.renderers.TileEntityDragonCrystalRenderer;
import hyghlander.mods.DragonScales.client.renderers.ModCauldronRenderer;
import hyghlander.mods.DragonScales.common.CommonProxy;
import hyghlander.mods.DragonScales.common.blocks.tile.TileEntityDragonCrystal;
import hyghlander.mods.DragonScales.common.blocks.tile.TileEntityModCauldron;
import hyghlander.mods.DragonScales.common.events.KeyBindings;
import hyghlander.mods.DragonScales.common.events.PlayerTickHandler;
import net.minecraft.client.model.ModelBiped;

public class ClientProxy extends CommonProxy {
	private static final ModelBiped dragonChestplate = new ModelDragonChestplate(1.0f);
	private static final ModelBiped dragonLeggings = new ModelBiped(0.45f);
	private static final ModelBiped dragonBoots = new ModelBiped(0.9f);
	
	private static int cauldronRenderType = -1;
	
	public void registerRenderThings(){
		//Register Crystal TileEntity Renderer
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDragonCrystal.class, new TileEntityDragonCrystalRenderer());
		
		//Register Cauldron Renderer
		cauldronRenderType = RenderingRegistry.getNextAvailableRenderId();
		ISimpleBlockRenderingHandler cauldronRenderer = new ModCauldronRenderer();
		RenderingRegistry.registerBlockHandler(cauldronRenderType, cauldronRenderer);
		
		//Register Dragon Renderer
		//RenderingRegistry.registerEntityRenderingHandler(EntityModDragon.class, new RenderModDragon(new ModelModDragon(), 0.5F));
	}
	
	public void registerHandlers()
	{
		super.registerHandlers();
		//KeyBindings.init();
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
