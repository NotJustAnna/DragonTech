package cf.adriantodt.mods.DragonScales.common.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cf.adriantodt.mods.DragonScales.DragonScalesEX;
import cf.adriantodt.mods.DragonScales.Lib.Config;
import cf.adriantodt.mods.DragonScales.common.DragonScalesHandler;
import cf.adriantodt.mods.DragonScales.common.events.EventHandler;
import cf.brforgers.core.lib.Utils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.relauncher.ModListHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DraconyVirus {
	private DraconyVirus() {}
	
	public static boolean ConvertBlock(World world, int x, int y, int z) {
		Block b = world.getBlock(x, y, z);
		if (b == DragonScalesHandler.dragonDirt || b == Blocks.dirt || b == Blocks.grass)
        {
        	if (world.getBlockLightValue(x, y + 1, z) >= 4 && world.getBlockLightOpacity(x, y + 1, z) <= 2) {
        		world.setBlock(x, y, z, DragonScalesHandler.dragonGrass);
        		if (world.getBlock(x, y + 1, z)==Blocks.snow_layer) {
					if (world.rand.nextInt(10)==1) {
						world.setBlock(x, y + 1, z, DragonScalesHandler.dragonCrystal);
					} else {
						world.setBlockToAir(x, y + 1, z);
					}
				}
        	} else {
        		world.setBlock(x, y, z, DragonScalesHandler.dragonDirt);
        		
        	}
        	return true;
        }
        
        if(b == Blocks.stone || b == Blocks.monster_egg) {
        	world.setBlock(x, y, z, DragonScalesHandler.dragonStone);
        	return true;
		}
        
        if (b == Blocks.log || b == Blocks.log2) {
            world.setBlock(x, y, z, DragonScalesHandler.draconyLog,world.getBlockMetadata(x, y, z), 3);
            return true;
        }
        
        if (b == Blocks.leaves || b == Blocks.leaves2) {
            world.setBlock(x, y, z, DragonScalesHandler.draconyLeaves);
            return true;
        }
        
        if (b == Blocks.planks) {
            world.setBlock(x, y, z, DragonScalesHandler.draconyPlanks);
            return true;
        }
        
        return false;
	}
	
	public static boolean CanConvertBlock(World world, int x, int y, int z) {
		Block b = world.getBlock(x, y, z);
		if (b == DragonScalesHandler.dragonDirt || b == Blocks.dirt || b == Blocks.grass)
        {
        	return true;
        }
        
        if(b == Blocks.stone) {;
        	return true;
		}
        
        if (b == Blocks.log || b == Blocks.log2) {
            return true;
        }
        
        if (b == Blocks.leaves || b == Blocks.leaves2) {
            return true;
        }
        
        return false;
	}
	
	/**
	 * Procriates the Virus (Assuming the Block Position being the ones that will infect adjacent ones)<br>
	 * Should be primarily used for Random Ticks, but can also be used for wands
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param random
	 */
	public static boolean ProcriateAt(World world, int x, int y, int z, Random random) {
		if (world.isRemote) return false;
		
		boolean changed = false;
		for (int l = 0; l < 4; ++l)
        {
            int i1 = x + random.nextInt(3) - 1;
            int j1 = y + random.nextInt(5) - 3;
            int k1 = z + random.nextInt(3) - 1;

            changed |= ConvertBlock(world,i1,j1,k1);
        }
		return changed;
	}
	
	/**
	 * Massively Procriates the Virus, Running it. The Spread is how much blocks it will be spread<br>
	 * Should be primarily used for World Gen.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param Spread
	 */
	public static void InfectBiomeAsync(World world, int BlockX, int BlockY, int BlockZ, int Spread) {
		Spread = Math.min(Spread, 32);
		if(Config.Debug) DragonScalesEX.logger.info("Async Spread begun at ["+(BlockX)+","+BlockY+","+(BlockZ)+"] with Rate (" + Spread + ")");
		if (ConvertBlock(world, BlockX, BlockY, BlockZ))
			InfectBiomeRecursiveAsync(world, BlockX, BlockY, BlockZ, Spread);
	}
	
	private static void InfectBiomeRecursiveAsync(final World world, final int x, final int y, final int z, final int Spread) {
		Random random = world.rand;
		if (world.rand.nextInt(10)>Spread) return;
		//for(int x1 = -1; x1 <=1; x1++) for(int y1 = 1; y1 >=-1; y1--) for(int z1 = -1; z1 <=1; z1++)
	    //    {
		//		final int x2 = x + x1;
	    //        final int y2 = y + y1;
	    //        final int z2 = z + z1;
		for (int l = 0; l < 4; ++l)
        {
            final int x2 = x + random.nextInt(3) - 1;
            final int y2 = y + random.nextInt(5) - 3;
            final int z2 = z + random.nextInt(3) - 1;
				//EventHandler.AddRunnablesToBeExecutedASAP(new Runnable() { public void run() {
					if(ConvertBlock(world, x2, y2, z2)) {
						if(Spread-1 != 0)
						EventHandler.AddRunnablesToBeExecutedASAP(new Runnable() { public void run() {
							InfectBiomeRecursiveAsync(world, x2, y2, z2, Spread-1);
						}});
					}
				//}
				//});
			}
	}
}
