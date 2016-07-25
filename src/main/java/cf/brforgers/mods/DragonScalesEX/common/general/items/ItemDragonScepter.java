package cf.brforgers.mods.DragonScalesEX.common.general.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDragonScepter extends Item//Tool
{
	public ItemDragonScepter(ToolMaterial material)
	{
		super(/*-8,material,ImmutableSet.of()*/);
		setMaxStackSize(1);
		setMaxDamage(325);
	}

	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
        return EnumRarity.RARE;
    }

	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		stack.damageItem(1, player);
		if (!world.isRemote)
		{
            Vec3d look = player.getLookVec();
            EntityLargeFireball fireBall = new EntityLargeFireball(world, player, 1, 1, 1);
            fireBall.explosionPower = player.worldObj.rand.nextInt(5) + 2;
            fireBall.setPosition(
				player.posX + look.xCoord * 2.5,
				player.posY + look.yCoord + 1,
				player.posZ + look.zCoord * 2.5
			);
            double speed = 0.1 + MathHelper.clamp_double(player.worldObj.rand.nextDouble() / 5 - 0.1, 0.0, 1.0);
            fireBall.accelerationX = look.xCoord * speed;
			fireBall.accelerationY = look.yCoord * speed;
			fireBall.accelerationZ = look.zCoord * speed;
			world.spawnEntityInWorld(fireBall);
		}
		
		return stack;
	}
}