package com.config;

import com.util.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Config(modid = Reference.MODID)
@Config.LangKey("igauntlet.config.title")

public class ModConfig {

    public static final Gauntlet Gauntlet = new Gauntlet();


    public static class Gauntlet {

        public  final TimeStone Time = new TimeStone();
        public  final SoulStone Soul = new SoulStone();
        public  final RealityStone Reality = new RealityStone();
        public  final SpaceStone Space = new SpaceStone();
        public  final PowerStone Power = new PowerStone();
        public  final MindStone Mind = new MindStone();

        public static class TimeStone {
        }

        public static class SoulStone {
        }

        public static class RealityStone {
        }

        public static class SpaceStone {
            @Config.Comment("How long should it take before you drift? (Seconds)")
            @Config.LangKey("igauntlet.config.drift_timeout")
            public int SpaceDriftTimeout = 40;

            @Config.Comment("Minimum Telport range when holding sotne")
            @Config.LangKey("igauntlet.config.rangeteleportminimum")
            @Config.RangeInt(min = 5, max = 50)
            public int MinimumDriftRange = 30;

            @Config.Comment("Maximum Telport range when holding sotne")
            @Config.LangKey("igauntlet.config.rangeteleportmaximum")
            @Config.RangeInt(min = 5, max = 100)
            public int MaximumDriftRange = 100;
        }

        public static class PowerStone {
            @Config.Comment("Shift + left Click to kill Entities in range")
            @Config.LangKey("igauntlet.config.snap")
            public boolean Snap = true;

            @Config.Comment("The range around you that using 'snap' will be killed.")
            @Config.LangKey("igauntlet.config.extensionrange")
            @Config.RangeInt(min = 5, max = 100)
            public int ExtensionRange = 25;

            @Config.Comment("Cooldown in seconds for snap (Seconds)")
            @Config.LangKey("igauntlet.config.cooldown")
            @Config.RangeInt(min = 5, max = 100)
            public int SnapCooldown = 25;
        }

        public static class MindStone {

        }

        @Config.LangKey("igauntlet.config.guitext")
        @Config.Comment("Enable Text on Gantlet GUI")
        public boolean GuiText = true;

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
