package com.jmteam.igauntlet.common.item.tools;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;

public class ItemShovel extends ShovelItem {

    public ItemShovel(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }
}
