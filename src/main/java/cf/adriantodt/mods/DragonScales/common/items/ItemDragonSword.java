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
	
	public float func_150931_i()
    {
		//Base Damage: 3/4 of Material
		//Random Bonus: 0 to 1/2 of Material
		
		//Unluckiest: 3/4 of Material
		//Luckiest: 5/4 of Material
		//Normal: 4/4 of Material
        return DRAGONALLOY_TOOL_MATERIAL.getDamageVsEntity()/4 * 3 + new Random().nextFloat() * DRAGONALLOY_TOOL_MATERIAL.getDamageVsEntity()/2;
    }
	
	public EnumRarity getRarity(ItemStack ignored)
	{
		return EnumRarity.rare;
	}
}