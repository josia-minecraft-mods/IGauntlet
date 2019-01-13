package com.util.handlers;

import com.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler
{

    public static SoundEvent GAUNTLET_HUM,SNAP;

    public static void registerSounds()
    {
        GAUNTLET_HUM = registerSound("gauntlethum");
        SNAP = registerSound("snap");
    }

    private  static SoundEvent registerSound(String name)
    {
        ResourceLocation location = new ResourceLocation(Reference.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}

