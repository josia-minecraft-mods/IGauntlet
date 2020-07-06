package com.jmteam.igauntlet.common.item.tools;

import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;

public class ItemPickaxe extends PickaxeItem {

    public ItemPickaxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }
}
