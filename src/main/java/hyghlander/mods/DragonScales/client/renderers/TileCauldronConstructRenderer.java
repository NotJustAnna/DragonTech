package hyghlander.mods.DragonScales.client.renderers;

import org.lwjgl.opengl.GL11;

import hyghlander.mods.DragonScales.Lib;
import hyghlander.mods.DragonScales.client.models.ModelCauldronConstruct;
import hyghlander.mods.DragonScales.client.models.ModelCombiner;
import hyghlander.mods.DragonScales.client.models.ModelDragonCrystal;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileCauldronConstructRenderer extends TileEntitySpecialRenderer {
	//Block Model
	public final ModelBase model;

	//Texture Path
	private static final ResourceLocation resourceLocal = new ResourceLocation(Lib.TEXTURE_PATH + "textures/models/cauldronConstruct.png");

	// also gets model of out block
	public TileCauldronConstructRenderer(){
		this.model = new ModelCauldronConstruct();
	}
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		//push matrix tells the renderer to start doing something
		GL11.glPushMatrix();
		//this sets the initial location
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		//this rotates your block otherwise will render upside down
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		renderBlockWithRotation(te.getWorldObj(),te.xCoord,te.yCoord,te.zCoord,te.blockType);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	private void renderBlockWithRotation(World world, int x, int y, int z, Block block) {
		GL11.glPushMatrix();
		
		if (world != null)
			GL11.glRotatef(world.getBlockMetadata(x, y, z) * (90), 0F, 1F, 0F);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocal);
		this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
}
