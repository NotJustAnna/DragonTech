package cf.adriantodt.mods.DragonScales.common.events;


import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import cf.adriantodt.mods.DragonScales.DragonScalesEX;
import cf.adriantodt.mods.DragonScales.Lib.Config;
import cf.adriantodt.mods.DragonScales.common.DragonScalesHandler;
import cf.adriantodt.mods.DragonScales.common.items.ItemDragonArmor;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import scala.actors.threadpool.Arrays;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.relauncher.Side;

public class EventHandler
{
	private static List<Runnable> executions = new ArrayList<Runnable>(), nextTickExecutions = new ArrayList<Runnable>();
	
	/**
	 * Add {@link Runnable}(s) that will be executed AsSoonAsPossibleTM to the BatchRunnableExecutor
	 */
	public static void AddRunnablesToBeExecutedASAP(Runnable... runnables) {
		nextTickExecutions.addAll(Arrays.asList(runnables));
	}
	
	@SubscribeEvent
	public void batchRunnableExecutor(ServerTickEvent event) {
		if(nextTickExecutions.size()>0) {
			executions.addAll(nextTickExecutions);
			nextTickExecutions = new ArrayList<Runnable>();
		}
		
		long masterStarted = System.currentTimeMillis();
		int runs = 0, total = executions.size();
		for (Iterator riterator = executions.iterator(); riterator.hasNext();) {
			Runnable runnable = (Runnable) riterator.next();
			if (System.currentTimeMillis() < (masterStarted + Math.min(Config.BatchExecutor_Timeout,4))) {
				runnable.run();
				runs++;
				riterator.remove();
			}
			else break;
		}
		if (runs > 0 && Config.Debug) DragonScalesEX.logger.info("Runned "+runs+" Generations from "+total+" ("+(runs*100/total)+"%)");
	}
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		EntityPlayer player = event.player;
		if (player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() instanceof ItemDragonArmor)
		{
			player.capabilities.allowFlying = true;
			player.fallDistance = 0F;
		}
		else if (!player.capabilities.isCreativeMode)
		{
			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;
		}

		//player.fallDistance = 0F; // does not seem to be working not worked out why yet.
		//if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && KeyBindings.DragonKeyJump2 == true)
		//{
		//	KeyBindings.DragonKeyJump = true;
		//} else {
		//	KeyBindings.DragonKeyJump = false;
		//}
	}
	
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