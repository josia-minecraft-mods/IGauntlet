package com.jmteam.igauntlet.common.items;

import com.jmteam.igauntlet.tabs.InfinityTabs;
import com.jmteam.igauntlet.util.handlers.SoundsHandler;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;


public class ItemMixTape extends ItemRecord {

    public ItemMixTape(String name, SoundEvent sound) {
        super(name, sound);
        setTranslationKey(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setCreativeTab(InfinityTabs.infinityTabs);
    }

    @Override
    public SoundEvent getSound() {
        return SoundsHandler.AWESOMEMIX;
    }
}
