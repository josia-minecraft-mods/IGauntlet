package com.igauntlet.common.items.function.gems;

import net.minecraft.entity.player.EntityPlayer;

public class GemReality {

    public static void SurvivalFlight(EntityPlayer player, boolean enable) {
        player.capabilities.allowFlying = enable;
    }
}
