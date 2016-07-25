package cf.brforgers.mods.DragonScalesEX.common.general.items;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemDragonSword extends ItemSword
{
	public ItemDragonSword(ToolMaterial material)
	{
		super(material);
	}
	
	public EnumRarity getRarity(ItemStack ignored)
	{
		return EnumRarity.rare;
	}
}