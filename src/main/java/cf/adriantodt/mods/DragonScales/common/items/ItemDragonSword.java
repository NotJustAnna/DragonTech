package cf.adriantodt.mods.DragonScales.common.items;

import java.util.Random;

import com.google.common.collect.Multimap;

import cf.adriantodt.mods.DragonScales.common.DragonScalesHandler;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MathHelper;

import static cf.adriantodt.mods.DragonScales.common.DragonScalesHandler.DRAGONALLOY_TOOL_MATERIAL;

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