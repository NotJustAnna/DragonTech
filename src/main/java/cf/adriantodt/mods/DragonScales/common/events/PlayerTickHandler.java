package cf.adriantodt.mods.DragonScales.common.events;


import java.util.EnumSet;

import cf.adriantodt.mods.DragonScales.DragonScales;
import cf.adriantodt.mods.DragonScales.common.items.ItemDragonArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.relauncher.Side;

public class PlayerTickHandler
{
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
			DragonScales.logger.info("FALSE");
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
}