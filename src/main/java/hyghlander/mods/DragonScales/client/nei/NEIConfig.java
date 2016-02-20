package hyghlander.mods.DragonScales.client.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import hyghlander.mods.DragonScales.Lib;

public class NEIConfig implements IConfigureNEI{

	public void loadConfig() {
		
		API.registerRecipeHandler(new InfusionAltarHandler());
		API.registerUsageHandler(new InfusionAltarHandler());
		//API.setGuiOffset(GuiInfusionAltar.class, 0, 0);
	}

	public String getName() {
		return Lib.MODNAME+" Plugin";
	}

	public String getVersion() {
		return Lib.VERSION;
	}
	
}
