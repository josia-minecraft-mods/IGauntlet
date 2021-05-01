package com.jmteam.igauntlet.common.init;

import net.minecraft.util.text.TranslationTextComponent;

public class InfinityMessages {

    public static final String STONE_NOT_SELECTED = createMessage("gauntlet.no_stone");
    public static final String STONE_NO_FUNCTION = createMessage("stone.notset");

    public static final String STONE_SPACE_NEAR_DRIFT = createMessage("stone.space.near_drift");
    public static final String STONE_SPACE_DRIFTED = createMessage("stone.space.drifted");

    public static final String SNAP_NOT_ENOUGH = createMessage("snap.not_enough");
    public static final String SNAP_AMOUNT = createMessage("snap.amount");
    public static final String SNAP_AMOUNT_MULTIPLE = createMessage("snap.amount.multiple");

    public static final String REVIVE_NOT_FOUND = createMessage("revive.not_found");
    public static final String REVIVE_AMOUNT = createMessage("revive.amount");
    public static final String REVIVE_AMOUNT_MULTIPLE = createMessage("revive.amount.multiple");

    public static final String INVENTORY_FULL = createMessage("inventory_full");

    public static String createMessage(String msg) {
        return "msg." + msg;
    }

    public static TranslationTextComponent getComponent(String msg, String... args) {
        return new TranslationTextComponent(msg, args);
    }
}
