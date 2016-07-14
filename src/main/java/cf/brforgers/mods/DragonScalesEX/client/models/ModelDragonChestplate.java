package cf.brforgers.mods.DragonScalesEX.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDragonChestplate extends ModelBiped
{
		ModelRenderer leftArm1;
		ModelRenderer leftArm2;
		ModelRenderer leftArm3;
		ModelRenderer rightArm1;
		ModelRenderer rightArm2;
		ModelRenderer rightArm3;
		ModelRenderer body1;
		ModelRenderer body2;
		ModelRenderer body3;
		ModelRenderer body4;
		ModelRenderer body41;
		ModelRenderer body31;
		ModelRenderer body312;
		ModelRenderer body311;
		ModelRenderer body411;
		ModelRenderer body412;
		ModelRenderer body5;
		ModelRenderer body51;
		ModelRenderer body52;
		ModelRenderer body2left;
		ModelRenderer body2right;
	
	public ModelDragonChestplate(float f)
	{
		super(f, 0, 128,128);
		textureWidth = 128;
		textureHeight = 128;
		
		leftArm1 = new ModelRenderer(this, 0, 118);
		leftArm1.addBox(-1F, -2.5F, -2.5F, 5, 5, 5);
		leftArm1.setRotationPoint(0F, 0F, 0F);
		leftArm1.setTextureSize(128, 128);
		leftArm1.mirror = true;
		setRotation(leftArm1, 0F, 0F, 0F);
		rightArm2 = new ModelRenderer(this, 46, 115);
		rightArm2.addBox(-3.5F, -0.5F, -1F, 1, 11, 2);
		rightArm2.setRotationPoint(0F, 0F, 0F);
		rightArm2.setTextureSize(128, 128);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0F, 0F, 0F);
		rightArm1 = new ModelRenderer(this, 26, 118);
		rightArm1.addBox(-4F, -2.5F, -2.5F, 5, 5, 5);
		rightArm1.setRotationPoint(0F, 0F, 0F);
		rightArm1.setTextureSize(128, 128);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0F, 0F, 0F);
		leftArm2 = new ModelRenderer(this, 20, 115);
		leftArm2.addBox(2.5F, -0.5F, -1F, 1, 11, 2);
		leftArm2.setRotationPoint(0F, 0F, 0F);
		leftArm2.setTextureSize(128, 128);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0F, 0F, 0F);
		leftArm3 = new ModelRenderer(this, 0, 115);
		leftArm3.addBox(-0.5F, 9.5F, -1F, 3, 1, 2);
		leftArm3.setRotationPoint(0F, 0F, 0F);
		leftArm3.setTextureSize(128, 128);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0F, 0F, 0F);
		rightArm3 = new ModelRenderer(this, 26, 115);
		rightArm3.addBox(-2.5F, 9.5F, -1F, 3, 1, 2);
		rightArm3.setRotationPoint(0F, 0F, 0F);
		rightArm3.setTextureSize(128, 128);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0F, 0F, 0F);
		body1 = new ModelRenderer(this, 53, 116);
		body1.addBox(-4F, 0F, -2.5F, 8, 11, 1);
		body1.setRotationPoint(0F, 0F, 0F);
		body1.setTextureSize(128, 128);
		body1.mirror = true;
		setRotation(body1, 0F, 0F, 0F);
		body2 = new ModelRenderer(this, 53, 104);
		body2.addBox(-4F, 0F, 1.5F, 8, 11, 1);
		body2.setRotationPoint(0F, 0F, 0F);
		body2.setTextureSize(128, 128);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		body3 = new ModelRenderer(this, 71, 114);
		body3.addBox(2F, 2F, 0F, 2, 1, 13);
		body3.setRotationPoint(0F, 0F, 0F);
		body3.setTextureSize(128, 128);
		body3.mirror = true;
		setRotation(body3, 0.992464F, 0F, 0.3903778F);
		body4 = new ModelRenderer(this, 71, 100);
		body4.addBox(-4F, 2F, 0F, 2, 1, 13);
		body4.setRotationPoint(0F, 0F, 0F);
		body4.setTextureSize(128, 128);
		body4.mirror = true;
		setRotation(body4, 0.9924652F, -2E-06F, -0.390384F);
		body41 = new ModelRenderer(this, 71, 100);
		body41.addBox(3F, -5F, 12.5F, 2, 1, 13);
		body41.setRotationPoint(0F, 0F, 0F);
		body41.setTextureSize(128, 128);
		body41.mirror = true;
		setRotation(body41, 0.5318635F, -0.5599532F, -0.6134471F);
		body31 = new ModelRenderer(this, 71, 114);
		body31.addBox(-5F, -5F, 12.5F, 2, 1, 13);
		body31.setRotationPoint(0F, 0F, 0F);
		body31.setTextureSize(128, 128);
		body31.mirror = true;
		setRotation(body31, 0.5235988F, 0.5585054F, 0.6108652F);
		body312 = new ModelRenderer(this, 71, 74);
		body312.addBox(-3F, -5.5F, 13F, 10, 1, 12);
		body312.setRotationPoint(0F, 0F, 0F);
		body312.setTextureSize(128, 128);
		body312.mirror = true;
		setRotation(body312, 0.5235988F, 0.5585054F, 0.6108652F);
		body311 = new ModelRenderer(this, 71, 87);
		body311.addBox(3F, 2.5F, 0F, 10, 1, 13);
		body311.setRotationPoint(0F, 0F, 0F);
		body311.setTextureSize(128, 128);
		body311.mirror = true;
		setRotation(body311, 0.9401041F, 0.1684417F, 0.2924823F);
		body411 = new ModelRenderer(this, 71, 60);
		body411.addBox(-13F, 2.5F, 0F, 10, 1, 13);
		body411.setRotationPoint(0F, 0F, 0F);
		body411.setTextureSize(128, 128);
		body411.mirror = true;
		setRotation(body411, 0.9401041F, -0.1684417F, -0.2924823F);
		body412 = new ModelRenderer(this, 71, 47);
		body412.addBox(-7F, -5.5F, 14F, 10, 1, 12);
		body412.setRotationPoint(0F, 0F, 0F);
		body412.setTextureSize(128, 128);
		body412.mirror = true;
		setRotation(body412, 0.5235988F, -0.5585054F, -0.6108652F);
		body5 = new ModelRenderer(this, 82, 42);
		body5.addBox(-0.5F, 1F, 2F, 1, 2, 2);
		body5.setRotationPoint(0F, 0F, 0F);
		body5.setTextureSize(128, 128);
		body5.mirror = true;
		setRotation(body5, 0F, 0F, 0F);
		body51 = new ModelRenderer(this, 82, 37);
		body51.addBox(-0.5F, 4F, 2F, 1, 2, 2);
		body51.setRotationPoint(0F, 0F, 0F);
		body51.setTextureSize(128, 128);
		body51.mirror = true;
		setRotation(body51, 0F, 0F, 0F);
		body52 = new ModelRenderer(this, 82, 32);
		body52.addBox(-0.5F, 7F, 2F, 1, 2, 2);
		body52.setRotationPoint(0F, 0F, 0F);
		body52.setTextureSize(128, 128);
		body52.mirror = true;
		setRotation(body52, 0F, 0F, 0F);
		body2left = new ModelRenderer(this, 59, 88);
		body2left.addBox(3.5F, 0F, -1.5F, 1, 11, 3);
		body2left.setRotationPoint(0F, 0F, 0F);
		body2left.setTextureSize(128, 128);
		body2left.mirror = true;
		setRotation(body2left, 0F, 0F, 0F);
		body2right = new ModelRenderer(this, 59, 88);
		body2right.addBox(-4.5F, 0F, -1.5F, 1, 11, 3);
		body2right.setRotationPoint(0F, 0F, 0F);
		body2right.setTextureSize(128, 128);
		body2right.mirror = true;
		setRotation(body2right, 0F, 0F, 0F);

		
		this.bipedLeftArm.addChild(leftArm1);
		this.bipedRightArm.addChild(rightArm1);
		this.bipedLeftArm.addChild(leftArm2);
		this.bipedRightArm.addChild(rightArm2);
		this.bipedLeftArm.addChild(leftArm3);
		this.bipedRightArm.addChild(rightArm3);
		this.bipedBody.addChild(body1);
		this.bipedBody.addChild(body2);
		this.bipedBody.addChild(body3);
		this.bipedBody.addChild(body4);
		this.bipedBody.addChild(body5);
		this.bipedBody.addChild(body41);
		this.bipedBody.addChild(body411);
		this.bipedBody.addChild(body412);
		this.bipedBody.addChild(body31);
		this.bipedBody.addChild(body311);
		this.bipedBody.addChild(body312);
		this.bipedBody.addChild(body2right);
		this.bipedBody.addChild(body2left);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
