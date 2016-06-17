package cf.adriantodt.mods.DragonScales.common;

import java.util.Random;

import cf.adriantodt.mods.DragonScales.DragonScales;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class DragonScalesWorldGenerator implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
			//case -1: generateNether(world, random,chunkX*16,chunkZ*16);
			case 0 : generateSurface(world, random,chunkX*16,chunkZ*16);
		}
	}

	private void generateSurface(World world, Random random, int BlockX, int BlockZ) {
		for(int i =0; i<10;i++)
			new WorldGenMinable(DragonScalesHandler.dragonEssenceOre, 4)
			.generate(world, random, BlockX + random.nextInt(16), random.nextInt(16), BlockZ + random.nextInt(16));
	}
}