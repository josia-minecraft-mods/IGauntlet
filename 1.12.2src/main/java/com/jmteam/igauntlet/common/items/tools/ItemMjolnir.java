package com.jmteam.igauntlet.common.items.tools;

import com.jmteam.igauntlet.common.init.InfinityTabs;
import net.minecraft.item.Item;


public class ItemMjolnir extends Item {

    public ItemMjolnir(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);
        setMaxDamage(5000);
    }
}
