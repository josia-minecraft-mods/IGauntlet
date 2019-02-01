package com.config;

import com.util.Reference;
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
        @Config.LangKey("infinity.config.snap")
        public boolean Snap = true;

        @Config.Comment("The range around you that using 'snap' will be killed.")
        @Config.LangKey("infinity.config.extensionrange")
        @Config.RangeInt(min = 5, max = 100)
        public int ExtensionRange = 25;

        @Config.Comment("Cooldown in seconds for snap")
        @Config.LangKey("infinity.config.cooldown")
        @Config.RangeInt(min = 5, max = 100)
        public int SnapCooldown = 25;

        @Config.Comment("Minimum Telport range when holding sotne")
        @Config.LangKey("infinity.config.rangeteleportminimum")
        @Config.RangeInt(min = 5, max = 50)
        public int MinimumTeleportRange = 30;

        @Config.Comment("Maximum Telport range when holding sotne")
        @Config.LangKey("infinity.config.rangeteleportmaximum")
        @Config.RangeInt(min = 5, max = 100)
        public int MaximumTeleportRange = 100;



        @Config.LangKey("infinity.config.guitext")
        @Config.Comment("Enable Text on Gantlet GUI")
        public boolean GUITEXT = true;

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
