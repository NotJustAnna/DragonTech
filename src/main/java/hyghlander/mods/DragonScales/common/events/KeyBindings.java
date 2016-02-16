package hyghlander.mods.DragonScales.common.events;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.settings.KeyBinding;

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