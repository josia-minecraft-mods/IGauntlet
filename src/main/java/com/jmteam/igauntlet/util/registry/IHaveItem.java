package com.jmteam.igauntlet.util.registry;

import net.minecraft.item.BlockItem;

public interface IHaveItem {

    int getMaxSize();

    void setItem(BlockItem item);
}
