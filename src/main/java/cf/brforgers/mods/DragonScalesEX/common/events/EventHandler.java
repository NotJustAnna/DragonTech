package cf.brforgers.mods.DragonScalesEX.common.events;


import cf.brforgers.mods.DragonScalesEX.common.DSEX;
import cf.brforgers.mods.DragonScalesEX.common.items.ItemDragonArmor;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventHandler {
    private static Map<UUID, Boolean> haveArmorPiece = new HashMap<UUID, Boolean>();

    @SubscribeEvent
    public void DragonDrops(LivingDropsEvent event) {
        if (!event.getEntity().worldObj.isRemote && (
                (event.getEntity() instanceof EntityDragon) || (
                        EntityList.getEntityString(event.getEntity()) != null &&
                                !EntityList.getEntityString(event.getEntity()).isEmpty() && (
                                EntityList.getEntityString(event.getEntity()).equals("HardcoreEnderExpansion.Dragon") ||
                                        EntityList.getEntityString(event.getEntity()).equals("DraconicEvolution.EnderDragon") ||
                                        EntityList.getEntityString(event.getEntity()).equals("DraconicEvolution.ChaosGuardian")
                        )
                )
        )
                ) {
            int count = 30 + event.getEntity().worldObj.rand.nextInt(30);
            for (int i = 0; i < count; i++) {
                float mm = 0.3F;
                EntityItem item = new EntityItem(event.getEntity().worldObj, event.getEntity().posX - 2 + event.getEntity().worldObj.rand.nextInt(4), event.getEntity().posY - 2 + event.getEntity().worldObj.rand.nextInt(4), event.getEntity().posZ - 2 + event.getEntity().worldObj.rand.nextInt(4), new ItemStack(DSEX.DRAGON_ESSENCE_SHARD));
                item.motionX = mm * ((((float) event.getEntity().worldObj.rand.nextInt(100)) / 100F) - 0.5F);
                item.motionY = mm * ((((float) event.getEntity().worldObj.rand.nextInt(100)) / 100F) - 0.5F);
                item.motionZ = mm * ((((float) event.getEntity().worldObj.rand.nextInt(100)) / 100F) - 0.5F);
                event.getEntity().worldObj.spawnEntityInWorld(item);
            }

            count = 30 + event.getEntity().worldObj.rand.nextInt(30);
            for (int i = 0; i < count; i++) {
                float mm = 0.3F;
                EntityItem item = new EntityItem(event.getEntity().worldObj, event.getEntity().posX - 2 + event.getEntity().worldObj.rand.nextInt(4), event.getEntity().posY - 2 + event.getEntity().worldObj.rand.nextInt(4), event.getEntity().posZ - 2 + event.getEntity().worldObj.rand.nextInt(4), new ItemStack(DSEX.DRAGON_SCALE));
                item.motionX = mm * ((((float) event.getEntity().worldObj.rand.nextInt(100)) / 100F) - 0.5F);
                item.motionY = mm * ((((float) event.getEntity().worldObj.rand.nextInt(100)) / 100F) - 0.5F);
                item.motionZ = mm * ((((float) event.getEntity().worldObj.rand.nextInt(100)) / 100F) - 0.5F);
                event.getEntity().worldObj.spawnEntityInWorld(item);
            }
        }
    }

    @SubscribeEvent
    public void playerTick(PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (player.worldObj.isRemote) return;
        if (haveArmorPiece.get(player.getPersistentID()) == ItemDragonArmor.IsAnyArmor(player))
            return; //State did not changed
        haveArmorPiece.put(player.getPersistentID(), ItemDragonArmor.IsAnyArmor(player));
        ItemDragonArmor.armorTick(player);
    }

    @SubscribeEvent
    public void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        haveArmorPiece.put(event.player.getPersistentID(), ItemDragonArmor.IsAnyArmor(event.player));
        ItemDragonArmor.armorTick(event.player);
    }

    @SubscribeEvent
    public void CustomDrops(LivingDeathEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();

            if (player.getDisplayNameString().equals("AdrianTodt")) {
                player.dropItem(new ItemStack(DSEX.DRAGON_SCALE, player.worldObj.rand.nextInt(5) + 1), true, false);
                player.dropItem(new ItemStack(DSEX.DRAGON_ESSENCE_SHARD, player.worldObj.rand.nextInt(5) + 1), true, false);
            }
        }
    }
}