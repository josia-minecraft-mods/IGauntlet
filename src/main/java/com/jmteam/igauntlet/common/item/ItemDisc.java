package com.jmteam.igauntlet.common.item;

import com.jmteam.igauntlet.common.init.InfinityGroups;
import com.mojang.datafixers.schemas.Schema;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.fixes.JukeboxRecordItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ItemDisc extends MusicDiscItem {

    public ItemDisc(Supplier<SoundEvent> soundIn) {
        super(2, soundIn, new Item.Properties().maxStackSize(1).group(InfinityGroups.INFINITY));
    }
}
