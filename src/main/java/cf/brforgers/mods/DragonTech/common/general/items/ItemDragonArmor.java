package cf.brforgers.mods.DragonTech.common.general.items;

import cf.brforgers.core.internal.InternalHelper;
import cf.brforgers.core.lib.ez.hooks.IEventArmor;
import cf.brforgers.mods.DragonTech.common.DT;
import com.google.common.collect.Lists;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public class ItemDragonArmor extends ItemArmor implements ISpecialArmor, IEventArmor {
    private static final int[] armorDisplayValues = {5, 16, 12, 6};

    public ItemDragonArmor(ArmorMaterial armorMaterial, EntityEquipmentSlot armorType) {
        super(armorMaterial, 0, armorType);
    }

    public static boolean haveFullArmor(EntityPlayer player) {
        boolean[] armor = armorEquipped(player);
        return armor[0] && armor[1] && armor[2] && armor[3];
    }

    public static boolean haveAnyArmor(EntityPlayer player) {
        boolean[] armor = armorEquipped(player);
        return armor[0] && armor[1] && armor[2] && armor[3];
    }

    public static boolean[] armorEquipped(EntityPlayer player) {
        boolean[] result = new boolean[4];
        for (int i = 0; i < result.length; i++)
            result[i] = armorEquippedOnSlot(player, i);
        return result;
    }

    public static boolean armorEquippedOnSlot(EntityPlayer player, int slot) {
        ItemStack stack = Lists.newArrayList(player.getArmorInventoryList()).get(slot);
        return stack != null && stack.getItem() != null && stack.getItem().equals(getArmorForSlot(slot));
    }

    public static Item getArmorForSlot(int slot) {
        switch (slot) {
            case 0:
                return DT.SCALES_BOOTS;
            case 1:
                return DT.SCALES_LEGGINGS;
            case 2:
                return DT.SCALES_CHESTPLATE;
            case 3:
                return DT.SCALES_HELM;
        }
        return null;
    }

//	@Override
//	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
//		int id = stack.getItem().equals(DTManager.scalesLeggings) ? 2 : stack.getItem().equals(DTManager.scalesChestplate) ? 3 : 1;
//		return Lib.TEXTURE_PATH + "textures/models/armor/scalesArmor"+id+".png";
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
//		if (itemStack != null && itemStack.getItem() instanceof ItemDragonArmor)
//		{
//			ModelBiped armorModel = DragonTech.proxy.getArmorModel(armorSlot);
//
//			armorModel.bipedHead.showModel = armorSlot == 0;
//			armorModel.bipedHeadwear.showModel = armorSlot == 0;
//			armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
//			armorModel.bipedRightArm.showModel = armorSlot == 1;
//			armorModel.bipedLeftArm.showModel = armorSlot == 1;
//			armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
//			armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
//
//			if (entityLiving != null) {
//				armorModel.isSneak = entityLiving.isSneaking();
//				armorModel.isRiding = entityLiving.isRiding();
//				armorModel.isChild = entityLiving.isChild();
//				if(entityLiving instanceof EntityLiving)
//				{
//                    armorModel.rightArmPose = // = ((EntityLiving)entityLiving).getHeldItem(EnumHand.MAIN_HAND) != null ? 1 : 0;
//                }
//
//				if(entityLiving instanceof EntityPlayer){
//					//armorModel.heldItemRight = ((EntityPlayer) entityLiving).getCurrentArmor(0) != null ? 1 :0;
//					armorModel.aimedBow = ((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
//				}
//			}
//            return armorModel;
//        }
//
//        return null;
//	}

    public EnumRarity getRarity(ItemStack ignored) {
        return EnumRarity.RARE;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        if (source.damageType.equals("cactus") || source.damageType.equals("anvil") || source.damageType.equals("fallingBlock") || source.isUnblockable() || source.isFireDamage() || source.isDamageAbsolute() || source.isMagicDamage() || (source.isProjectile() && (source.damageType.equals("onFire") || source.damageType.equals("fireball"))))
            return new ArmorProperties(0, damageReduceAmount / 100D, 15);
        if (source.damageType.equals("mob"))
            return new ArmorProperties(0, damageReduceAmount / 50D, 15);
        return new ArmorProperties(0, damageReduceAmount / 24.5D, armor.getMaxDamage() * 2);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return armorDisplayValues[slot];
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        stack.damageItem(damage / 2, entity);
    }

    @Override
    public void onArmorWorn(World world, EntityPlayer player, ItemStack stack) {
        if (haveFullArmor(player)) {
            player.capabilities.allowFlying = true;
            player.fallDistance = 0.0F;
            player.stepHeight = 1.0f;
        }
    }

    @Override
    public void onArmorUnworn(World world, EntityPlayer player, ItemStack stack) {
        if (haveAnyArmor(player)) return;
        if (player.capabilities.isCreativeMode == false && player.capabilities.allowFlying == true) {
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
        }
        player.stepHeight = 0.5f;
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (!haveFullArmor(player) || itemStack.getItem() != DT.SCALES_CHESTPLATE) return;

        player.capabilities.allowFlying = true;
        player.fallDistance = 0.0F;
        player.stepHeight = 1.0f;
        if (player.getHealth() < 20.0f) {
            player.heal(0.05f);

            InternalHelper.getCurrentArmor(player)[player.worldObj.rand.nextInt(4)].damageItem(1, player);
        }

        if (armorEquippedOnSlot(player, 3)) {
            player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 260, 0, false, false));
        }

        if (armorEquippedOnSlot(player, 1)) {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 40, 3, false, false));
        }

        if (armorEquippedOnSlot(player, 0)) {
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 40, 3, false, false));
        }
    }
}