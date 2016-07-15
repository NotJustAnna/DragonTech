package cf.brforgers.mods.DragonScalesEX.common.items;

import cf.brforgers.mods.DragonScalesEX.DragonScalesEX;
import cf.brforgers.mods.DragonScalesEX.Lib;
import cf.brforgers.mods.DragonScalesEX.common.DragonScalesHandler;
import com.google.common.collect.Lists;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDragonArmor extends ItemArmor implements ISpecialArmor
{
    public ItemDragonArmor(ArmorMaterial armorMaterial, EntityEquipmentSlot armorType) {
        super(armorMaterial, 0, armorType);
    }

	public static boolean IsFullArmor(EntityPlayer player) {
		boolean[] armor = ArmorEquipped(player);
		return armor[0] && armor[1] && armor[2] && armor[3];
	}

    public static boolean IsAnyArmor(EntityPlayer player) {
		boolean[] armor = ArmorEquipped(player);
		return armor[0] || armor[1] || armor[2] || armor[3];
	}
	
	public static boolean[] ArmorEquipped(EntityPlayer player) {
		boolean[] result = new boolean[4];
		for (int i = 0; i < result.length; i++)
			result[i] = ArmorEquippedOnSlot(player, i);
		return result;
	}
	
	public static boolean ArmorEquippedOnSlot(EntityPlayer player, int slot) {
        ItemStack stack = Lists.newArrayList(player.getArmorInventoryList()).get(slot);
        return stack != null && stack.getItem() != null && stack.getItem().equals(GetArmorForSlot(slot));
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

    public static void armorTick(EntityPlayer player) {
        if (ArmorEquippedOnSlot(player, 3)) {
            player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 260, 0, true));
        }

        if (ArmorEquippedOnSlot(player, 2)) {
            player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 40, 0, true));

        }

        if (ArmorEquippedOnSlot(player, 1)) {
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 40, 3, true));
        }

        if (ArmorEquippedOnSlot(player, 0)) {
            player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 40, 3, true));
            player.fallDistance = 0.0F;
            player.stepHeight = 1.0f;
        } else {
            player.stepHeight = 0.5f;
        }

        if (IsFullArmor(player)) {
            player.capabilities.allowFlying = true;
        } else if (player.capabilities.isCreativeMode == false && player.capabilities.allowFlying == true) {
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
        }
    }

    public static int floorFixed(float f) {
        int r = MathHelper.floor_float(f);
        if (f - r != 0) r++;
        return r;
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
			ModelBiped armorModel = DragonScalesEX.proxy.getArmorModel(armorSlot);

			armorModel.bipedHead.showModel = armorSlot == 0;
			armorModel.bipedHeadwear.showModel = armorSlot == 0;
			armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
			armorModel.bipedRightArm.showModel = armorSlot == 1;
			armorModel.bipedLeftArm.showModel = armorSlot == 1;
			armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
			armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;

			if (entityLiving != null) {
				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				if(entityLiving instanceof EntityLiving)
				{
                    armorModel.rightArmPose = // = ((EntityLiving)entityLiving).getHeldItem(EnumHand.MAIN_HAND) != null ? 1 : 0;
                }

				if(entityLiving instanceof EntityPlayer){
					//armorModel.heldItemRight = ((EntityPlayer) entityLiving).getCurrentArmor(0) != null ? 1 :0;
					armorModel.aimedBow = ((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
				}
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

    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		armorTick(player);
	}

	public EnumRarity getRarity(ItemStack ignored) { return EnumRarity.rare; }

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if (source.damageType.equals("cactus") || source.damageType.equals("anvil") || source.damageType.equals("fallingBlock") || source.isUnblockable() || source.isFireDamage() || source.isDamageAbsolute() || source.isMagicDamage() || (source.isProjectile() && (source.damageType.equals("onFire") || source.damageType.equals("fireball"))))
            return new ArmorProperties(0, damageReduceAmount / 100D, 15);
		if (source.damageType.equals("mob"))
			return new ArmorProperties(0, damageReduceAmount / 50D, 15);
        return new ArmorProperties(0, damageReduceAmount / 24.5D, armor.getMaxDamage()*2);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		int[] v = { 5, 16, 12, 6 };

        return v[((ItemArmor)armor.getItem()).armorType];
	}

    @Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		stack.damageItem(damage/2, entity);
	}
}