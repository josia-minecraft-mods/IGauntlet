package com.jmteam.igauntlet.common.items;

import com.jmteam.igauntlet.common.init.InfinitySounds;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;


public class ItemMixTape extends ItemRecord {

    public ItemMixTape(String name, SoundEvent sound) {
        super(name, sound);
        setMaxStackSize(1);
    }

    @Override
    public SoundEvent getSound() {
        return InfinitySounds.AWESOMEMIX;
    }
}
