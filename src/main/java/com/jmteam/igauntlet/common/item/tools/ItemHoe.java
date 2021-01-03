package com.jmteam.igauntlet.common.item.tools;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;

public class ItemHoe extends HoeItem {

    public ItemHoe(IItemTier tier, float attackSpeedIn, Properties builder) {
        super(tier, 1, attackSpeedIn, builder);
    }
}
