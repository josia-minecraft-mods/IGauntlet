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

    public static int getPDataInt(EntityPlayer player, String name) {
        return player.getEntityData().getInteger(name);
    }

    public static boolean getPDataBoolean(EntityPlayer player, String name) {
        return player.getEntityData().getBoolean(name);
    }

    public static void setPDataInt(EntityPlayer player, String name, int integer) {
        player.getEntityData().setInteger(name, integer);
    }

    public static void setPDataBoolean(EntityPlayer player, String name, boolean bool) {
        player.getEntityData().setBoolean(name, bool);
    }
}
