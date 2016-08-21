package cf.brforgers.mods.DragonTech.common.world;

import cf.brforgers.core.lib.batch.TickBatchExecutor;
import cf.brforgers.core.lib.utils.GridSystem;
import cf.brforgers.core.lib.utils.Utils;
import cf.brforgers.mods.DragonTech.Lib;
import cf.brforgers.mods.DragonTech.common.DT;
import cf.brforgers.mods.DragonTech.common.virus.DTVirus;
import cf.brforgers.mods.DragonTech.common.virus.EnumVirusState;
import cf.brforgers.mods.DragonTech.common.virus.utils.DVUtils;
import cf.brforgers.mods.DragonTech.common.world.blocks.BlockDragonCrystal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

import static cf.brforgers.mods.DragonTech.common.virus.DTVirus.rand;

public class DTWorld implements IWorldGenerator {
    public static TickBatchExecutor.Base batchExecutor = new TickBatchExecutor.Server() {{
        this.runOnTickEnd = false;
        this.discardRunnablesForEfficiencyOnOverload = true;
        this.tickTimeout = Lib.Config.BatchExecutor_Timeout;
        this.runnableOverloadMark = 100000;
        this.debug = Utils.debugFlag;
    }};

    public static GridSystem<EnumVirusState> virusGrid = new GridSystem<EnumVirusState>(8);


    private DTWorld() {
    }

    public static void initWorld() {
        boolean biome = true, virus = true, ore = true;
        GameRegistry.registerWorldGenerator(new DTWorld(), 1000);

    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        this.generate(random, new BlockPos(chunkX * 16, 0, chunkZ * 16), world, world.provider.getDimension());
    }

    public void generate(Random random, BlockPos pos, World world, int dim) {
        DTDimension info = DTDimension.get(dim);

        if (info.doGenerateOres()) generateOres(random, pos, world, info);
        if (info.doGenerateVirus()) generateVirus(random, pos, world, info);
        if (info.doGenerateIslands()) generateIslands(random, pos, world, info);
    }

    public void generateOres(Random random, BlockPos pos, World world, DTDimension dim) {

    }

    public void generateVirus(Random random, BlockPos pos, World world, DTDimension dim) {
        if (Lib.Config.DraconyVirus_ChanceMultiplier == 0) return;

        int base, chance, spread;
        base = dim.getWeight(world.getBiome(pos.up(1)));
        chance = base + rand.nextInt(6);
        spread = chance + rand.nextInt(2);

        chance *= Lib.Config.DraconyVirus_ChanceMultiplier;
        spread *= Lib.Config.DraconyVirus_SpreadingMultiplier;

        if (rand.nextInt(1000) <= (chance)) {
            for (int y = 128; y > 0; y--) {
                int x = rand.nextInt(16), z = rand.nextInt(16);
                BlockPos pos2 = pos.add(x, y, z);
                if (DVUtils.canConvertBlock(world.getBlockState(pos))) {
                    DTVirus.createAt(DTWorld.batchExecutor, world, pos2, spread);
                    return;
                } else if (!world.isAirBlock(pos2)) {
                    if (!world.canBlockSeeSky(pos2)) break;
                }
            }
        }
    }

    public void generateIslands(Random random, BlockPos pos, World world, DTDimension dim) {
        //todo Islands
    }

    private void generateOreAsync(final World world, final BlockPos pos, final Vec3i range) {
        batchExecutor.addRunnablesToNextTick(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < range.getX(); i++) {
                    for (int j = 0; j < range.getY(); j++) {
                        for (int k = 0; k < range.getZ(); k++) {
                            if (world.rand.nextInt(4) == 3) i = -i;
                            if (world.rand.nextInt(4) == 3) j = -j;
                            if (world.rand.nextInt(4) == 3) k = -k;
                            BlockPos pos2 = pos.add(i, j, k);
                            if (world.isAirBlock(pos2) && (!world.isAirBlock(pos2.up()) || !world.isAirBlock(pos2.down()))) {
                                world.setBlockState(pos2, DT.DRAGON_CRYSTAL.getDefaultState().withProperty(BlockDragonCrystal.ROTATION, (i * j * k) & 15));
                                return;
                            }
                        }
                    }
                }
            }
        });
    }
}
