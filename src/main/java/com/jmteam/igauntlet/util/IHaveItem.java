package com.jmteam.igauntlet.util;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public interface IHaveItem {

    int getMaxSize();
    void setItem(BlockItem item);
}
