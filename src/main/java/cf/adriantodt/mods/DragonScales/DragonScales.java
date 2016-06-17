package cf.adriantodt.mods.DragonScales;

import org.apache.logging.log4j.Logger;

import brforgers.core.UpdateManager;
import brforgers.core.lib.SilentLogger;
import cf.adriantodt.mods.DragonScales.common.CommonProxy;
import cf.adriantodt.mods.DragonScales.common.DragonScalesHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

@Mod(modid = Lib.MODID, name = Lib.MODNAME, version = Lib.VERSION, dependencies = Lib.DEPS)
public class DragonScales {
	@Mod.Instance
	public static DragonScales instance;
	
	@SidedProxy(clientSide = "hyghlander.mods.DragonScales.client.ClientProxy", serverSide = "hyghlander.mods.DragonScales.common.CommonProxy")
	public static CommonProxy proxy;
	
	public static Logger logger = new SilentLogger();
	
	public static final CreativeTabs tabDragonScales = new CreativeTabs("tabDragonScalesEX") {@Override public Item getTabIconItem(){return DragonScalesHandler.dragonEssenceShard;}};
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		logger = e.getModLog();
		proxy.preInit();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent e)
	{
		proxy.init();
		UpdateManager.addToUpdateChecker(Lib.MODID, Lib.FANCYNAME, Lib.UPDATEURL, Lib.VERSION, logger);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
		proxy.postInit();
	}
}
