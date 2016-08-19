package cf.brforgers.mods.DragonTech.common.virus.blocks;

import cf.brforgers.mods.DragonTech.common.DT;
import cf.brforgers.mods.DragonTech.common.virus.utils.DVUtils;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockVirusBase extends Block {
	public BlockVirusBase(Material mat) {
		super(mat);
        setSoundType(fromMaterial(mat));
        this.setTickRandomly(true);
	}

    private static SoundType fromMaterial(Material material) {
        if (material.equals(Material.PLANTS) || material.equals(Material.GRASS)) return SoundType.PLANT;
        if (material.equals(Material.WOOD)) return SoundType.WOOD;
        if (material.equals(Material.GROUND)) return SoundType.GROUND;
        return SoundType.STONE;
    }

    public static void randomTick(World world, BlockPos pos)
    {
        if (world.rand.nextInt(20) == 0)
            DVUtils.procriateAt(world, pos);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        randomTick(worldIn, pos);
    }

    public boolean isToolEffective(String type, IBlockState state)
    {
        if ("shovel".equals(type) && (this == DT.DRAGON_GRASS || this == DT.DRAGON_DIRT))
            return true;
        return super.isToolEffective(type, state);
    }
}
