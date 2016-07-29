package cf.brforgers.mods.DragonScalesEX.nei;

import cf.brforgers.mods.DragonScalesEX.Lib;
import cf.brforgers.mods.DragonScalesEX.common.DragonScalesHandler;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import net.minecraft.item.ItemStack;

public class NEIConfig implements IConfigureNEI{

	public void loadConfig() {
		
		API.registerRecipeHandler(new CauldronHandler());
		API.registerUsageHandler(new CauldronHandler());
        API.hideItem(new ItemStack(DragonScalesHandler.modCauldron));
        API.hideItem(new ItemStack(DragonScalesHandler.essenceCombiner));
        //API.setGuiOffset(GuiInfusionAltar.class, 0, 0);
	}

	public String getName() {
		return Lib.MODNAME+" Plugin";
	}

	public String getVersion() {
		return Lib.VERSION;
	}
	
}
