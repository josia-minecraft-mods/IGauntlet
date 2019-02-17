package com.igauntlet.util.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;

public class PlayerHelper {

        public static void sendMessage(EntityPlayer player, String message, boolean hotBar) {
            if (!player.world.isRemote) {
                player.sendStatusMessage(new TextComponentTranslation(message), hotBar);
            }
        }

        public static void sendMessage(EntityPlayer player, TextComponentTranslation message, boolean hotBar) {
            if (!player.world.isRemote) {
                player.sendStatusMessage(message, hotBar);
            }
        }

    public static void sendMessageClient(EntityPlayer player, String message, boolean hotBar) {
        if (player.world.isRemote) {
            player.sendStatusMessage(new TextComponentTranslation(message), hotBar);
        }
    }

    public static void sendMessageClient(EntityPlayer player, TextComponentTranslation message, boolean hotBar) {
        if (player.world.isRemote) {
            player.sendStatusMessage(message, hotBar);
        }
    }
}
