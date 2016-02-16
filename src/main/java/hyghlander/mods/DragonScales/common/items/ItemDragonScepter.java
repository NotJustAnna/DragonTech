package hyghlander.mods.DragonScales.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hyghlander.mods.DragonScales.DragonScales;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemDragonScepter extends Item
{
	public ItemDragonScepter()
	{
		super();
		setMaxStackSize(1);
		setMaxDamage(325);
	}
	
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.rare;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		itemstack.damageItem(1, entityplayer);
		if (!world.isRemote)
		{
			Vec3 look = entityplayer.getLookVec();
			EntityLargeFireball fireball2 = new EntityLargeFireball(world, entityplayer, 1, 1, 1);
			fireball2.setPosition(
				entityplayer.posX + look.xCoord * 2,
				entityplayer.posY + look.yCoord + 1,
				entityplayer.posZ + look.zCoord * 2
			);
			fireball2.accelerationX = look.xCoord * 0.1;
			fireball2.accelerationY = look.yCoord * 0.1;
			fireball2.accelerationZ = look.zCoord * 0.1;
			world.spawnEntityInWorld(fireball2);
		}
		
		return itemstack;
	}
}