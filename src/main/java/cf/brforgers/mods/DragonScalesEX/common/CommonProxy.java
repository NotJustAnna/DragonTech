package cf.brforgers.mods.DragonScalesEX.common;

import cf.brforgers.mods.DragonScalesEX.common.events.EventHandler;
import cf.brforgers.mods.DragonScalesEX.common.world.DraconyVirus;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	public void preInit()
	{
		DragonScalesHandler.registerAll();
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
		
	}
	
	public void registerHandlers() {
		Object handler = new EventHandler();
		MinecraftForge.EVENT_BUS.register(handler);
		FMLCommonHandler.instance().bus().register(handler);
        FMLCommonHandler.instance().bus().register(DraconyVirus.batchExecutor);
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
