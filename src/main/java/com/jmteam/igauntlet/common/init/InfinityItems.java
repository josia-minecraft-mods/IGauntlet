package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.common.item.ItemBase;
import com.jmteam.igauntlet.common.item.ItemDisc;
import com.jmteam.igauntlet.common.item.ItemInfinityGauntlet;
import com.jmteam.igauntlet.common.item.stones.*;
import com.jmteam.igauntlet.common.item.tools.*;
import com.jmteam.igauntlet.util.registry.InfinityRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InfinityItems {

    // Gauntlet + Stones
    public static RegistryObject<Item> INFINITY_GAUNTLET = addItem(new ItemInfinityGauntlet(), "infinity_gauntlet");
    public static RegistryObject<Item> MIND_STONE = addItem(new ItemMindStone(), "mind_stone");
    public static RegistryObject<Item> REALITY_STONE = addItem(new ItemRealityStone(), "reality_stone");
    public static RegistryObject<Item> TIME_STONE = addItem(new ItemTimeStone(), "time_stone");
    public static RegistryObject<Item> SPACE_STONE = addItem(new ItemSpaceStone(), "space_stone");
    public static RegistryObject<Item> SOUL_STONE = addItem(new ItemSoulStone(), "soul_stone");

    // URU
    public static RegistryObject<Item> URU_INGOT = addItem(new ItemBase(), "uru_ingot");
    public static RegistryObject<Item> URU_PICKAXE = addItem(new ItemPickaxe(ItemTier.DIAMOND, 4, 1, new Item.Properties().tab(InfinityGroups.INFINITY)), "uru_pickaxe");
    public static RegistryObject<Item> URU_SWORD = addItem(new ItemSword(ItemTier.DIAMOND, 4, 1, new Item.Properties().tab(InfinityGroups.INFINITY)), "uru_sword");
    public static RegistryObject<Item> URU_AXE = addItem(new ItemAxe(ItemTier.DIAMOND, 4, 1, new Item.Properties().tab(InfinityGroups.INFINITY)), "uru_axe");
    public static RegistryObject<Item> URU_SHOVEL = addItem(new ItemShovel(ItemTier.DIAMOND, 4, 1, new Item.Properties().tab(InfinityGroups.INFINITY)), "uru_shovel");
    public static RegistryObject<Item> URU_HOE = addItem(new ItemHoe(ItemTier.DIAMOND, 4, new Item.Properties().tab(InfinityGroups.INFINITY)), "uru_hoe");

    // MISC
    public static RegistryObject<Item> DWARF_HAMMER = addItem(new ItemPickaxe(ItemTier.DIAMOND, 4, 1, new Item.Properties().tab(InfinityGroups.INFINITY)), "dwarf_hammer");
    public static RegistryObject<Item> DWARF_SWORD = addItem(new ItemSword(ItemTier.DIAMOND, 4, 1, new Item.Properties().tab(InfinityGroups.INFINITY)), "dwarf_sword");
    public static RegistryObject<Item> MIXTAPE = addItem(new ItemDisc(InfinitySounds.AWESOMEMIX), "awesome_mix");

    //  public static RegistryObject<Item>  necklace = addItem(new ItemEyeOfAgamotto(), "eye_agamotto").setGroup(InfinityTabs.infinityTab);

    public static <T extends Item> RegistryObject<T> addItem(T item, String name) {
        return InfinityRegistry.ITEM_REGISTRY.register(name, () -> item);
    }
}
