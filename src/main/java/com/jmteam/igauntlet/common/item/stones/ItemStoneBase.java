package com.jmteam.igauntlet.common.item.stones;

import com.jmteam.igauntlet.common.item.ItemBase;
import net.minecraft.item.ItemStack;

public abstract class ItemStoneBase extends ItemBase {

    public ItemStoneBase() {
        super(1);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
}
