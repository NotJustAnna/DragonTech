package cf.brforgers.mods.DragonScalesEX.common.blocks;

import cf.brforgers.mods.DragonScalesEX.common.DSEXManager;
import net.minecraft.block.BlockOre;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockDragonEssenceOre extends BlockOre {
    private Random rand = new Random();

    public BlockDragonEssenceOre()
	{
		super();
	}

	public Item getItemDropped(int ignored1, Random ignored2, int ignored3)
    {
        return DSEXManager.DRAGON_ESSENCE_SHARD;
    }

    public int quantityDropped(Random rand)
    {
        return MathHelper.getRandomIntegerInRange(rand, 1, 2);
    }
    
    @Override
    public int getExpDrop(IBlockAccess ignored1, int ignored2, int ignored3)
    {
    	return MathHelper.getRandomIntegerInRange(rand, 3, 7);
    }
}
