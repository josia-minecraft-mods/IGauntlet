package com.jmteam.igauntlet.util.registry;

import net.minecraft.item.BlockItem;

public interface IHaveItem {

    /* Max Stack Size */
    int getMaxSize();

    /* ItemBlock to use for tab */
    void setItem(BlockItem item);
}
