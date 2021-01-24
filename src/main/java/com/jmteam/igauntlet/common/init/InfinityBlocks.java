package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.common.blocks.*;
import com.jmteam.igauntlet.common.blocks.stoneholders.BlockAether;
import com.jmteam.igauntlet.common.blocks.stoneholders.BlockOrb;
import com.jmteam.igauntlet.common.blocks.stoneholders.BlockTesseract;
import com.jmteam.igauntlet.util.registry.InfinityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InfinityBlocks {

    public static void init() {}

    public static RegistryObject<Block> ASH_PILE = registerBlock(new BlockAshPile(Material.SAND), "ash_pile", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> URU_ORE = registerBlock(new BlockOre(Material.ROCK, InfinityItems.URU_INGOT, 1, 1), "uru_ore", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> TESSERACT = registerBlock(new BlockTesseract(), "tesseract", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> DWARF_STONE = registerBlock(new InfinityBlock(Block.Properties.create(Material.ORGANIC)), "dwarf_stone", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> DWARF_DIRT = registerBlock(new InfinityBlock(Block.Properties.create(Material.PLANTS).hardnessAndResistance(15).harvestTool(ToolType.SHOVEL).harvestLevel(1).sound(SoundType.WET_GRASS)), "dwarf_dirt", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> DWARF_COBBLE = registerBlock(new InfinityBlock(Material.ORGANIC), "dwarf_cobble", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> DWARF_WOOD = registerBlock(new InfinityBlock(Material.WOOD), "dwarf_wood", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> FORGE = registerBlock(new BlockForge(Material.ANVIL), "forge", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> MANIPULATOR = registerBlock(new BlockManipulator(Material.IRON), "manipulator", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> QUICK_SAND = registerBlock(new BlockQuickSand(Material.SAND), "quick_sand", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> POWER_ORB = registerBlock(new BlockOrb(Material.ROCK), "power_orb", InfinityGroups.INFINITY, true, 1);
    public static RegistryObject<Block> AETHER = registerBlock(new BlockAether(Material.BARRIER), "aether", InfinityGroups.INFINITY, true, 1);

    public static <T extends Block> RegistryObject<T> registerBlock(T block, String name, ItemGroup group, boolean item, int amount) {

        if (item) {
            Item itemBlock = new BlockItem(block, new Item.Properties().group(group).maxStackSize(amount));
            InfinityItems.addItem(itemBlock, name);
        }

        return InfinityRegistry.BLOCK_REGISTRY.register(name, () -> block);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(T block, String name, ItemGroup group, boolean item) {
        return registerBlock(block, name, group, item, 64);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(T block, String name) {
        return registerBlock(block, name, null, false);
    }
}
