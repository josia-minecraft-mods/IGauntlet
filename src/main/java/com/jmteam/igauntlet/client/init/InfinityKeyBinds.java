package com.jmteam.igauntlet.util.handlers.client;

import com.jmteam.igauntlet.Infinity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ModKeyBinds {

    public static KeyBinding SNAP;
    public static KeyBinding SPECIAL;

    public static void init() {
        SNAP = new KeyBinding(Infinity.MODID + ".keybinds.snap", Keyboard.KEY_M, Infinity.NAME);
        ClientRegistry.registerKeyBinding(SNAP);
        SPECIAL = new KeyBinding(Infinity.MODID + ".keybinds.special", Keyboard.KEY_N, Infinity.NAME);
        ClientRegistry.registerKeyBinding(SPECIAL);
    }

    @SubscribeEvent
    public static void onClientTick(InputUpdateEvent e) {

        if (ModKeyBinds.SNAP.isPressed()) {
            NetworkHandler.NETWORK.sendToServer(new PacketSnap());
        }
    }
}