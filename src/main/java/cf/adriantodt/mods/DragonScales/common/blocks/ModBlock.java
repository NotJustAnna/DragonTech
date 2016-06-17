package cf.adriantodt.mods.DragonScales.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModBlock extends Block {
	private static CreativeTabs tab = null;
	private static String pre = "";
	private static Material mat = Material.rock;
	public ModBlock(String name)
	{
		super(mat);
		setBlockName(name);
		setBlockTextureName(pre + name);
		setCreativeTab(tab);
	}
	
	public static Block process(Block block, String name)
	{
		block.setBlockName(name);
		block.setBlockTextureName(pre + name);
		block.setCreativeTab(tab);
		return block;
	}
	
	public static void set(CreativeTabs modTab, String texturePrefix, Material material)
	{
		tab = modTab;
		pre = texturePrefix + ":";
		mat = material;
	}
}
