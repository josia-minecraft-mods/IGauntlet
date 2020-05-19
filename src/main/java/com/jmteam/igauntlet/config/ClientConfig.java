package com.jmteam.igauntlet.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {

    public ForgeConfigSpec.BooleanValue StoneGUIText;

    public ClientConfig(ForgeConfigSpec.Builder builder) {
        builder.push("Gauntlet");
        StoneGUIText = builder.comment("Does text need to show?")
                .translation("infinity.config.stoneguitext")
                .define("StoneGUIText", true);
        builder.pop();
    }

}
