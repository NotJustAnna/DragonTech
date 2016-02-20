package hyghlander.mods.DragonScales.common.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hyghlander.mods.DragonScales.DragonScales;
import hyghlander.mods.DragonScales.Lib;
import hyghlander.mods.DragonScales.client.models.ModelDragonChestplate;
import hyghlander.mods.DragonScales.common.DragonScalesHandler;

public class ItemDragonArmor extends ItemArmor
{
	
	public ItemDragonArmor(ArmorMaterial armorMaterial, int armorType, String name)
	{
		super(armorMaterial, 0, armorType);
		//setUnlocalizedName(name);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (stack.getItem().equals(DragonScalesHandler.scalesLeggings)) {
			return Lib.TEXTURE_PATH + "textures/models/armor/dragon_layer_2.png";
		}
		if (stack.getItem().equals(DragonScalesHandler.scalesChestplate)) {
			return Lib.TEXTURE_PATH + "textures/models/armor/dragonchest3d.png";
		}
		
		return Lib.TEXTURE_PATH + "textures/models/armor/dragon_layer_1.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
		if (itemStack != null && itemStack.getItem() instanceof ItemDragonArmor)
		{
			ModelBiped armorModel = DragonScales.proxy.getArmorModel(armorSlot);
			
			armorModel.bipedHead.showModel = armorSlot == 0;
			armorModel.bipedHeadwear.showModel = armorSlot == 0;
			armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
			armorModel.bipedRightArm.showModel = armorSlot == 1;
			armorModel.bipedLeftArm.showModel = armorSlot == 1;
			armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
			armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;

			armorModel.isSneak = entityLiving.isSneaking();
			armorModel.isRiding = entityLiving.isRiding();
			armorModel.isChild = entityLiving.isChild();
			if(entityLiving instanceof EntityLiving)
			{
				armorModel.heldItemRight = ((EntityPlayer) entityLiving).getCurrentArmor(0) != null ? 1 :0;
			}
			
			if(entityLiving instanceof EntityPlayer){
				armorModel.aimedBow = ((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
			}

			return armorModel;	
		}
		
		return null; //Default Rendering
		/*
		ModelBiped armorModel = new ModelBiped();
		if(itemStack != null){
			if(itemStack.getItem() instanceof ItemDragonArmor){
				int type = ((ItemArmor)itemStack.getItem()).armorType;

				if(type == 1){
					armorModel = MainClass.proxy.getArmorModel(0);
				}else if(type == 3){
					armorModel = MainClass.proxy.getArmorModel(1);
				}else if(type == 2/4){
					armorModel = MainClass.proxy.getArmorModel(2);
				}
			}
			if(armorModel != null){
				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0;
				armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;

				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				armorModel.heldItemRight = entityLiving.getCurrentArmor(0) != null ? 1 :0;
				if(entityLiving instanceof EntityPlayer){
					armorModel.aimedBow =((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
				}
				return armorModel;
			}
		}

		return null;*/
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
		if(player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() == DragonScalesHandler.scalesHelm)
		{
			player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 260, 0));
		}
		
		if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == DragonScalesHandler.scalesChestplate)
		{
			player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 40, 1));
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 40, 1));
			player.fallDistance = 0.0F;
			
			player.capabilities.allowFlying = true;
		}
				
		if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == DragonScalesHandler.scalesLeggings)
		{
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 40, 3));
		}
		
		if(player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem() == DragonScalesHandler.scalesBoots)
		{
			player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 40, 3));
			player.fallDistance = 0.0F;
		}
		
		if((player.getCurrentArmor(2) == null || player.getCurrentArmor(2).getItem() != DragonScalesHandler.scalesChestplate) && player.capabilities.isCreativeMode == false && player.capabilities.allowFlying == true)
		{
			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;
		}
	}
	
	public EnumRarity getRarity(ItemStack ignored)
	{
		return EnumRarity.rare;
	}
}