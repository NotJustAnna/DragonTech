package cf.brforgers.mods.DragonTech.client.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ItemTileEntityRenderer implements IItemRenderer {
	private final TileEntity tile;
	
	public ItemTileEntityRenderer(TileEntity tile) {
		this.tile = tile;
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
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(block), new ItemTileEntityRenderer(tile));
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
}