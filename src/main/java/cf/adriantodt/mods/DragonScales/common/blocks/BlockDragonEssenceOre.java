package cf.adriantodt.mods.DragonScales.common.blocks;

import java.util.Random;

import cf.adriantodt.mods.DragonScales.common.DragonScalesHandler;
import net.minecraft.block.BlockOre;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDragonEssenceOre extends BlockOre {
	public BlockDragonEssenceOre()
	{
		super();
	}
	
	public Item getItemDropped(int ignored1, Random ignored2, int ignored3)
    {
        return DragonScalesHandler.dragonEssenceShard;
    }

    public int quantityDropped(Random rand)
    {
        return MathHelper.getRandomIntegerInRange(rand, 1, 2);
    }

    private Random rand = new Random();
    
    @Override
    public int getExpDrop(IBlockAccess ignored1, int ignored2, int ignored3)
    {
    	return MathHelper.getRandomIntegerInRange(rand, 3, 7);
    }
}
