package cf.adriantodt.mods.DragonScales.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItem extends Item {
	private static CreativeTabs tab = null;
	private static String pre = "";
	public ModItem(String name)
	{
		super();
		setUnlocalizedName(name);
		setTextureName(pre + name);
		setCreativeTab(tab);
	}
	
	public static Item process(Item item, String name)
	{
		item.setUnlocalizedName(name);
		item.setTextureName(pre + name);
		item.setCreativeTab(tab);
		return item;
	}
	
	public static void set(CreativeTabs modTab, String texturePrefix)
	{
		tab = modTab;
		pre = texturePrefix + ":";
	}
}
