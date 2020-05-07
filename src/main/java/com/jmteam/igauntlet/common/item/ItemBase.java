package com.jmteam.igauntlet.common.item;

import com.jmteam.igauntlet.util.IHaveItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemBase extends Item implements IHaveItem {
    public List<ItemGroup> groups;

    public ItemBase(Properties properties) {
        super(properties);
    }

    public ItemBase() {
        super(new Item.Properties());
    }

    public ItemBase(int maxSize) {
        super(new Item.Properties().maxStackSize(maxSize));
    }

    public ItemBase setGroup(ItemGroup... groups) {
        this.groups = new ArrayList<ItemGroup>();

        for (ItemGroup group : groups) {
            this.groups.add(group);
        }

        return this;
    }

    @Override
    protected boolean isInGroup(ItemGroup group) {
        if (getCreativeTabs().stream().anyMatch(tab -> tab == group) || (groups != null && groups.contains(group))) {
            return true;
        }

        ItemGroup itemgroup = this.getGroup();
        return itemgroup != null && (group == ItemGroup.SEARCH || group == itemgroup);
    }

    @Override
    public int getMaxSize() {
        return 64;
    }

    @Override
    public Item setGroup(ItemGroup group) {
        groups = new ArrayList<>();

        if(!groups.contains(group)) {
            groups.add(group);
        }

        return this;
    }
}
