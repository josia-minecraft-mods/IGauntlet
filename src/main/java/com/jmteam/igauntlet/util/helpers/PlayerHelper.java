package com.jmteam.igauntlet.util.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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

    public static boolean hasItemInHands(EntityPlayer player, ItemStack stack) {
        return player.getHeldItem(EnumHand.MAIN_HAND).getItem() == stack.getItem()
                || player.getHeldItem(EnumHand.OFF_HAND).getItem() == stack.getItem();
    }

    public static EnumHand getHandForItem(EntityPlayer player, ItemStack stack) {
        if (hasItemInHands(player, stack)) {
            return player.getHeldItem(EnumHand.MAIN_HAND).getItem() == stack.getItem() ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
        }
        return EnumHand.MAIN_HAND;
    }

    public static void teleportToPosition(EntityPlayer player, BlockPos p) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            playerMP.connection.setPlayerLocation(p.getX(), p.getY(), p.getZ(), player.rotationYaw, player.rotationPitch);
        }
    }

    public static void teleportToSafePosition(EntityPlayer player, BlockPos bp) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            BlockPos p = player.world.getTopSolidOrLiquidBlock(bp);
            playerMP.connection.setPlayerLocation(p.getX(), p.getY(), p.getZ(), player.rotationYaw, player.rotationPitch);
        }
    }

    // This is all temporary data
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
