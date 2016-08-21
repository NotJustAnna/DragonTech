package cf.brforgers.mods.DragonTech.common.world;

import cf.brforgers.core.lib.GridSystem;
import cf.brforgers.core.lib.Utils;
import cf.brforgers.core.lib.batch.TickBatchExecutor;
import cf.brforgers.mods.DragonTech.Lib;
import cf.brforgers.mods.DragonTech.common.DT;
import cf.brforgers.mods.DragonTech.common.virus.EnumVirusState;
import cf.brforgers.mods.DragonTech.common.world.blocks.BlockDragonCrystal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

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
        if (virus) initVirus();
        if (ore) initOre();
    }

    private static void initOre() {
        //Dunno what to add
    }

    private static void initVirus() {
        //Prepare GridSystem
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        this.generate(random, chunkX, chunkZ, world, world.provider.getDimension());
    }

    public void generate(Random random, int x, int y, World world, int dim) {
        DTDimManager d = DTDimManager.getDimension(dim);
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
