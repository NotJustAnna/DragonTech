package cf.brforgers.mods.DragonScalesEX.common.blocks.world;

import cf.brforgers.mods.DragonScalesEX.common.DSEXManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

public class DraconyLeaves extends BlockLeavesBase implements IShearable
{
    private static final int METADATA_BITMASK       = 0x1; // 0001
    private static final int METADATA_USERPLACEDBIT = 0x2; // 0010
    private static final int METADATA_DECAYBIT      = 0x4; // 0100
    private static final int METADATA_CLEARDECAYBIT = -METADATA_DECAYBIT - 1;
    int[] adjacentTreeBlocks;
    private ItemStack sapling = new ItemStack(Blocks.sapling);
    private IIcon[] textures = {null, null};

    public DraconyLeaves() {
        super(Material.leaves, false);
        this.setTickRandomly(true);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setStepSound(soundTypeGrass);
    }
    
    static private int clearDecayOnMetadata(int metadata)
    {
        return metadata & METADATA_CLEARDECAYBIT;
    }
    
    private static boolean isDecaying(int metadata)
    {
        return (metadata & METADATA_DECAYBIT) != 0;
    }
    
    private static boolean isUserPlaced(int metadata)
    {
        return (metadata & METADATA_USERPLACEDBIT) != 0;
    }
    
    private static int setDecayOnMetadata(int metadata)
    {
        return metadata | METADATA_DECAYBIT;
    }
    
    private static int unmarkedMetadata(int metadata)
    {
        return metadata & METADATA_BITMASK;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	textures[0] = iconRegister.registerIcon(this.getTextureName());
        textures[1] = iconRegister.registerIcon(this.getTextureName()+"_solid");
        this.blockIcon = textures[0];
    }
    
    @Override
    public void beginLeavesDecay(World world, int x, int y, int z)
    {
        world.setBlockMetadataWithNotify(x, y, z, setDecayOnMetadata(world.getBlockMetadata(x, y, z)), 3);
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block _block, int metadata)
    {
        final int leafDecayRadius = 1;
        
        final int chuckCheckRadius = leafDecayRadius + 1;
        if (!world.checkChunksExist(x - chuckCheckRadius, y - chuckCheckRadius, z - chuckCheckRadius, x + chuckCheckRadius, y + chuckCheckRadius, z + chuckCheckRadius))
            return;
        
        for (int x1 = -leafDecayRadius; x1 <= leafDecayRadius; ++x1)
        {
            for (int y1 = -leafDecayRadius; y1 <= leafDecayRadius; ++y1)
            {
                for (int z1 = -leafDecayRadius; z1 <= leafDecayRadius; ++z1)
                {
                    final Block block = world.getBlock(x + x1, y + y1, z + z1);
                    
                    if (block != null)
                    {
                        block.beginLeavesDecay(world, x + x1, y + y1, z + z1);
                    }
                }
            }
        }
    }
    
