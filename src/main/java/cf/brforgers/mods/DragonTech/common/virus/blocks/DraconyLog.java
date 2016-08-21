package cf.brforgers.mods.DragonTech.common.virus.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class DraconyLog extends BlockLog
{
    public DraconyLog()
    {
        super();
        this.setTickRandomly(true);
        this.setHardness(2.0F);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        BlockVirusBase.randomTick(worldIn, pos);
    }
}
