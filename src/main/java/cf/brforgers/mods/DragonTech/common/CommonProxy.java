package cf.brforgers.mods.DragonTech.common;

import cf.brforgers.mods.DragonTech.common.general.events.EventHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	public void preInit()
	{
        DSEXManager.registerItems();   //Items
        DSEXManager.registerBlocks();  //Blocks
        DSEXRecipes.registerOredict(); //Oredict
    }
	
	public void init()
	{
        DSEXRecipes.registerOredict();
        DSEXRecipes.registerRecipes();
        registerRenderThings();
		registerHandlers();
	}
	
	public void postInit()
	{
		//CauldronAPIHandler.processDispensingBehaviour();
	}
	
	public void registerHandlers() {
		Object handler = new EventHandler();
		MinecraftForge.EVENT_BUS.register(handler);
	}
	
	public void registerRenderThings(){
		
	}

	public ModelBiped getArmorModel(int id) {
		return null;
	}
}
