package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.IGauntlet;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class InfinityGroups {

    public static final ItemGroup INFINITY = new ItemGroup("infinity_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(InfinityItems.INFINITY_GAUNTLET.get());
        }
    }.setBackgroundImage(new ResourceLocation("textures/gui/container/creative_inventory/tab_igauntlet.png"));
}
