package com.igauntlet.util.handlers;

import com.igauntlet.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {

    public static SoundEvent GAUNTLET_HUM, SNAP, AWESOMEMIX;

    public static void registerSounds() {
        GAUNTLET_HUM = registerSound("gauntlethum");
        SNAP = registerSound("snap");
        AWESOMEMIX = registerSound("awesome_mix");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation location = new ResourceLocation(Reference.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}

