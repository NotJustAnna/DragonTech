package cf.adriantodt.mods.DragonScales.client.renderers;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import brforgers.core.lib.ItemHelper;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

public class ItemTileEntityRenderer implements IItemRenderer {
	private final TileEntity tile;
	
	public ItemTileEntityRenderer(TileEntity tile) {
		this.tile = tile;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRendererDispatcher.instance.renderTileEntityAt(tile, 0.0D, 0.0D, 0.0D, 0.0F);
		
	}
	
	public static void newItemTileRenderer(Block block)
	{
		newItemTileRenderer(block, block.createTileEntity(null, 0));
	}
	
	public static void newItemTileRenderer(Block block, int meta)
	{
		newItemTileRenderer(block, block.createTileEntity(null, meta));
	}
	
	public static void newItemTileRenderer(Block block, TileEntity tile)
	{
		MinecraftForgeClient.registerItemRenderer(ItemHelper.toItem(block), new ItemTileEntityRenderer(tile));
	}
}