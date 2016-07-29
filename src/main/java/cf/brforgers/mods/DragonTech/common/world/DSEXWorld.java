package cf.brforgers.mods.DragonTech.common.world;

import cf.brforgers.core.lib.Utils;
import cf.brforgers.core.lib.batch.TickBatchExecutor;
import cf.brforgers.core.lib.world.WorldBlockPos;
import cf.brforgers.mods.DragonTech.Lib;
import cf.brforgers.mods.DragonTech.common.DSEXManager;
import cf.brforgers.mods.DragonTech.common.virus.EnumVirusState;
import cf.brforgers.mods.DragonTech.common.virus.utils.DVUtils;
import cf.brforgers.mods.DragonTech.common.virus.utils.GridSystem;
import net.minecraft.block.Block;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.Random;

public class DSEXWorld implements IWorldGenerator {
    public static TickBatchExecutor.Base batchExecutor = new TickBatchExecutor.Server() {{
        this.runOnTickEnd = false;
        this.discardRunnablesForEfficiencyOnOverload = true;
        this.tickTimeout = Lib.Config.BatchExecutor_Timeout;
        this.runnableOverloadMark = 100000;
        this.debug = Utils.debugFlag;
    }};
    public static GridSystem<EnumVirusState> virusGrid = new GridSystem<EnumVirusState>(8);


    private DSEXWorld() {
    }

    public static void initWorld() {
        boolean biome = true, virus = true, ore = true;
        GameRegistry.registerWorldGenerator(new DSEXWorld(), 1000);
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
        new ArrayList<Integer>().con
    }

    public void generate(Random random, int x, int y, World world, int dim) {

    }

    private void generateOreAsync(final WorldBlockPos pos, final Vec3i range, final Block underBlock) {
        DVUtils.batchExecutor.AddRunnablesToNextTick(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < range.getX(); i++) {
                    for (int j = 0; j < range.getY(); j++) {
                        for (int k = 0; k < range.getZ(); k++) {
                            if (world.rand.nextInt(4) == 3) i = -i;
                            if (world.rand.nextInt(4) == 3) j = -j;
                            if (world.rand.nextInt(4) == 3) k = -k;
                            if (world.getBlock(BaseX + i, BaseY + j, BaseZ + k).isAir(world, BaseX + i, BaseY + j, BaseZ + k) && (world.getBlock(BaseX + i, BaseY + j - 1, BaseZ + k) == underBlock || world.getBlock(BaseX + i, BaseY + j + 1, BaseZ + k) == underBlock)) {
                                world.setBlock(BaseX + i, BaseY + j, BaseZ + k, DSEXManager.dragonCrystal, i * j * k & 0x0F, 3);
                                return;
                            }
                        }
                    }
                }
            }
        });
    }
}
