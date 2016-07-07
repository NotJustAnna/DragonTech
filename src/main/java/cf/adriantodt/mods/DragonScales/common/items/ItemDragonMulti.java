package cf.adriantodt.mods.DragonScales.common.items;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import cf.adriantodt.api.DragonScales.DragonScalesAPI;
import cf.adriantodt.mods.DragonScales.DragonScalesEX;
import cf.adriantodt.mods.DragonScales.Lib;
import cpw.mods.fml.common.eventhandler.Event.Result;


public class ItemDragonMulti extends ItemPickaxe
{
	private Item pickaxe, axe, shovel, hoe;
	
	private class InternalMultiToolPickaxe extends ItemPickaxe {public InternalMultiToolPickaxe(ToolMaterial material) {super(material);efficiencyOnProperMaterial *= 16.0F;}}
	private class InternalMultiToolShovel extends ItemSpade {public InternalMultiToolShovel(ToolMaterial material) {super(material);efficiencyOnProperMaterial *= 16.0F;}}
	private class InternalMultiToolAxe extends ItemAxe {public InternalMultiToolAxe(ToolMaterial material) {super(material);efficiencyOnProperMaterial *= 16.0F;}}
	private class InternalMultiToolHoe extends ItemHoe {public InternalMultiToolHoe(ToolMaterial material) {super(material);efficiencyOnProperMaterial *= 16.0F;}}
	
    public ItemDragonMulti(ToolMaterial material) {
    	super (material);
        pickaxe = new InternalMultiToolPickaxe(material);
        shovel = new InternalMultiToolShovel(material);
        axe = new InternalMultiToolAxe(material);
        hoe = new InternalMultiToolHoe(material);
        this.setCreativeTab(DragonScalesEX.tabDragonScales);
	}

	@Override
    public boolean func_150897_b(Block block) {
        return pickaxe.func_150897_b(block) || axe.func_150897_b(block) || shovel.func_150897_b(block) || hoe.func_150897_b(block);
    }

    @Override
    public float func_150893_a(ItemStack stack, Block block) {
    	//Maior, mais rápido
    	return Collections.max(
    		Arrays.asList(
    			new Float[]{
    				pickaxe.func_150893_a(stack, block),
        			axe.func_150893_a(stack, block),
        			shovel.func_150893_a(stack, block),
        			hoe.func_150893_a(stack, block),
        			DragonScalesAPI.getSpeed(block),
        			4.0f
    			}
    		)
    	).floatValue();
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("pickaxe", "axe", "shovel");
    }

    @Override
    
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    	boolean used = false;
    	for (int ix = -1; ix < 2; ix++) {
    		for (int iz = -1; iz < 2; iz++) {
    			for (int iy = -1; iy < 2; iy++) {
    				if(world.isAirBlock(x+ix, y+iy+1, z+iz)) {
    					boolean usedNow = hoe.onItemUse(stack, player, world, x+ix, y+iy, z+iz, side, hitY, hitY, hitZ);
    					if (usedNow) {
    						used = true;
    						break;
    					}
    				}
    			}
    		}
		}
    	return used;
    }
    
	public EnumRarity getRarity(ItemStack ignored)
	{
		return EnumRarity.rare;
	}
}