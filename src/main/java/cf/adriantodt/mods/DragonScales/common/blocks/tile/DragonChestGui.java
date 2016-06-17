package cf.adriantodt.mods.DragonScales.common.blocks.tile;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class DragonChestGui extends GuiContainer{
	
	public static final ResourceLocation field_110421_t = new ResourceLocation("jakester12dragonscale:/textures/gui/dragonchest.png");

	private IInventory upperChestInventory;
	private IInventory lowerChestInventory;
	private int inventoryRows;

	public DragonChestGui(IInventory par1IInventory, IInventory par2IInventory) {
		super(new ContainerChest(par1IInventory, par2IInventory));
		this.upperChestInventory = par1IInventory;
		this.lowerChestInventory = par1IInventory;
		short short1 = 222;
		int i = short1 - 108;
		this.inventoryRows = par2IInventory.getSizeInventory()/9;
		this.ySize = i + this.inventoryRows * 18;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(field_110421_t);
		int k = (this.width - this.xSize)/ 2;
		int y = (this.height - this.ySize) /2;
		this.drawTexturedModalRect(k, y, 0, 0, xSize, ySize);
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2){
		fontRendererObj.drawString("DragonChest", 8, 6, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 +2, 4210752);
	}
	

}
