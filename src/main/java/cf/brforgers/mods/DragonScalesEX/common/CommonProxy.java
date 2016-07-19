package cf.brforgers.mods.DragonScalesEX.common;

import cf.brforgers.mods.DragonScalesEX.common.events.EventHandler;
import cf.brforgers.mods.DragonScalesEX.common.world.DraconyVirus;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	public void preInit()
	{
        DSEXManager.registerHelpers(); //Helpers (FastFactory and RegisterHelper)
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
		MinecraftForge.EVENT_BUS.register(DraconyVirus.batchExecutor);
	}
	
	public void registerRenderThings(){
		
	}

	public ModelBiped getArmorModel(int id) {
		return null;
	}
	
	public int getRenderType(String name) {
		return 0;
	}
}
