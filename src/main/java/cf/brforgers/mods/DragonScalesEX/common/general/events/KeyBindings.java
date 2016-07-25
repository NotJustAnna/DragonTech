package cf.brforgers.mods.DragonScalesEX.common.general.events;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class KeyBindings {
    public static KeyBinding dragonKeyJump;
    public static boolean DragonKeyJump = false;
	public static boolean DragonKeyJump2 = false;

    public static void init() {
    	dragonKeyJump = new KeyBinding("Ascend Dragon", Keyboard.KEY_F, "key.categories.dragonscalesmod");
        ClientRegistry.registerKeyBinding(dragonKeyJump);
    }
    
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        DragonKeyJump2 = KeyBindings.dragonKeyJump.isPressed();
    }
}