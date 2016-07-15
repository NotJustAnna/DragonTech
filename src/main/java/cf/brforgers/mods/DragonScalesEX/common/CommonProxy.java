package cf.brforgers.mods.DragonScalesEX.common;

import cf.brforgers.mods.DragonScalesEX.common.events.EventHandler;
import cf.brforgers.mods.DragonScalesEX.common.world.DraconyVirus;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	public void preInit()
	{
		DragonScalesHandler.registerHelpers(); //Helpers (FastFactory and RegisterHelper)
		DragonScalesHandler.registerItems();   //Items
		DragonScalesHandler.registerBlocks();  //Blocks
		DragonScalesHandler.registerOredict(); //Oredict
	}
	
	public void init()
	{
	    DragonScalesHandler.registerOredict();
		DragonScalesHandler.registerRecipes();
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
