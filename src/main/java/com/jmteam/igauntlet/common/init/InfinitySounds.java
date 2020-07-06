package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.IGauntlet;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import java.util.ArrayList;
import java.util.List;

public class InfinitySounds {

    public static List<SoundEvent> SOUNDS = new ArrayList<>();

    public static SoundEvent AWESOMEMIX = addSound("awesome_mix");
    public static SoundEvent SNAP = addSound("snap");
    public static SoundEvent GAUNTLET_HUM  = addSound("gauntlethum");
    public static SoundEvent IDONTFEELGOOD = addSound("feelgood");

    private static SoundEvent addSound(String name) {
        SoundEvent soundEvent = new SoundEvent(new ResourceLocation(IGauntlet.MODID, name)).setRegistryName(name);
        SOUNDS.add(soundEvent);
        return soundEvent;
    }
}
