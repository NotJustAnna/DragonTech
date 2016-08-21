package cf.brforgers.mods.DragonTech.client;

import cf.brforgers.mods.DragonTech.client.models.ModelDragonChestplate;
import cf.brforgers.mods.DragonTech.common.CommonProxy;
import net.minecraft.client.model.ModelBiped;

import static cf.brforgers.mods.DragonTech.common.DTManager.REGISTER;

public class ClientProxy extends CommonProxy {
	private static final ModelBiped dragonChestplate = new ModelDragonChestplate(1.0f);
	private static final ModelBiped dragonLeggings = new ModelBiped(0.45f);
	private static final ModelBiped dragonBoots = new ModelBiped(0.9f);
	
	private static int cauldronRenderType = -1;
	
	public void preInit(){
		super.preInit();
	}
	
	public void registerRenderThings(){
		REGISTER.automagicallyRegisterRenderers();
	}
	
	@Override
	public ModelBiped getArmorModel(int id){
		switch (id) {
		case 0:
			return dragonBoots;
		case 1:
			return dragonChestplate;
		case 2:
			return dragonLeggings;
		case 3:
			return dragonBoots;
		}
		return dragonLeggings;
	}
}
