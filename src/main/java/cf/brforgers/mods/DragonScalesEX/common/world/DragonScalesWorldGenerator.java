package cf.brforgers.mods.DragonScalesEX.common.world;

import cf.brforgers.core.lib.world.WorldBlockPos;
import cf.brforgers.mods.DragonScalesEX.Lib;
import cf.brforgers.mods.DragonScalesEX.common.virus.DraconyVirus;
import cf.brforgers.mods.DragonScalesEX.common.virus.utils.DVUtils;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class DragonScalesWorldGenerator implements IWorldGenerator {
    /**
     * Number of failed generations, clamped to 500
     * For each fail, 0,1% is added
     */
	private int fails = 0;

	@Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int blockX = chunkX * 16, blockZ = chunkZ * 16;
		switch (world.provider.getDimension()) {
			case -1:
				generateNether(world, random, blockX, blockZ);
				break;
			case 0:
				generateSurface(world, random, blockX, blockZ);
				generateVirus(world, random, blockX, blockZ);
				break;
			case 1:
				generateEnd(world, random, blockX, blockZ);
				break;
		}
	}

	private void generateEnd(World world, Random rand, int BlockX, int BlockZ) {
		int baseY, baseX, baseZ;
		for(int i=0; i<10;i++) {
			baseX = BlockX; baseY = 48; baseZ = BlockZ;
			baseX += rand.nextInt(16);
			baseY += rand.nextInt(16);
			baseZ += rand.nextInt(16);
			DSEXWorld.generateOre(world, baseX, baseY, baseZ, 5, 5, 5, Blocks.end_stone);
		}
	}

	private void generateNether(World world, Random rand, int BlockX, int BlockZ) {
		int baseY, baseX, baseZ;
		for(int i=0; i<5;i++) {
			baseX = BlockX; baseY = 0; baseZ = BlockZ;
			baseX += rand.nextInt(16);
			baseY += rand.nextInt(16);
			baseZ += rand.nextInt(16);
            tryGenerateOreOnceAsync(world, baseX, baseY, baseZ, 5, 5, 5, Blocks.netherrack);
            tryGenerateOreOnceAsync(world, baseX, baseY, baseZ, 5, 5, 5, Blocks.bedrock);
        }
		for(int i=0; i<5;i++) {
			baseX = BlockX; baseY = 128; baseZ = BlockZ;
			baseX += rand.nextInt(16);
			baseY -= rand.nextInt(16);
			baseZ += rand.nextInt(16);
            tryGenerateOreOnceAsync(world, baseX, baseY, baseZ, 5, 5, 5, Blocks.netherrack);
            tryGenerateOreOnceAsync(world, baseX, baseY, baseZ, 5, 5, 5, Blocks.bedrock);
        }
	}

    private void generateSurface(World world, Random rand, int BlockX, int BlockZ) {
		int baseY, baseX, baseZ;
		int gens = 5;
		if(world.getBiomeGenForCoords(BlockX, BlockZ).equals(BiomeGenBase.extremeHills)) {
			gens = 8;
		}
		if(world.getBiomeGenForCoords(BlockX, BlockZ).equals(BiomeGenBase.extremeHillsEdge)) {
			gens = 6;
		}
		if(world.getBiomeGenForCoords(BlockX, BlockZ).equals(BiomeGenBase.extremeHillsPlus)) {
			gens = 10;
		}
		for(int i =0; i<gens;i++) {
			baseX = BlockX; baseY = 8; baseZ = BlockZ;
			baseX += rand.nextInt(16);
			baseY += rand.nextInt(32);
			baseZ += rand.nextInt(16);
            tryGenerateOreOnceAsync(world, baseX, baseY, baseZ, 5, 5, 5, Blocks.stone);
        }
	}
	
	private void generateVirus(World world, Random rand, int BlockX, int BlockZ) {
		if (Lib.Config.DraconyVirus_ChanceMultiplier == 0) return;
		
		int chance = 0, spread = 0;
		if(world.getBiomeGenForCoords(BlockX, BlockZ).equals(BiomeGenBase.extremeHills)) {
			chance = 5 + rand.nextInt(6); spread = 5 + rand.nextInt(2);
		}
		if(world.getBiomeGenForCoords(BlockX, BlockZ).equals(BiomeGenBase.extremeHillsEdge)) {
			chance = 4 + rand.nextInt(6); spread = 7 + rand.nextInt(2);
		}
		if(world.getBiomeGenForCoords(BlockX, BlockZ).equals(BiomeGenBase.extremeHillsPlus)) {
			chance = 8; spread = 11 + rand.nextInt(2);
		} else {
			chance = rand.nextInt(5)-(4/(Lib.Config.DraconyVirus_ChanceMultiplier+1)); spread = 7 + rand.nextInt(2);
		}
		chance *= Lib.Config.DraconyVirus_ChanceMultiplier;
		spread *= Lib.Config.DraconyVirus_SpreadingMultiplier;
		
		if (rand.nextInt(1000) <= (chance + fails * chance)) {
			for (int y = 128; y > 63; y--) {
				int x = rand.nextInt(16), z = rand.nextInt(16);
				BlockPos pos = new BlockPos(x, y, z);
				if (DVUtils.canConvertBlock(world.getBlockState(pos))) {
						fails = 0;
					DraconyVirus.createAt(DSEXWorld.batchExecutor, new WorldBlockPos(world, BlockX + x, y, BlockZ + z), spread);
					return;
				} else if (!world.isAirBlock(pos)) {
						if (!world.canBlockSeeTheSky(x, y, z)) break;
					}
			}
		}
		fails = MathHelper.clamp_int(fails + 1,  0, 500);
		
		if(fails == 500) {
			//DVUtils.InfectBiome(blocks, BlockX+rand.nextInt(16), 63, BlockZ+rand.nextInt(16), spread);
			DVUtils.InfectBiomeAsync(world, BlockX + rand.nextInt(16), 63, BlockZ + rand.nextInt(16), spread);
            fails = 0;
		}

    }
}