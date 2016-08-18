package cf.brforgers.mods.DragonTech.common.virus.blocks;

import cf.brforgers.core.lib.world.WorldBlockPos;
import cf.brforgers.mods.DragonTech.common.DT;
import cf.brforgers.mods.DragonTech.common.virus.utils.DVUtils;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class DraconyLeaves extends BlockLeaves
{
    private ItemStack sapling = new ItemStack(DT.DRACONY_SAPLING);

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return sapling.getItem();
    }
    
    @Override
    public int quantityDropped(Random rand)
    {
        return rand.nextInt(20) == 0 ? 1 : 0;
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }
    
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote) DVUtils.procriateAt(new WorldBlockPos(world, pos));

        super.updateTick(world, pos, state, rand);
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return null;
    }

    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
        if (worldIn.rand.nextInt(chance) == 0)
        {
            spawnAsEntity(worldIn, pos, new ItemStack(Items.APPLE));
        }
    }
}