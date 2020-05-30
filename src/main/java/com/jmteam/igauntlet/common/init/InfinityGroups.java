package com.jmteam.igauntlet.common.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class InfinityGroups {

    public static final ItemGroup infinityTab = new ItemGroup("infinityTab") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(InfinityItems.infinity_gauntlet);
        }

    };
}
