package cf.brforgers.mods.DragonTech.common.virus.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class DraconyLog extends BlockLog {
    public static final PropertyEnum<EnumAxis> LOG_AXIS = PropertyEnum.create("axis", BlockLog.EnumAxis.class);

    public DraconyLog() {
        super();
        this.setTickRandomly(true);
        this.setHardness(2.0F);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState();

        switch (meta & 12) {
            case 0:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;

            case 4:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;

            case 8:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;

            case 12:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
                break;
        }

        return state;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        switch (state.getValue(LOG_AXIS)) {
            case X:
                return 4;
            case Y:
                return 0;
            case Z:
                return 8;
            case NONE:
                return 12;
            default:
                return 0;
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer.Builder(this).add(LOG_AXIS).build();
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        BlockVirusBase.randomTick(worldIn, pos);
    }
}
