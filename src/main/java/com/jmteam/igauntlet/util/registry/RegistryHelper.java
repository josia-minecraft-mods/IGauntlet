package com.jmteam.igauntlet.util.registry;

import com.jmteam.igauntlet.util.helpers.ReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class RegistryHelper {

    public static Item setCreativeTab(Item i, ItemGroup group) {
        BlockItem item = (BlockItem) i;

        if (item != null) {
            ReflectionHelper.setValuePrivateDeclaredField("group", ReflectionHelper.getClassFromSuperClasses(item, Item.class), item, group);
        }

        return i;
    }

    public static Block setCreativeTab(Block b, ItemGroup group) {
        BlockItem item = (BlockItem) b.asItem();

        if (item != null) {
            ReflectionHelper.setValuePrivateDeclaredField("group", ReflectionHelper.getClassFromSuperClasses(item, Item.class), item, group);
        }

        return b;
    }
}
