package com.util;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Config(modid = Reference.MODID)
@Config.LangKey("infinity.config.title")

public class ModConfig {

    public static final Gauntlet Gauntlet = new Gauntlet();

    public static class Gauntlet {

        @Config.Comment("Shift + left Click to kill Entities in range")
        public boolean Snap = true;

    }

    @Mod.EventBusSubscriber(modid = Reference.MODID)
    private static class EventHandler {

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Reference.MODID)) {
                ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
