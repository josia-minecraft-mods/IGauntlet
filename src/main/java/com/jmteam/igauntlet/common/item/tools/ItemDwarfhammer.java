package com.jmteam.igauntlet.common.item.tools;

import com.jmteam.igauntlet.common.item.ItemBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class ItemDwarfhammer extends PickaxeItem {

    public ItemDwarfhammer(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }
}
