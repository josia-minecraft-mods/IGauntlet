package com.jmteam.igauntlet;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(IGauntlet.MODID)
public class IGauntlet {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "igauntlet";

    public IGauntlet() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
