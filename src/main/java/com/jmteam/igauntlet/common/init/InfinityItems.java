package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.common.item.ItemBackInBlack;
import com.jmteam.igauntlet.common.item.ItemBase;
import com.jmteam.igauntlet.common.item.ItemInfinityGauntlet;
import com.jmteam.igauntlet.common.item.ItemMixtape;
import com.jmteam.igauntlet.common.item.stones.*;
import com.jmteam.igauntlet.common.item.tools.ItemDwarfhammer;
import com.jmteam.igauntlet.common.item.tools.ItemSword;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;

import java.util.ArrayList;
import java.util.List;

public class InfinityItems {

    public static List<Item> ITEMS = new ArrayList<>();

    public static Item infinity_gauntlet = addItem(new ItemInfinityGauntlet(), "infinity_gauntlet").setGroup(InfinityGroups.infinityTab);
    public static Item uru_ingot = addItem(new ItemBase(), "uru_ingot").setGroup(InfinityGroups.infinityTab);
    public static Item mind_stone = addItem(new ItemMindStone(), "mind_stone").setGroup(InfinityGroups.infinityTab);
    public static Item reality_stone = addItem(new ItemRealityStone(), "reality_stone").setGroup(InfinityGroups.infinityTab);
    public static Item time_stone = addItem(new ItemTimeStone(), "time_stone").setGroup(InfinityGroups.infinityTab);
    public static Item space_stone = addItem(new ItemSpaceStone(), "space_stone").setGroup(InfinityGroups.infinityTab);
    public static Item power_stone = addItem(new ItemPowerStone(), "power_stone").setGroup(InfinityGroups.infinityTab);
    public static Item soul_stone = addItem(new ItemSoulStone(), "soul_stone").setGroup(InfinityGroups.infinityTab);
    public static Item dwarf_hammer = addItem(new ItemDwarfhammer(ItemTier.DIAMOND, 4, 1, new Item.Properties().group(InfinityGroups.infinityTab)), "dwarf_hammer");
    public static Item dwarf_sword = addItem(new ItemSword(ItemTier.DIAMOND, 4, 1, new Item.Properties().group(InfinityGroups.infinityTab)), "dwarf_sword");
    public static Item mixtape = addItem(new ItemMixtape(InfinitySounds.AWESOMEMIX), "awesome_mix").setGroup(InfinityGroups.infinityTab);
    public static Item backinblack = addItem(new ItemBackInBlack(InfinitySounds.BACKINBLACK),"backinblack").setGroup(InfinityGroups.infinityTab);

    //  public static Item necklace = addItem(new ItemEyeOfAgamotto(), "eye_agamotto").setGroup(InfinityTabs.infinityTab);

    public static <T extends Item> T addItem(T item, String name) {
        item.setRegistryName(name);
        ITEMS.add(item);

        return item;
    }
}
