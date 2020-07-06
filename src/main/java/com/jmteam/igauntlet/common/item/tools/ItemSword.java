package com.jmteam.igauntlet.common.item.tools;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class ItemSword extends SwordItem {

    public ItemSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }
}