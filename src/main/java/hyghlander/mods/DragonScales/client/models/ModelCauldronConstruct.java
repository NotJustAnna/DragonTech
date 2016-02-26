package hyghlander.mods.DragonScales.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelCauldronConstruct - TFH
 * Created using Tabula 4.1.1
 */
public class ModelCauldronConstruct extends ModelBase {
    public ModelRenderer CauldInterf;
    public ModelRenderer MachineBlock;
    public ModelRenderer OutValve;
    public ModelRenderer LiquiValve;
    public ModelRenderer InValve;
    public ModelRenderer InValve2;

    public ModelCauldronConstruct() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.InValve2 = new ModelRenderer(this, 24, 31);
        this.InValve2.setRotationPoint(-2.0F, 13.0F, -2.0F);
        this.InValve2.addBox(0.0F, 0.0F, 0.0F, 4, 2, 4, 0.0F);
        this.CauldInterf = new ModelRenderer(this, 0, 0);
        this.CauldInterf.setRotationPoint(-7.0F, 23.0F, -7.0F);
        this.CauldInterf.addBox(0.0F, 0.0F, 0.0F, 14, 1, 14, 0.0F);
        this.MachineBlock = new ModelRenderer(this, 0, 15);
        this.MachineBlock.setRotationPoint(-4.0F, 15.0F, -4.0F);
        this.MachineBlock.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.OutValve = new ModelRenderer(this, 42, 0);
        this.OutValve.setRotationPoint(-2.0F, 17.0F, -8.0F);
        this.OutValve.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.LiquiValve = new ModelRenderer(this, 42, 0);
        this.LiquiValve.setRotationPoint(-2.0F, 17.0F, 4.0F);
        this.LiquiValve.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.InValve = new ModelRenderer(this, 0, 31);
        this.InValve.setRotationPoint(-3.0F, 11.0F, -3.0F);
        this.InValve.addBox(0.0F, 0.0F, 0.0F, 6, 2, 6, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.InValve2.render(f5);
        this.CauldInterf.render(f5);
        this.MachineBlock.render(f5);
        this.OutValve.render(f5);
        this.LiquiValve.render(f5);
        this.InValve.render(f5);
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
