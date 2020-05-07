package com.jmteam.igauntlet.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;

import java.util.ArrayList;
import java.util.List;

public class InfinityItemBlock extends BlockItem {
    public List<ItemGroup> groups;

    public InfinityItemBlock(Block blockIn) {
        super(blockIn, new Properties());
    }

    public InfinityItemBlock(Block blockIn, int stackSize) {
        super(blockIn, new Properties().maxStackSize(stackSize));
    }

    public InfinityItemBlock setGroup(ItemGroup... groups) {
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
}
