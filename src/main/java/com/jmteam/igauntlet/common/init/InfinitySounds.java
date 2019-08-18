package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.Infinity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Infinity.MODID)
public class InfinitySounds {

    private static final List<SoundEvent> SOUND_EVENTS = new ArrayList<>();
    public static SoundEvent GAUNTLET_HUM, SNAP, AWESOMEMIX, IDONTFEELGOOD;

    public static void registerSounds() {
        GAUNTLET_HUM = registerSound("gauntlethum");
        SNAP = registerSound("snap");
        AWESOMEMIX = registerSound("awesome_mix");
        IDONTFEELGOOD = registerSound("feelgood");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation location = new ResourceLocation(Infinity.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        SOUND_EVENTS.add(event);
        return event;
    }

    @SubscribeEvent
    public static void addSoundEvents(RegistryEvent.Register<SoundEvent> e) {
        IForgeRegistry<SoundEvent> reg = e.getRegistry();
        reg.registerAll(SOUND_EVENTS.toArray(new SoundEvent[0]));
    }

}

