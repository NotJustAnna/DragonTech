package cf.brforgers.mods.DragonScalesEX.nei;

import cf.brforgers.mods.DragonScalesEX.Lib;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIConfig implements IConfigureNEI{

	public void loadConfig() {
		
		API.registerRecipeHandler(new CauldronHandler());
		API.registerUsageHandler(new CauldronHandler());
		//API.setGuiOffset(GuiInfusionAltar.class, 0, 0);
	}

	public String getName() {
		return Lib.MODNAME+" Plugin";
	}

	public String getVersion() {
		return Lib.VERSION;
	}
	
}
