package com.common.items;

import com.Main;
import net.minecraft.item.Item;
import com.tabs.InfinityTabs;
import com.util.IHasModel;
import com.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class realitystone extends Item implements IHasModel {

    public realitystone(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(64);
        setMaxDamage(5);

        ModItems.ITEMS.add(this);
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return TextFormatting.BOLD + "Reality Stone";
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
