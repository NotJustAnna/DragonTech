package cf.adriantodt.mods.DragonScales.common.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cf.adriantodt.mods.DragonScales.DragonScales;
import cf.adriantodt.mods.DragonScales.common.DragonScalesHandler;
import cf.brforgers.core.lib.Utils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.relauncher.ModListHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DraconyVirus {
	private static DraconyVirus instance = null;
	public static DraconyVirus Instance() {
		if (instance == null) instance = new DraconyVirus();
		return instance;
	}
	public static void Register() {
		Utils.addEventsToBus(Instance());
	}
	private List<Runnable> executions = new ArrayList<Runnable>();
	public List<Runnable> nextTickExecutions = new ArrayList<Runnable>();
	@SubscribeEvent
	public void tickEvent(ServerTickEvent e) {
		executions.addAll(nextTickExecutions);
		nextTickExecutions = new ArrayList<Runnable>();
		long masterStarted = System.currentTimeMillis();
		int runs = 0, total = executions.size();
		for (Iterator riterator = executions.iterator(); riterator.hasNext();) {
			Runnable runnable = (Runnable) riterator.next();
			if (System.currentTimeMillis() < (masterStarted + 4)) {
				runnable.run();
				runs++;
				riterator.remove();
			}
			else break;
		}
		if (runs > 0) DragonScales.logger.info("Runned "+runs+" Generations from "+total+" ("+(runs*100/total)+"%)");
	}
	
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
        
        if(b == Blocks.stone) {
        	world.setBlock(x, y, z, DragonScalesHandler.dragonStone);
        	return true;
		}
        
        if (b == Blocks.log || b == Blocks.log2) {
            world.setBlock(x, y, z, DragonScalesHandler.draconyLog);
            return true;
        }
        
        if (b == Blocks.leaves || b == Blocks.leaves2) {
            world.setBlock(x, y, z, DragonScalesHandler.draconyLeaves);
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
	 * Massively Procriates the Virus. The Spread is how much blocks it will be spread<br>
	 * Should be primarily used for World Gen.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param Spread
	 */
	public static void InfectBiome(World world, int BlockX, int BlockY, int BlockZ, int Spread) {
		DragonScales.logger.info("Spread begun at ["+(BlockX)+","+BlockY+","+(BlockZ)+"] with Rate (" + Spread + ")");
		InfectBiomeRecursive(world, BlockX, BlockY, BlockZ, Spread, System.currentTimeMillis());
	}
	
	private static void InfectBiomeRecursive(World world, int x, int y, int z, int Spread, long timeAtStartup) {
		Spread = Math.min(Spread, 512);
		if (world.rand.nextInt(10)>Spread) return;
		for(int x1 = -1; x1 <=1; x1++) for(int y1 = 1; y1 >=-1; y1--) for(int z1 = -1; z1 <=1; z1++) {
			if(ConvertBlock(world, x+x1, y+y1, z+z1)) {
				if (System.currentTimeMillis() >= (timeAtStartup + 1500)) return;
				InfectBiomeRecursive(world, x+x1, y+y1, z+z1, Spread-1, timeAtStartup);
			}
		}
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
		DragonScales.logger.info("Spread begun at ["+(BlockX)+","+BlockY+","+(BlockZ)+"] with Rate (" + Spread + ")");
		DraconyVirus spreader = new DraconyVirus();
		InfectBiomeRecursiveAsync(world, BlockX, BlockY, BlockZ, Spread);
	}
	
	private static void InfectBiomeRecursiveAsync(final World world, final int x, final int y, final int z, int Spread) {
		final int spreadDepth = Math.min(Spread, 512);
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
				Instance().nextTickExecutions.add(new Runnable() { public void run() {
					if(ConvertBlock(world, x2, y2, z2)) {
						Instance().nextTickExecutions.add(new Runnable() { public void run() {
								InfectBiomeRecursiveAsync(world, x2, y2, z2, spreadDepth-1);
							}
						});
					}
				}
				});
			}
	}
}
