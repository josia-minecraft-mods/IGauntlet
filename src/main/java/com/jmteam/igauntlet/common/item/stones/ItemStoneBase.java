package com.jmteam.igauntlet.common.item.stones;

import com.jmteam.igauntlet.common.init.InfinityGroups;
import com.jmteam.igauntlet.common.item.ItemBase;
import com.jmteam.igauntlet.util.registry.InfinityRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class ItemStoneBase extends ItemBase {

    public ItemStoneBase() {
        super(new Item.Properties().stacksTo(1).tab(InfinityGroups.INFINITY));
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
}
