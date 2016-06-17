package cf.adriantodt.mods.DragonScales.common;

import java.util.Random;

import cf.adriantodt.mods.DragonScales.DragonScales;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class DragonScalesWorldGenerator implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
			case -1: generateNether(world, random,chunkX*16,chunkZ*16); break;
			case 0 : generateSurface(world, random,chunkX*16,chunkZ*16); break;
			case 1 : generateEnd(world, random,chunkX*16,chunkZ*16); break;
		}
	}

	private void generateEnd(World world, Random rand, int BlockX, int BlockZ) {
		int baseY, baseX, baseZ;
		for(int i=0; i<10;i++) {
			baseX = BlockX; baseY = 48; baseZ = BlockZ;
			baseX += rand.nextInt(16);
			baseY += rand.nextInt(16);
			baseZ += rand.nextInt(16);
			tryGenerateOnce(world, baseX, baseY, baseZ, 5, 5, 5, Blocks.end_stone);
		}
	}

	private void generateNether(World world, Random rand, int BlockX, int BlockZ) {
		int baseY, baseX, baseZ;
		for(int i=0; i<5;i++) {
			baseX = BlockX; baseY = 0; baseZ = BlockZ;
			baseX += rand.nextInt(16);
			baseY += rand.nextInt(16);
			baseZ += rand.nextInt(16);
			if (!tryGenerateOnce(world, baseX, baseY, baseZ, 5, 5, 5, Blocks.netherrack)) {
				tryGenerateOnce(world, baseX, baseY, baseZ, 5, 5, 5, Blocks.bedrock);
			}
		}
		for(int i=0; i<5;i++) {
			baseX = BlockX; baseY = 128; baseZ = BlockZ;
			baseX += rand.nextInt(16);
			baseY -= rand.nextInt(16);
			baseZ += rand.nextInt(16);
			if (!tryGenerateOnce(world, baseX, baseY-5, baseZ, 5, 5, 5, Blocks.netherrack)) {
				tryGenerateOnce(world, baseX, baseY-5, baseZ, 5, 5, 5, Blocks.bedrock);
			}
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
		for(int i =0; i<5;i++) {
			baseX = BlockX; baseY = 8; baseZ = BlockZ;
			baseX += rand.nextInt(16);
			baseY += rand.nextInt(32);
			baseZ += rand.nextInt(16);
			tryGenerateOnce(world, baseX, baseY, baseZ, 5, 5, 5, Blocks.stone);
		}
	}
	
	private boolean tryGenerateOnce(World world, int BaseX, int BaseY, int BaseZ, int RangeX, int RangeY, int RangeZ, Block underBlock) {
		for(int i = 0; i<RangeX; i++) {
			for(int j = 0; j<RangeY; j++) {
				for(int k = 0; k<RangeZ; k++) {
					if (world.getBlock(BaseX+i, BaseY+j, BaseZ+k).isAir(world, BaseX+i, BaseY+j, BaseZ+k) && (world.getBlock(BaseX+i, BaseY+j-1, BaseZ+k) == underBlock || world.getBlock(BaseX+i, BaseY+j+1, BaseZ+k) == underBlock)) {
						world.setBlock(BaseX+i, BaseY+j, BaseZ+k, DragonScalesHandler.dragonCrystal, i*j*k & 0x0F ,3);
						return true;
					}
				}
			}
		}
		return false;
	}
}