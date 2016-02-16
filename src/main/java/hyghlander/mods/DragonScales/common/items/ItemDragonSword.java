package hyghlander.mods.DragonScales.common.items;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemDragonSword extends ItemSword
{
	public ItemDragonSword(ToolMaterial material)
	{
		super(material);
	}
	
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.rare;
	}
}