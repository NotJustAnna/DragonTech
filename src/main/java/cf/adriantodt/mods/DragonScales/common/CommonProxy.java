package cf.adriantodt.mods.DragonScales.common;

import cf.adriantodt.mods.DragonScales.common.events.EventHandler;
import cf.adriantodt.mods.DragonScales.common.world.DraconyVirus;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	public void preInit()
	{
		DragonScalesHandler.registerAll();
	}
	
	public void init()
	{
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
		
		DraconyVirus.Register();
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
