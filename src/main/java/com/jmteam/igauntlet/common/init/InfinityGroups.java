package com.jmteam.igauntlet.common.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.function.Supplier;

public class InfinityGroups {

    public static final ItemGroup INFINITY = createTab("infinity_tab", () -> InfinityItems.INFINITY_GAUNTLET::get)
            .setBackgroundImage(new ResourceLocation("textures/gui/container/creative_inventory/tab_igauntlet.png"));

    private static ItemGroup createTab(String name, Supplier<Supplier<IItemProvider>> iItemProvider) {
        return new ItemGroup(name) {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(iItemProvider.get().get());
            }
        };
    }
}
