package com.jmteam.igauntlet.common.items.stones;

import com.jmteam.igauntlet.tabs.InfinityTabs;
import net.minecraft.item.Item;

public class ItemSoulStone extends Item {

    public ItemSoulStone(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);
        setMaxDamage(4500);
    }
}
