package com.jmteam.igauntlet.common.init;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class InfinityItems {

    public static List<Item> ITEMS = new ArrayList<>();



    public static <T extends Item> T addItem(T item, String name) {
        item.setRegistryName(name);
        ITEMS.add(item);

        return item;
    }
}
