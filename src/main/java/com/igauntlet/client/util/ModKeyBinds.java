package com.igauntlet.client.util;

import com.igauntlet.Infinity;
import com.igauntlet.network.NetworkHandler;
import com.igauntlet.network.packets.MessageSnap;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ModKeyBinds {

    public static KeyBinding SNAP;

    public static void init() {
        SNAP = new KeyBinding(Infinity.MODID + ".keybinds.snap", Keyboard.KEY_M, Infinity.NAME);
        ClientRegistry.registerKeyBinding(SNAP);
    }

    @SubscribeEvent
    public static void onClientTick(InputUpdateEvent e) {

        if (ModKeyBinds.SNAP.isPressed()) {
            NetworkHandler.NETWORK.sendToServer(new MessageSnap());
        }
    }
}