package com.jmteam.igauntlet.client.init;

import com.jmteam.igauntlet.IGauntlet;
import net.java.games.input.Keyboard;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;


public class InfinityKeyBinds {

    public static InfinityKeyBinding SNAP;

    public static void init() {
        SNAP = new InfinityKeyBinding("keybind.gauntlet.snap", GLFW.GLFW_KEY_M, IGauntlet.MOD_NAME);
        ClientRegistry.registerKeyBinding(SNAP);
    }

    public static class InfinityKeyBinding extends KeyBinding {

        private boolean isAlreadyPressed = false;

        public InfinityKeyBinding(String description, int keyCode, String category) {
            super(description, keyCode, category);
        }

        public boolean shouldExecute() {
            boolean pressed = isPressed() || isKeyDown(); // Is pressed?
            boolean previousState = isAlreadyPressed; // Previous
            isAlreadyPressed = pressed; // Update pressed
            return pressed && !previousState; // Is pressed and wasn't last pressed?
        }
    }
}
