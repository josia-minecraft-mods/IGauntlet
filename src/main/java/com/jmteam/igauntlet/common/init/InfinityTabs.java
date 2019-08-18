package com.jmteam.igauntlet.common.init;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class InfinityTabs {

    public static final CreativeTabs infinityTabs = new CreativeTabs("infinityitems") {

        @Override
        public String getBackgroundImageName() {
            return "igauntlet.png";
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(com.jmteam.igauntlet.common.init.InfinityItems.infinity_gauntlet);
        }
    };
}
