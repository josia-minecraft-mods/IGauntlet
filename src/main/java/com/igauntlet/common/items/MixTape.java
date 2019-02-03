package com.igauntlet.common.items;

import com.igauntlet.Infinity;
import com.igauntlet.init.ModItems;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.handlers.helpers.IHasModel;
import net.minecraft.item.Item;

public class MixTape extends Item implements IHasModel {

    public MixTape(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setMaxDamage(4500);
        setCreativeTab(InfinityTabs.infinityTabs);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
