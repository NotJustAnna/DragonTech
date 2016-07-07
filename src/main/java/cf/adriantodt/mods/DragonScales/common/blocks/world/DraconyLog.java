package cf.adriantodt.mods.DragonScales.common.blocks.world;

import java.util.List;
import java.util.Random;

import cf.adriantodt.mods.DragonScales.common.DragonScalesHandler;
import cf.adriantodt.mods.DragonScales.common.world.DraconyVirus;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DraconyLog extends BlockLog
{
    //private BlockSettings settings;
    private IIcon[] textures = { null, null, };
    
    public DraconyLog()
    {
        super();
        this.setTickRandomly(true);
        this.setHardness(2.0F);
        this.setStepSound(soundTypeWood);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        textures[0] = iconRegister.registerIcon(this.getTextureName() + "_side");
        textures[1] = iconRegister.registerIcon(this.getTextureName() + "_top");
        this.blockIcon = textures[0];
    }
    
    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        DraconyVirus.ProcriateAt(world, x, y, z, random);
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        final int orientation = metadata & 12;
        int type = metadata & 3;
        if (type > 3)
            type = 0;
        if (orientation == 0 && (side == 1 || side == 0) || orientation == 4 && (side == 5 || side == 4) || orientation == 8 && (side == 2 || side == 3))
        {
            //return texturesMap.get(index + 16 + type);
            return textures[(type * 2) + 1];
        }
        
        return textures[type * 2];
        //return texturesMap.get(index + type);
    }
    
    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
}
