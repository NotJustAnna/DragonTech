package cf.brforgers.mods.DragonScalesEX.common.blocks.world;

import cf.brforgers.mods.DragonScalesEX.common.DSEXManager;
import cf.brforgers.mods.DragonScalesEX.common.world.DraconyVirus;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import java.util.Random;

public class BlockVirusBase extends Block {
	public BlockVirusBase(Material mat) {
		super(mat);
		this.setTickRandomly(true);
	}

    public static void randomTick(World world, int x, int y, int z, Random random)
    {
        DraconyVirus.ProcriateAt(world, x, y, z, random);
    }

    public void updateTick(World world, int x, int y, int z, Random random)
    {
        randomTick(world, x, y, z, random);
    }

    public boolean isToolEffective(String type, int metadata)
    {
        if ("shovel".equals(type) && (this == DSEXManager.dragonGrass || this == DSEXManager.dragonDirt))
            return true;
        return super.isToolEffective(type, metadata);
    }
}
