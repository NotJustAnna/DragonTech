package cf.brforgers.mods.DragonScalesEX.common.blocks.world;

import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
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
        BlockVirusBase.randomTick(world, x, y, z, random);
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        final int orientation = metadata & 12;
        if (orientation == 0 && (side == 1 || side == 0) || orientation == 4 && (side == 5 || side == 4) || orientation == 8 && (side == 2 || side == 3))
        {
            return textures[1];
        }
        
        return textures[0];
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
