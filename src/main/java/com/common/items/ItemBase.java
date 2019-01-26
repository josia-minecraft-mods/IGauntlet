package com.common.items;

import com.Infinity;
import com.init.ModItems;
import com.tabs.InfinityTabs;
import com.util.IHasModel;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

    public ItemBase(String name, boolean tab) {
        setTranslationKey(name);
        setRegistryName(name);

        if(tab)
        setCreativeTab(InfinityTabs.infinityTabs);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
