package com.igauntlet.client.util;

import com.igauntlet.util.Reference;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class ModKeyBinds {

    public static KeyBinding SNAP;

    public static void init(){
        SNAP = new KeyBinding(Reference.MODID+".keybinds.snap", Keyboard.KEY_C, Reference.NAME);
        ClientRegistry.registerKeyBinding(SNAP);
    }
}