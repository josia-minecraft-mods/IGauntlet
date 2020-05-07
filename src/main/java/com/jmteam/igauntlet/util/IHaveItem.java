package com.jmteam.igauntlet.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public interface IHaveItem {

    int getMaxSize();
    Item setGroup(ItemGroup group);
}
