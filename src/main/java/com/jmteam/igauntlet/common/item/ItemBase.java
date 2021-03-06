package com.jmteam.igauntlet.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.util.ArrayList;
import java.util.List;

public class ItemBase extends Item {

    public ItemBase(Properties properties) {
        super(properties);
    }

    public ItemBase() {
        super(new Item.Properties());
    }

    public ItemBase(int maxSize) {
        super(new Item.Properties().maxStackSize(maxSize));
    }
}
