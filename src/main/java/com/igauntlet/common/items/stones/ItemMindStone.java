package com.igauntlet.common.items.stones;

import com.igauntlet.tabs.InfinityTabs;
import net.minecraft.item.Item;

public class ItemMindStone extends Item {

    public ItemMindStone(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);
        setMaxDamage(4500);
    }
}
