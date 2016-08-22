package cf.brforgers.mods.DragonTech.common;

import cf.brforgers.core.lib.ez.hooks.DropHooks;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.ItemStack;

public class CommonProxy {
    public void preInit() {
        DTManager.registerItems();   //Items
        DTManager.registerBlocks();  //Blocks
        DTRecipes.registerOredict(); //Oredict
    }

    public void init() {
        DTRecipes.registerOredict();
        DTRecipes.registerRecipes();
        registerRenderThings();
        registerHandlers();
    }

    public void postInit() {
        DropHooks.addDropItemStack("AdrianTodt", new ItemStack(DT.DRAGON_SCALE));
        DropHooks.addDragonDropItemStack(new ItemStack(DT.DRAGON_SCALE, 30));
        DropHooks.addDragonDropItemStack(new ItemStack(DT.DRAGON_SCALE, 30));
        //CauldronAPIHandler.processDispensingBehaviour();
    }

    public void registerHandlers() {
    }

    public void registerRenderThings() {
    }

    public ModelBiped getArmorModel(int id) {
        return null;
    }
}
