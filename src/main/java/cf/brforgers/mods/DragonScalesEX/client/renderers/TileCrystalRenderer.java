package cf.brforgers.mods.DragonScalesEX.client.renderers;

import cf.brforgers.mods.DragonScalesEX.Lib;
import cf.brforgers.mods.DragonScalesEX.client.models.ModelDragonCrystal;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class TileCrystalRenderer extends TileEntitySpecialRenderer{

    // the model texture of our block
    private static final ResourceLocation resourceLocal = new ResourceLocation(Lib.TEXTURE_PATH + "textures/models/DRAGON_CRYSTAL.png");
    // the model of out block
	public final ModelDragonCrystal model;



	// also gets model of out block
	public TileCrystalRenderer(){
		this.model = new ModelDragonCrystal();
	}
	//renders tile entity in world
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

	private void rotateBlock(World world, int x, int y, int z, Block block){

		if (world != null){
			int dir = world.getBlockMetadata(x, y, z);
			GL11.glPushMatrix();
			GL11.glRotatef(dir * (90), 0F, 1F, 0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocal);
			this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		}else{
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocal);
			this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		}
	}
	
	private void renderBlockWithRotation(World world, int x, int y, int z, Block block) {
		GL11.glPushMatrix();
		
		if (world != null) {
			GL11.glRotatef(world.getBlockMetadata(x, y, z) * (90), 0F, 1F, 0F);
			if (world.getBlock(x, y-1, z).isAir(world, x, y-1, z)) {
				if (!world.getBlock(x, y+1, z).isAir(world, x, y+1, z)) {
					GL11.glRotatef(180, 1F, 0F, 0F);
					GL11.glTranslatef(0.0F, -2.0F, 0.0F);
				}
			}
		}
		
		Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocal);
		this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
}




