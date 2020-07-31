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
    public static SoundEvent BACKINBLACK = addSound("backinblack");

    /* Register Sound with name that is defined in sounds.json */
    private static SoundEvent addSound(String name) {
        ResourceLocation location = new ResourceLocation(IGauntlet.MODID, name);
        SoundEvent soundEvent = new SoundEvent(location).setRegistryName(location);
        SOUNDS.add(soundEvent);
        return soundEvent;
    }
}
