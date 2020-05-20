package com.jmteam.igauntlet.client.init;

import net.java.games.input.Keyboard;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;


public class InfinityKeyBinds {

    public static KeyBinding SNAP;

    public static void init() {
        SNAP = new KeyBinding("keybind.gauntlet.snap", GLFW.GLFW_KEY_M, "main.mod_name");
        ClientRegistry.registerKeyBinding(SNAP);
    }
}
