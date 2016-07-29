package cf.brforgers.mods.DragonTech.common.general.events;


import cf.brforgers.api.DragonTech.armor.IEventArmor;
import cf.brforgers.mods.DragonTech.common.DSEX;
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

import static cf.brforgers.mods.DragonTech.common.utils.ArmorUtils.getCurrentArmor;

public class EventHandler {
    @SuppressWarnings("unchecked")
    private Map<UUID, ItemStack>[] armorMaps = new Map[]{new HashMap<UUID, ItemStack>(), new HashMap<UUID, ItemStack>(), new HashMap<UUID, ItemStack>(), new HashMap<UUID, ItemStack>()};

    @SubscribeEvent
    public void dragonDrops(LivingDropsEvent event) {
        if (!event.getEntity().worldObj.isRemote && (
                (event.getEntity() instanceof EntityDragon) || (
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
        if (event.player.worldObj.isRemote) return;
        EntityPlayer player = event.player;
        UUID uuid = EntityPlayer.getUUID(player.getGameProfile());
        ItemStack[] cur = getCurrentArmor(player), reg = getRegisteredArmor(player);

        for (int i = 0; i < 4; i++) {
            if (cur[i] != reg[i]) {
                if (reg[i].getItem() instanceof IEventArmor) {
                    ((IEventArmor) reg[i].getItem()).onArmorUnworn(player.worldObj, player, reg[i]);
                }
                if (cur[i].getItem() instanceof IEventArmor) {
                    ((IEventArmor) cur[i].getItem()).onArmorWorn(player.worldObj, player, cur[i]);
                }
                armorMaps[i].put(uuid, cur[i]);
            }
        }
    }

    @SubscribeEvent
    public void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player.worldObj.isRemote) return;

        ItemStack[] armor = getCurrentArmor(event.player);
        UUID uuid = EntityPlayer.getUUID(event.player.getGameProfile());
        for (int i = 0; i < 4; i++) armorMaps[i].put(uuid, armor[i]);
    }

    private ItemStack[] getRegisteredArmor(EntityPlayer player) {
        UUID uuid = EntityPlayer.getUUID(player.getGameProfile());
        ItemStack[] stacks = new ItemStack[4];
        for (int i = 0; i < 4; i++) stacks[i] = armorMaps[i].get(uuid);
        return stacks;
    }

    @SubscribeEvent
    public void easterEggDrops(LivingDeathEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();

            if (player.getDisplayNameString().equals("AdrianTodt")) {
                player.dropItem(new ItemStack(DSEX.DRAGON_SCALE, player.worldObj.rand.nextInt(5) + 1), true, false);
                player.dropItem(new ItemStack(DSEX.DRAGON_ESSENCE_SHARD, player.worldObj.rand.nextInt(5) + 1), true, false);
            }
        }
    }
}