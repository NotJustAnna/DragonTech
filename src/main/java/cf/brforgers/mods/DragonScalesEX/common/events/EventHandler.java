package cf.brforgers.mods.DragonScalesEX.common.events;


import java.util.*;

import cf.brforgers.mods.DragonScalesEX.DragonScalesEX;
import cf.brforgers.mods.DragonScalesEX.Lib.Config;
import cf.brforgers.mods.DragonScalesEX.common.DragonScalesHandler;
import cf.brforgers.mods.DragonScalesEX.common.items.ItemDragonArmor;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import scala.actors.threadpool.Arrays;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public class EventHandler
{
	private static Map<UUID, Boolean> haveArmorPiece = new HashMap<UUID,Boolean>();
	@SubscribeEvent
    public void DragonDrops(LivingDropsEvent event) {
        if (!event.entity.worldObj.isRemote && (
        		(event.entity instanceof EntityDragon) || (
        			EntityList.getEntityString(event.entity) != null && 
        			!EntityList.getEntityString(event.entity).isEmpty() && (
        				EntityList.getEntityString(event.entity).equals("HardcoreEnderExpansion.Dragon") ||
        				EntityList.getEntityString(event.entity).equals("DraconicEvolution.EnderDragon") ||
        				EntityList.getEntityString(event.entity).equals("DraconicEvolution.ChaosGuardian")
        				)
        			)
        		)
        	)
        {
            int count = 30 + event.entity.worldObj.rand.nextInt(30);
            for (int i = 0; i < count; i++) {
                float mm = 0.3F;
                EntityItem item = new EntityItem(event.entity.worldObj, event.entity.posX - 2 + event.entity.worldObj.rand.nextInt(4), event.entity.posY - 2 + event.entity.worldObj.rand.nextInt(4), event.entity.posZ - 2 + event.entity.worldObj.rand.nextInt(4), new ItemStack(DragonScalesHandler.dragonEssenceShard));
                item.motionX = mm * ((((float) event.entity.worldObj.rand.nextInt(100)) / 100F) - 0.5F);
                item.motionY = mm * ((((float) event.entity.worldObj.rand.nextInt(100)) / 100F) - 0.5F);
                item.motionZ = mm * ((((float) event.entity.worldObj.rand.nextInt(100)) / 100F) - 0.5F);
                event.entity.worldObj.spawnEntityInWorld(item);
            }
            
            count = 30 + event.entity.worldObj.rand.nextInt(30);
            for (int i = 0; i < count; i++) {
                float mm = 0.3F;
                EntityItem item = new EntityItem(event.entity.worldObj, event.entity.posX - 2 + event.entity.worldObj.rand.nextInt(4), event.entity.posY - 2 + event.entity.worldObj.rand.nextInt(4), event.entity.posZ - 2 + event.entity.worldObj.rand.nextInt(4), new ItemStack(DragonScalesHandler.dragonScale));
                item.motionX = mm * ((((float) event.entity.worldObj.rand.nextInt(100)) / 100F) - 0.5F);
                item.motionY = mm * ((((float) event.entity.worldObj.rand.nextInt(100)) / 100F) - 0.5F);
                item.motionZ = mm * ((((float) event.entity.worldObj.rand.nextInt(100)) / 100F) - 0.5F);
                event.entity.worldObj.spawnEntityInWorld(item);
            }
        }
    }

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		if(player.worldObj.isRemote) return;
		if (haveArmorPiece.get(player.getPersistentID()) == ItemDragonArmor.IsAnyArmor(player)) return; //State did not changed
		haveArmorPiece.put(player.getPersistentID(), ItemDragonArmor.IsAnyArmor(player));
		ItemDragonArmor.armorTick(player);
	}

	@SubscribeEvent
	public void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
		haveArmorPiece.put(event.player.getPersistentID(), ItemDragonArmor.IsAnyArmor(event.player));
		ItemDragonArmor.armorTick(event.player);
	}
	
	@SubscribeEvent
	public void CustomDrops(LivingDeathEvent e)
	{
		if (e.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)e.entity;
			
			if(player.getCommandSenderName().equals("AdrianTodt"))
			{
				player.dropPlayerItemWithRandomChoice(new ItemStack(DragonScalesHandler.dragonScale,player.worldObj.rand.nextInt(5)+1),true);
				player.dropPlayerItemWithRandomChoice(new ItemStack(DragonScalesHandler.dragonEssenceShard,player.worldObj.rand.nextInt(5)+1),true);
			}
		}
	}
}