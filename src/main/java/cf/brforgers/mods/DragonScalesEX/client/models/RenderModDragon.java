package cf.brforgers.mods.DragonScalesEX.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderModDragon extends RenderLiving
{
    private static ResourceLocation Your_Texture = new ResourceLocation("jakester12dragonscale:textures/entities/Dragon.png");

    public RenderModDragon(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }


	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return Your_Texture;
	}
}