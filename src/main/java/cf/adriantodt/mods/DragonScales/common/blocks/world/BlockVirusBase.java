package cf.adriantodt.mods.DragonScales.common.blocks.world;

import java.util.Random;

import cf.adriantodt.mods.DragonScales.common.world.DraconyVirus;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockVirusBase extends Block {
	public BlockVirusBase(Material mat) {
		super(mat);
		this.setTickRandomly(true);
	}
	public void updateTick(World world, int x, int y, int z, Random random)
    {
        randomTick(world, x, y, z, random);
    }
	
	public static void randomTick(World world, int x, int y, int z, Random random)
    {
        DraconyVirus.ProcriateAt(world, x, y, z, random);
    }
}
