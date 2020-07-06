package com.jmteam.igauntlet.common.item;

import com.mojang.datafixers.schemas.Schema;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.fixes.JukeboxRecordItem;

import java.util.ArrayList;
import java.util.List;

public class ItemDisc extends MusicDiscItem {
    public List<ItemGroup> groups;

    public ItemDisc(SoundEvent soundIn) {
        super(2, soundIn, new Item.Properties().maxStackSize(1));
    }

    public Item setGroup(ItemGroup... groups) {
        this.groups = new ArrayList<>();

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
