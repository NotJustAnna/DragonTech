package cf.brforgers.mods.DragonScalesEX.common.utils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RegisterHelper {
    private final String path;

    private RegisterHelper(String modId) {
        path = modId + ":";
    }

    public static RegisterHelper fromMod(String modId) {
        return new RegisterHelper(modId);
    }

    public <K extends IForgeRegistryEntry<?>> void register(K object) {
        GameRegistry.register(object);
    }

    public void register(Block block) {
        register(block);
    }

    public void register(Item item) {
        register(item);
    }

    @SideOnly(Side.CLIENT)
    public void registerItemRenderer(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(path + item.getUnlocalizedName().substring(5), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockRenderer(Block block) {
        registerItemRenderer(Item.getItemFromBlock(block));
    }
}
