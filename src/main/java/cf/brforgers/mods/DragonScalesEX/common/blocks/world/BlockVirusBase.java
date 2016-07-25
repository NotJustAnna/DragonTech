package cf.brforgers.mods.DragonScalesEX.common.blocks.world;

import cf.brforgers.core.lib.world.WorldBlockPos;
import cf.brforgers.mods.DragonScalesEX.common.DSEX;
import cf.brforgers.mods.DragonScalesEX.common.world.DVUtils;
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

    public static void randomTick(WorldBlockPos exactPos)
    {
        DVUtils.ProcriateAt(exactPos);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        randomTick(new WorldBlockPos(worldIn, pos));
    }

    public boolean isToolEffective(String type, IBlockState state)
    {
        if (type.equals("shovel") && (this == DSEX.DRAGON_GRASS || this == DSEX.DRAGON_DIRT))
            return true;
        return super.isToolEffective(type, state);
    }
}
