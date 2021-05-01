package com.jmteam.igauntlet.config;

import com.jmteam.igauntlet.IGauntlet;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = IGauntlet.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InfinityConfig {

    // Client
    public static boolean stoneGUIText;

    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT = specPair.getLeft();
        CLIENT_SPEC = specPair.getRight();
    }

    public static void bakeConfig() {
        stoneGUIText = CLIENT.StoneGUIText.get();
    }

    @SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {
        if (configEvent.getConfig().getSpec() == InfinityConfig.CLIENT_SPEC) {
            bakeConfig();
        }
    }

}
