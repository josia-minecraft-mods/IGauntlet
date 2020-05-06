package com.jmteam.igauntlet.common.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class InfinityTabs {

    public static final ItemGroup igauntlet = new ItemGroup("igauntlet") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.ACACIA_BOAT);
        }
    };
}
