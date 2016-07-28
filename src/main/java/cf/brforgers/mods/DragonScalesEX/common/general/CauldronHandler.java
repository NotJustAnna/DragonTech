package cf.brforgers.mods.DragonScalesEX.common.general;

import cf.brforgers.api.DragonScalesEX.DragonScalesEXAPI;
import cf.brforgers.api.DragonScalesEX.cauldron.ICauldronRecipe;
import cf.brforgers.core.lib.world.WorldBlockPos;
import cf.brforgers.mods.DragonScalesEX.common.DSEX;
import cf.brforgers.mods.DragonScalesEX.common.general.blocks.BlockModCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CauldronHandler {
    public static void performCauldronInteraction(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) return; //Check if World is Local

        if (!checkCauldronIntegrity(world, pos, state)) return;
        if (isLocked(world, pos, state)) return;
        if (heldItem != null && !player.canPlayerEdit(pos, side, heldItem)) return;

        if (heldItem == null) return;
        tryPerformCauldronRecipe(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
    }

    private static boolean checkCauldronIntegrity(World world, BlockPos pos, IBlockState state) {
        if (!state.getBlock().equals(DSEX.CAULDRON)) { //Something is trolling us
            try {
                throw new IllegalStateException("Block isn't a DSEX Cauldron");
            } catch (Exception e) { //Let's forge a Exception to get an StackTrace
                DSEX.LOGGER.warn("Forged attempt with Cauldron Interaction detected. REPORT THIS TO THE MODDER.", e);
            }
            return false;
        }

        if (state.getValue(BlockModCauldron.LEVEL) == 0) { //Fix the Cauldron if it is with no Water
            DSEX.LOGGER.warn("DSEX Cauldron is empty. This should be nothing to worry, but this is being warned if weird behaviour happens.");
            world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
            return false;
        }

        return true;
    }

    private static boolean isLocked(World world, BlockPos pos, IBlockState state) {
        if (world.getBlockState(pos.up()).getBlock().equals(DSEX.CAULDRON_CONSTRUCT)) {
            return true;
        }

        return false;
    }

    public static void tryPerformCauldronRecipe(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        //WorldBlockPos worldPos = new WorldBlockPos(params.world, params.position);
        int level = world.getBlockState(pos).getValue(BlockModCauldron.LEVEL);

        WorldBlockPos wp = new WorldBlockPos(world, pos);

        ICauldronRecipe recipe = DragonScalesEXAPI.getValidRecipe(wp, heldItem, hand, level);

        if (recipe != null) {
            ItemStack stack = heldItem;
            ItemStack out = recipe.getOutput(wp, heldItem, hand, level);

            stack.stackSize -= recipe.getItemCost(wp, heldItem, hand, level);

            setWaterLevel(world, pos, state, level - recipe.getEssentiaCost(wp, heldItem, hand, level));

            if (stack.stackSize == 0) {
                player.setHeldItem(hand, out);
            } else if (!player.inventory.addItemStackToInventory(out)) {
                player.dropItem(out, false);
            } else if (player instanceof EntityPlayerMP) {
                ((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
            }
        }
    }

    public static void setWaterLevel(World worldIn, BlockPos pos, IBlockState state, int level) {
        worldIn.setBlockState(pos, state.withProperty(BlockModCauldron.LEVEL, Integer.valueOf(MathHelper.clamp_int(level, 0, 3))), 2);
        worldIn.updateComparatorOutputLevel(pos, state.getBlock());
    }
}
