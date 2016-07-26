package cf.brforgers.api.DragonScalesEX.utils;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlockActivationParams {
    public final World world;
    public final BlockPos position;
    public final EntityPlayer player;
    public final EnumHand hand;
    public final ItemStack heldItem;
    public final EnumFacing side;
    public final Vec3d hit;


    public BlockActivationParams(World world, BlockPos position, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, Vec3d hit) {
        this.world = world;
        this.position = position;
        this.player = player;
        this.heldItem = heldItem;
        this.side = side;
        this.hit = hit;
        this.hand = hand;
    }

    public boolean activate(Block block) {
        return block.onBlockActivated(world, position, world.getBlockState(position), player, hand, heldItem, side, (float) hit.xCoord, (float) hit.yCoord, (float) hit.zCoord);
    }
}