package com.igauntlet.common.items.tools;

import com.igauntlet.Infinity;
import com.igauntlet.init.ModItems;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.handlers.helper.IHasModel;
import net.minecraft.item.Item;

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