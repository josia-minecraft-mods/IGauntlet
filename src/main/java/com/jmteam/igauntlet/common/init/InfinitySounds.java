package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.util.registry.InfinityRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class InfinitySounds {

    public static RegistryObject<SoundEvent> AWESOMEMIX = addSound("awesome_mix");
    public static RegistryObject<SoundEvent> SNAP = addSound("snap");
    public static RegistryObject<SoundEvent> GAUNTLET_HUM  = addSound("gauntlet_hum");
    public static RegistryObject<SoundEvent> IDONTFEELGOOD = addSound("feel_good");

    public static RegistryObject<SoundEvent> addSound(String name) {
        ResourceLocation location = new ResourceLocation(IGauntlet.MODID, name);
        SoundEvent soundEvent = new SoundEvent(location);
        return InfinityRegistry.SOUND_REGISTRY.register(name, () -> soundEvent);
    }
}
