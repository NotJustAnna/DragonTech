package cf.brforgers.mods.DragonScalesEX;

import org.apache.logging.log4j.Logger;

import cf.brforgers.mods.DragonScalesEX.common.CommonProxy;
import cf.brforgers.mods.DragonScalesEX.common.DragonScalesHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Lib.MODID, name = Lib.MODNAME, version = Lib.VERSION, dependencies = Lib.DEPS)
public class DragonScalesEX {
	@Mod.Instance
	public static DragonScalesEX instance;
	
	@SidedProxy(clientSide = "cf.brforgers.mods.DragonScalesEX.client.ClientProxy", serverSide = "cf.brforgers.mods.DragonScalesEX.common.CommonProxy")
	public static CommonProxy proxy;
	
	public static Logger logger;
	
	public static final CreativeTabs tabDragonScales = new CreativeTabs("tabDragonScalesEX") {@Override public Item getTabIconItem(){return DragonScalesHandler.dragonEssenceShard;}};
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		logger = e.getModLog();
		proxy.preInit();
		
		Configuration cfg = new Configuration(e.getSuggestedConfigurationFile());
		cfg.load();
		
		Lib.Config.DraconyVirus_SpreadingMultiplier = cfg.getInt("SpreadingMultiplier", "DraconyVirus", 3, 1, 4, "Determines how big the Virus World Gen will be (The bigger, the Longer it takes to Generate)");
		Lib.Config.DraconyVirus_ChanceMultiplier = cfg.getInt("ChanceMultiplier", "DraconyVirus", 1, 0, 4, "Determines how rare the Virus World Gen will be (Less = rarer; 0 disables)");
		Lib.Config.DraconyVirus_ForceGeneration = cfg.getBoolean("ForceGeneration", "DraconyVirus", false, "Force Generation after 500 Chunks non-generated. Used to Debug more easily the World Gen");
		Lib.Config.BatchExecutor_Timeout = cfg.getInt("BatchExecutorTimeout", "Optimization", 3, 1, 4, "Determines how much of the tick it will reserve to proccess the Runnables (1=1/5 of the Tick;4 = Almost Full Tick Free Time). Reduce number if the Server can't handle. Lower Numbers tend to make AsyncGeneration a lot slower");
		Lib.Config.Debug = cfg.getBoolean("Log", "Debug", false, "Log useful Debug Information in the Console");
		cfg.save();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent e)
	{
		proxy.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
		proxy.postInit();
	}
}
