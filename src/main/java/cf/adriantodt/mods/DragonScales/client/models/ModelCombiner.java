package cf.adriantodt.mods.DragonScales.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Combiner - TFH
 * Created using Tabula 4.1.1
 */
public class ModelCombiner extends ModelBase {
    public ModelRenderer base;
    public ModelRenderer plate1;
    public ModelRenderer plate2;
    public ModelRenderer plate3;

    public ModelCombiner() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.plate3 = new ModelRenderer(this, 0, 0);
        this.plate3.setRotationPoint(4.0F, 19.0F, -4.0F);
        this.plate3.addBox(-6.0F, 0.0F, -2.0F, 4, 1, 4, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(-8.0F, 20.0F, -8.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 16, 4, 16, 0.0F);
        this.plate1 = new ModelRenderer(this, 0, 0);
        this.plate1.setRotationPoint(2.0F, 19.0F, 2.0F);
        this.plate1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4, 0.0F);
        this.plate2 = new ModelRenderer(this, 0, 0);
        this.plate2.setRotationPoint(-6.0F, 19.0F, 2.0F);
        this.plate2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.base.render(f5);
        this.plate3.render(f5);
        this.plate1.render(f5);
        this.plate2.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
