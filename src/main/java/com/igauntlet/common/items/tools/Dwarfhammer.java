package com.igauntlet.common.items.tools;

import com.igauntlet.Infinity;
import net.minecraft.item.Item;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.handlers.helpers.IHasModel;
import com.igauntlet.init.ModItems;

public class Dwarfhammer extends Item implements IHasModel {

    public Dwarfhammer(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);
        setMaxDamage(4500);

        ModItems.ITEMS.add(this);
    }


    @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}