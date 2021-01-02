package com.jmteam.igauntlet.common.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class InfinityGroups {

    public static final ItemGroup INFINITY = new ItemGroup("infinity_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(InfinityItems.INFINITY_GAUNTLET.get());
        }
    };
}