    private void doSaplingDrop(World world, int x, int y, int z, int metadata, int par7)
    {
        final Item itemDropped = getItemDropped(metadata, world.rand, par7);
        final int damageDropped = damageDropped(metadata);
        dropBlockAsItem(world, x, y, z, new ItemStack(itemDropped, 1, damageDropped));
    }
    
    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int par7)
    {
        leafTypeDropper(world, world, x, y, z, metadata, par7);
    }
    
    private void leafTypeDropper(IBlockAccess iBlockAccess, World world, int x, int y, int z, int metadata, int par7)
    {
        final int damageValue = unmarkedMetadata(iBlockAccess.getBlockMetadata(x, y, z));
        if (world.isRemote)
            return;
        
        
        if (world.rand.nextInt(20) == 0)
        {
            doSaplingDrop(world, x, y, z, metadata, par7);
        }
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        return textures[(!isOpaqueCube() ? 0 : 1)];
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return unmarkedMetadata(world.getBlockMetadata(x, y, z));
    }
    
    @Override
    public void harvestBlock(World world, final EntityPlayer player, final int x, final int y, final int z, final int md)
    {
        super.harvestBlock(world, player, x, y, z, md);
    }
    
    @Override
    public Item getItemDropped(int metadata, Random rand, int par3)
    {
        
        return sapling.getItem();
    }
    
    @Override
    public boolean isLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return Blocks.leaves.isOpaqueCube();
    }
    
    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
    
    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        beginLeavesDecay(world, x, y, z);
    }
    
    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
        final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, unmarkedMetadata(world.getBlockMetadata(x, y, z))));
        return ret;
    }
    
    @Override
    public int quantityDropped(Random rand)
    {
        return rand.nextInt(20) == 0 ? 1 : 0;
    }
    
    private void removeLeaves(World world, int x, int y, int z)
    {
        dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        world.setBlockToAir(x, y, z);
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5)
    {
        this.field_150121_P = !DSEXManager.draconyLeaves.isOpaqueCube(); // fix leaf render
                                                      // bug
        return super.shouldSideBeRendered(par1iBlockAccess, par2, par3, par4, par5);
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (world.isRemote)
            return;
        
        BlockVirusBase.randomTick(world, x, y, z, random);
        
        final int metadata = world.getBlockMetadata(x, y, z);
        
        if (isUserPlaced(metadata) || !isDecaying(metadata))
            return;
        
        final int rangeWood =  8;
        final int rangeCheckChunk = rangeWood + 1;
        final byte var9 = 32;
        final int var10 = var9 * var9;
        final int var11 = var9 / 2;
        final int leafRange = 10;
        
        if (adjacentTreeBlocks == null)
        {
            adjacentTreeBlocks = new int[var9 * var9 * var9];
        }
        
        if (world.checkChunksExist(x - rangeCheckChunk, y - rangeCheckChunk, z - rangeCheckChunk, x + rangeCheckChunk, y + rangeCheckChunk, z + rangeCheckChunk))
        {
            
            for (int var12 = -rangeWood; var12 <= rangeWood; ++var12)
            {
                for (int var13 = -rangeWood; var13 <= rangeWood; ++var13)
                {
                    for (int var14 = -rangeWood; var14 <= rangeWood; ++var14)
                    {
                        final Block block = world.getBlock(x + var12, y + var13, z + var14);
                        
                        if (block != null && block.canSustainLeaves(world, x + var12, y + var13, z + var14))
                        {
                            adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
                        }
                        else if (block != null && block.isLeaves(world, x + var12, y + var13, z + var14))
                        {
                            adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -2;
                        }
                        else
                        {
                            adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -1;
                        }
                    }
                }
            }
            
            for (int var12 = 1; var12 <= leafRange; ++var12)
            {
                for (int var13 = -rangeWood; var13 <= rangeWood; ++var13)
                {
                    for (int var14 = -rangeWood; var14 <= rangeWood; ++var14)
                    {
                        for (int var15 = -rangeWood; var15 <= rangeWood; ++var15)
                        {
                            if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11] == var12 - 1)
                            {
                                if (adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2)
                                {
                                    adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                }
                                
                                if (adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2)
                                {
                                    adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                }
                                
                                if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] == -2)
                                {
                                    adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] = var12;
                                }
                                
                                if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] == -2)
                                {
                                    adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] = var12;
                                }
                                
                                if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 - 1] == -2)
                                {
                                    adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 - 1] = var12;
                                }
                                
                                if (adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] == -2)
                                {
                                    adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] = var12;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        if (adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11] >= 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, clearDecayOnMetadata(metadata), 3);
        }
        else
        {
            removeLeaves(world, x, y, z);
        }
    }
    
}

/*package thefreehigh.mods.DTHighlands.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import thefreehigh.mods.DTHighlands.DTHighlands;
import thefreehigh.mods.DTHighlands.Lib;

public class DraconyLeaves extends BlockLeavesBase implements IShearable
{
	//Texture-Related
	public static final String[] textureNames = new String[] {Lib.MODID + ":" + "DraconyLeaves", Lib.MODID + ":" + "DraconyLeaves_solid"};
    
    @SideOnly(Side.CLIENT)
    protected int gfxMode;
    
    protected IIcon[] textures = new IIcon[2];
    
    //Block-Related
    int[] field_150128_a;
    
    public DraconyLeaves()
    {
        super(Material.leaves, false);
        this.setTickRandomly(true);
        this.setCreativeTab(DTHighlands.tabDTHighLands);
        //this.setBlockTextureName(Lib.MODID + ":" + "DraconyLeaves");
        this.setBlockName("DraconyLog");
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setStepSound(soundTypeGrass);
    }
}*/