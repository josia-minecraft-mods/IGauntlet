package com.igauntlet.init;

import com.igauntlet.Infinity;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Config(modid = Infinity.MODID)
@Config.LangKey("igauntlet.config.title")

public class ModConfig {

    public static final Gauntlet Gauntlet = new Gauntlet();
    public static final Dimensions Dimensions = new Dimensions();
    public static final AllowedGems AllowedGems = new AllowedGems();


    public static class Gauntlet {

        public final TimeStone TimeStone = new TimeStone();
        public final SoulStone SoulStone = new SoulStone();
        public final RealityStone RealityStone = new RealityStone();
        public final SpaceStone SpaceStone = new SpaceStone();
        public final PowerStone PowerStone = new PowerStone();
        public final MindStone MindStone = new MindStone();

        public static class TimeStone {
            @Config.Comment("The range around you that using 'Time Freeze' with entities will be frozen.")
            @Config.LangKey("igauntlet.config.time.freezerange")
            @Config.RangeInt(min = 5, max = 100)
            public int FreezeRange = 25;
        }

        public static class SoulStone {
        }

        public static class RealityStone {
          /*  @Config.Comment("Should the fireball explode blocks?")
            @Config.LangKey("igauntlet.config.reality.explode")
            public boolean ExplodeBlocks = true;*/
        }

        public static class SpaceStone {
            @Config.Comment("How long should it take before you drift? (Seconds)")
            @Config.LangKey("igauntlet.config.drift_timeout")
            public int SpaceDriftTimeout = 40;

            @Config.Comment("Minimum Telport range when holding stone")
            @Config.LangKey("igauntlet.config.rangeteleportminimum")
            @Config.RangeInt(min = 5, max = 50)
            public int MinimumDriftRange = 30;

            @Config.Comment("Maximum Telport range when holding sotne")
            @Config.LangKey("igauntlet.config.rangeteleportmaximum")
            @Config.RangeInt(min = 5, max = 100)
            public int MaximumDriftRange = 100;
        }

        public static class PowerStone {
        }

        public static class MindStone {

        }

        @Config.LangKey("igauntlet.config.guitext")
        @Config.Comment("Enable Text on Gantlet GUI")
        public boolean GuiText = true;

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

    public static class Dimensions {
        @Config.LangKey("igauntlet.config.dwarfid")
        @Config.Comment("Change the id for the Dwarf Dimension")
        public int DwarfDimensionID = 20;
    }

    public static class AllowedGems {
        @Config.RequiresMcRestart
        @Config.Comment("Allow Powerstone")
        @Config.LangKey("igauntlet.config.allow.power")
        public boolean PowerStone = true;

        @Config.RequiresMcRestart
        @Config.Comment("Allow Powerstone")
        @Config.LangKey("igauntlet.config.allow.reality")
        public boolean RealityStone = true;

        @Config.RequiresMcRestart
        @Config.Comment("Allow Powerstone")
        @Config.LangKey("igauntlet.config.allow.space")
        public boolean SpaceStone = true;

        @Config.RequiresMcRestart
        @Config.Comment("Allow Powerstone")
        @Config.LangKey("igauntlet.config.allow.soul")
        public boolean SoulStone = true;

        @Config.RequiresMcRestart
        @Config.Comment("Allow Powerstone")
        @Config.LangKey("igauntlet.config.allow.mind")
        public boolean MindStone = true;

        @Config.RequiresMcRestart
        @Config.Comment("Allow Powerstone")
        @Config.LangKey("igauntlet.config.allow.time")
        public boolean TimeStone = true;

    }


    @Mod.EventBusSubscriber(modid = Infinity.MODID)
    private static class EventHandler {

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Infinity.MODID)) {
                ConfigManager.sync(Infinity.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
