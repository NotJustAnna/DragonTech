package cf.brforgers.mods.DragonScalesEX.common.general.items;

import cf.brforgers.api.DragonScalesEX.DragonScalesEXAPI;
import cf.brforgers.mods.DragonScalesEX.DragonScalesEX;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;


public class ItemDragonMulti extends ItemPickaxe
{
	private Item pickaxe, axe, shovel, hoe;
	
    public ItemDragonMulti(ToolMaterial material) {
    	super (material);
        pickaxe = new InternalMultiToolPickaxe(material);
        shovel = new InternalMultiToolShovel(material);
        axe = new InternalMultiToolAxe(material);
        hoe = new InternalMultiToolHoe(material);
        this.setCreativeTab(DragonScalesEX.tabDragonScales);
	}

	@Override
    public boolean canHarvestBlock(IBlockState block) {
        return pickaxe.canHarvestBlock(block) || axe.canHarvestBlock(block) || shovel.canHarvestBlock(block) || hoe.canHarvestBlock(block);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState block) {
        //Maior, mais rápido
        return Collections.max(Arrays.asList(
                pickaxe.getStrVsBlock(stack, block),
                axe.getStrVsBlock(stack, block),
                shovel.getStrVsBlock(stack, block),
                hoe.getStrVsBlock(stack, block),
                DragonScalesEXAPI.getCustomSpeedForBlock(block.getBlock()),
                4.0f
        ));
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("pickaxe", "axe", "shovel");
    }

    @Override

    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        boolean success = false;
        for (int ix = -1; ix < 2; ix++) {
    		for (int iz = -1; iz < 2; iz++) {
    			for (int iy = -1; iy < 2; iy++) {
                    if (world.isAirBlock(new BlockPos(pos.getX() + ix, pos.getY() + iy + 1, pos.getZ() + iz))) {
                        success |= hoe.onItemUse(stack, player, world, pos, hand, facing, hitX, hitY, hitZ) == EnumActionResult.SUCCESS;
                    }
    			}
    		}
		}
        return success ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
    }

	public EnumRarity getRarity(ItemStack ignored)
	{
        return EnumRarity.RARE;
    }

    private class InternalMultiToolPickaxe extends ItemPickaxe {
        public InternalMultiToolPickaxe(ToolMaterial material) {
            super(material);
            efficiencyOnProperMaterial *= 16.0F;
        }
    }

    private class InternalMultiToolShovel extends ItemSpade {
        public InternalMultiToolShovel(ToolMaterial material) {
            super(material);
            efficiencyOnProperMaterial *= 16.0F;
        }
    }

    private class InternalMultiToolAxe extends ItemAxe {
        public InternalMultiToolAxe(ToolMaterial material) {
            super(material);
            efficiencyOnProperMaterial *= 16.0F;
        }
    }

    private class InternalMultiToolHoe extends ItemHoe {
        public InternalMultiToolHoe(ToolMaterial material) {
            super(material);
            efficiencyOnProperMaterial *= 16.0F;
        }
    }
}