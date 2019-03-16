package com.igauntlet.common.items.stones;

import com.igauntlet.Infinity;
import com.igauntlet.init.InfinityItems;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.helpers.IHasModel;
import net.minecraft.item.Item;

public class TimeStone extends Item implements IHasModel {

    public TimeStone(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);
        setMaxDamage(4500);

        InfinityItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
