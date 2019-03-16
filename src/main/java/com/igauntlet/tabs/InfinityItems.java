package com.igauntlet.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import static com.igauntlet.util.IReference.BACKGROUND_TAB;


public class InfinityItems extends CreativeTabs {
    public InfinityItems(String label) {
        super(label);
        this.setBackgroundImageName(BACKGROUND_TAB);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(com.igauntlet.init.InfinityItems.INFINITY_GAUNTLET);
    }
}

