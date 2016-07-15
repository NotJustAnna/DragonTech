package cf.brforgers.mods.DragonScalesEX.common.blocks.world;

import cf.brforgers.mods.DragonScalesEX.common.DragonScalesHandler;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class DragonGrass extends BlockVirusBase implements IGrowable {
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;

    public DragonGrass()
    {
        super(Material.grass);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setStepSound(soundTypeGrass);
        this.setHardness(0.6F);
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.topIcon : (side == 0 ? DragonScalesHandler.dragonDirt.getBlockTextureFromSide(side) : this.blockIcon);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (!world.isRemote)
        {
            if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
            {
                world.setBlock(x, y, z, DragonScalesHandler.dragonDirt);
            }
            else if (world.getBlockLightValue(x, y + 1, z) >= 9)
            {
                super.updateTick(world, x, y, z, random);
            }
        }
    }

    public Item getItemDropped(int p_149650_1_, Random random, int p_149650_3_)
    {
        return DragonScalesHandler.dragonDirt.getItemDropped(0, random, p_149650_3_);
    }

    public boolean func_149851_a(World world, int x, int y, int z, boolean flag)
    {
        return true;
    }

    public boolean func_149852_a(World world, Random random, int x, int y, int z)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
        this.topIcon = iconRegister.registerIcon(this.getTextureName() + "_top");
    }

    /**
     * OnBonemeal
     */
    public void func_149853_b(World world, Random random, int x, int y, int z)
    {
    	//TODO: Implement DraconyLeaves
//        int l = 0;
//
//        while (l < 128)
//        {
//            int i1 = x;
//            int j1 = y + 1;
//            int k1 = z;
//            int l1 = 0;
//
//            while (true)
//            {
//                if (l1 < l / 16)
//                {
//                    i1 += random.nextInt(3) - 1;
//                    j1 += (random.nextInt(3) - 1) * random.nextInt(3) / 2;
//                    k1 += random.nextInt(3) - 1;
//
//                    if (world.getBlock(i1, j1 - 1, k1) == Blocks.grass && !world.getBlock(i1, j1, k1).isNormalCube())
//                    {
//                        ++l1;
//                        continue;
//                    }
//                }
//                else if (world.getBlock(i1, j1, k1).getMaterial() == Material.air)
//                {
//                    if (random.nextInt(8) != 0)
//                    {
//                        if (Blocks.tallgrass.canBlockStay(world, i1, j1, k1))
//                        {
//                            world.setBlock(i1, j1, k1, Blocks.tallgrass, 1, 3);
//                        }
//                    }
//                    else
//                    {
//                        world.getBiomeGenForCoords(i1, k1).plantFlower(world, random, i1, j1, k1);
//                    }
//                }
//
//                ++l;
//                break;
//            }
//        }
    }
}
