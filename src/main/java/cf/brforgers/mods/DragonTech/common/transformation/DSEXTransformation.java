package cf.brforgers.mods.DragonTech.common.transformation;

import cf.brforgers.mods.DragonTech.common.utils.DSEXWorldData;
import cf.brforgers.mods.DragonTech.common.utils.EnumPlayerState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class DSEXTransformation {
    public static void enableDragonModel(Item item) {
        item.addPropertyOverride(new ResourceLocation("entityType"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (entityIn == null) {
                    return 0.0F;
                } else {
                    return isDragon(entityIn);
                }
            }
        });
    }

    private static float isDragon(EntityLivingBase entityIn) {
        if (entityIn instanceof EntityPlayer) {
            return DSEXWorldData.get((EntityPlayer) entityIn).getState((EntityPlayer) entityIn) == EnumPlayerState.NONE ? 0 : 1;
        }

        return 0;
    }
}
