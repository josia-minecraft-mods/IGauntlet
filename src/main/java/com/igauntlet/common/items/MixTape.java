package com.igauntlet.common.items;

import com.igauntlet.Infinity;
import com.igauntlet.init.ModItems;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.handlers.helpers.IHasModel;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class MixTape extends ItemRecord implements IHasModel {

    SoundEvent getsound;

    public MixTape(String name, SoundEvent sound) {
        super(name, sound);
        setTranslationKey(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setMaxDamage(4500);
        setCreativeTab(InfinityTabs.infinityTabs);

        getsound = sound;
        ModItems.ITEMS.add(this);
    }

    @Override
    public SoundEvent getSound() {
        return getsound;
    }



    @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
