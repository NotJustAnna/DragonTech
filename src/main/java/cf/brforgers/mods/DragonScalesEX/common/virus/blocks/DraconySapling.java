package cf.brforgers.mods.DragonScalesEX.common.virus.blocks;

import cf.brforgers.mods.DragonScalesEX.common.DSEXManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class DraconySapling extends BlockBush implements IGrowable
{
	/* Metadata System */
	private static final int METADATA_BITMASK = 0x1; //0001
    private static final int METADATA_MARKBIT = 0x2; //0010

    /* Block Declaration */
    public DraconySapling() {
        this.setStepSound(soundTypeGrass);
        this.setHardness(0.0F);
        this.setTickRandomly(true);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }
    
    private static boolean isEnoughLightToGrow(World world, int x, int y, int z)
    {return world.getBlockLightValue(x, y, z) >= 9;}
    
    private static boolean isMarkedMetadata(int metadata)
    {return (metadata & METADATA_MARKBIT) != 0;}
    
    private static int markedMetadata(int metadata)
    {return metadata | METADATA_MARKBIT;}
    
    private static int unmarkedMetadata(int metadata)
    {return metadata & METADATA_BITMASK;}
    
    /* Override for DragonLands Grass */
    protected boolean canPlaceBlockOn(Block block)
    {
        return block == Blocks.grass || block == Blocks.dirt || block == DSEXManager.dragonGrass;
    }

    public void growTree(World world, int x, int y, int z, Random r)
    {
    	final int metadata = unmarkedMetadata(world.getBlockMetadata(x, y, z));
        WorldGenerator tree = null;
        int offX = 0;
        int offZ = 0;
        boolean isHuge = false;
        
        // Find a Huge Tree preset
        for (offX = 0; offX >= -1; --offX)
        {
        	for (offZ = 0; offZ >= -1; --offZ)
        	{
                if (isSameSapling(world, x + offX, y, z + offZ, metadata) &&
                	isSameSapling(world, x + offX + 1, y, z + offZ, metadata) &&
                	isSameSapling(world, x + offX, y, z + offZ + 1, metadata) &&
                	isSameSapling(world, x + offX + 1, y, z + offZ + 1, metadata)) isHuge = true;
                
                if (isHuge) break;
        	}
            if (isHuge) break;
        }
        
        if (isHuge)
        {
            tree = new WorldGenBigTree(true, DSEXManager.draconyLog, DSEXManager.draconyLeaves, this);
            world.setBlock(x + offX, y, z + offZ, Blocks.air);
            world.setBlock(x + offX + 1, y, z + offZ, Blocks.air);
            world.setBlock(x + offX, y, z + offZ + 1, Blocks.air);
            world.setBlock(x + offX + 1, y, z + offZ + 1, Blocks.air);
        }
        else
        {
            tree = new WorldGenTree(true, DSEXManager.draconyLog, DSEXManager.draconyLeaves, this);
            world.setBlock(x, y, z, Blocks.air);
        }
            
        if (!tree.generate(world, r, x + offX, y, z + offZ))
        {
            if (isHuge)
            {
                world.setBlock(x + offX, y, z + offZ, this, metadata, 3);
                world.setBlock(x + offX + 1, y, z + offZ, this, metadata, 3);
                world.setBlock(x + offX, y, z + offZ + 1, this, metadata, 3);
                world.setBlock(x + offX + 1, y, z + offZ + 1, this, metadata, 3);
            }
            else
            {
                world.setBlock(x, y, z, this, metadata, 3);
            }
        }
    }
    
    private void attemptGrowTree(World world, int x, int y, int z, Random r)
    {
        if (isEnoughLightToGrow(world, x, y + 1, z) && r.nextInt(7) == 0)
        {
            final int metadata = world.getBlockMetadata(x, y, z);
            
            if (!isMarkedMetadata(metadata))
            {
                world.setBlockMetadataWithNotify(x, y, z, markedMetadata(metadata), 3);
            }
            else
            {
                growTree(world, x, y, z, r);
            }
        }
    }

    public boolean func_149880_a(World world, int x, int y, int z, int meta)
    {
        return world.getBlock(x, y, z) == this && (world.getBlockMetadata(x, y, z) & 7) == meta;
    }

    /*func_149851_a is basically a stillGrowing() method.
     *  It returns (or should return) true if the growth stage is less than the max growth stage. */
    public boolean func_149851_a(World world, int x, int y, int z, boolean param5)
    {
        return true;
    }

    /*func_149852_a is basically a canBoneMealSpeedUpGrowth() method.
     *  I usually just return true, but depends on your crop. */
    public boolean func_149852_a(World world, Random r, int x, int y, int z)
    {
        return (double)world.rand.nextFloat() < 0.45D;
    }

    /*func_149853_b is basically an incrementGrowthStage() method.*/
    public void func_149853_b(World world, Random r, int x, int y, int z)
    {
        this.attemptGrowTree(world, x, y, z, r);
    }
    
    public void markOrGrowMarked(World world, int x, int y, int z, Random rand)
    {
        int marked = world.getBlockMetadata(x, y, z);
        
        if ((marked & 8) == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, marked | 8, 4);
        }
        else
        {
            this.growTree(world, x, y, z, rand);
        }
    }
    
    public boolean isSameSapling(World world, int x, int y, int z, int metadata)
    {
        return world.getBlock(x, y, z) == this && unmarkedMetadata(world.getBlockMetadata(x, y, z)) == metadata;
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
            super.updateTick(world, x, y, z, rand);
            BlockVirusBase.randomTick(world, x, y, z, rand);
            attemptGrowTree(world, x, y, z, rand);
        }
    }
    
    private boolean isSameSaplingBlock(int x, int y, int z, World world, ItemStack sapling)
    {
        Block block = world.getBlock(x, y, z);
        int metadata = world.getBlockMetadata(x, y, z);
        return block != null && block != Blocks.air && sapling.getItem() == ItemBlock.getItemFromBlock(this) && sapling.getItemDamage() == metadata;
    }
}
