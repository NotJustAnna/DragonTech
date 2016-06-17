package cf.adriantodt.mods.DragonScales.common.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cf.adriantodt.mods.DragonScales.DragonScales;
import cf.adriantodt.mods.DragonScales.Lib;
import cf.adriantodt.mods.DragonScales.client.models.ModelDragonChestplate;
import cf.adriantodt.mods.DragonScales.common.DragonScalesHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDragonArmor extends ItemArmor
{
	public static boolean IsFullArmor(EntityPlayer player) {
		boolean[] armor = ArmorEquipped(player);
		return armor[0] && armor[1] && armor[2] && armor[3];
	}
	
	public static boolean[] ArmorEquipped(EntityPlayer player) {
		boolean[] result = new boolean[4];
		for (int i = 0; i < result.length; i++)
			result[i] = ArmorEquippedOnSlot(player, i);
		return result;
	}
	
	public static boolean ArmorEquippedOnSlot(EntityPlayer player, int slot) {
		return player.getCurrentArmor(slot) != null && player.getCurrentArmor(slot).getItem() != null && player.getCurrentArmor(slot).getItem().equals(GetArmorForSlot(slot));
	}
	
	public static Item GetArmorForSlot(int slot) {
		switch (slot) {
			case 0: return DragonScalesHandler.scalesBoots;
			case 1: return DragonScalesHandler.scalesLeggings;
			case 2: return DragonScalesHandler.scalesChestplate;
			case 3: return DragonScalesHandler.scalesHelm;
		}
		return null;
	}
	
	public ItemDragonArmor(ArmorMaterial armorMaterial, int armorType, String name)
	{
		super(armorMaterial, 0, armorType);
		//setUnlocalizedName(name);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		int id = stack.getItem().equals(DragonScalesHandler.scalesLeggings) ? 2 : stack.getItem().equals(DragonScalesHandler.scalesChestplate) ? 3 : 1;
		return Lib.TEXTURE_PATH + "textures/models/armor/scalesArmor"+id+".png";
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
		if(ArmorEquippedOnSlot(player,3))
		{
			player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 260, 0));
		}
		
		if(ArmorEquippedOnSlot(player,2))
		{
			player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 40, 1));
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 40, 1));
			player.fallDistance = 0.0F;
			
			player.capabilities.allowFlying = true;
		}
				
		if(ArmorEquippedOnSlot(player,1))
		{
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 40, 3));
		}
		
		if(ArmorEquippedOnSlot(player,0))
		{
			player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 40, 3));
			player.fallDistance = 0.0F;
		}
		
		if (!ArmorEquippedOnSlot(player,2) && player.capabilities.isCreativeMode == false && player.capabilities.allowFlying == true)
		{
			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;
		}
	}
	
	public EnumRarity getRarity(ItemStack ignored) { return EnumRarity.rare; }
}