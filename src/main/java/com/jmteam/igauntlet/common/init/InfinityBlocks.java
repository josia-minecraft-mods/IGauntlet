package com.jmteam.igauntlet.common.init;

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

import java.util.function.Supplier;

public class InfinityBlocks {

    public static final RegistryObject<Block> ASH_PILE = registerBlock(new BlockAshPile(Material.SAND), "ash_pile", InfinityGroups.INFINITY);
    public static final RegistryObject<Block> URU_ORE = registerBlock(new BlockOre(Material.STONE, InfinityItems.URU_INGOT, 1, 1), "uru_ore", InfinityGroups.INFINITY);
    public static final RegistryObject<Block> TESSERACT = registerBlock(new BlockTesseract(), "tesseract", InfinityGroups.INFINITY);
    public static final RegistryObject<Block> DWARF_STONE = registerBlock(new InfinityBlock(Block.Properties.of(Material.STONE)), "dwarf_stone", InfinityGroups.INFINITY);
    public static final RegistryObject<Block> DWARF_DIRT = registerBlock(new InfinityBlock(Block.Properties.of(Material.PLANT).strength(15).harvestTool(ToolType.SHOVEL).harvestLevel(1).sound(SoundType.WET_GRASS)), "dwarf_dirt", InfinityGroups.INFINITY);
    public static final RegistryObject<Block> DWARF_COBBLE = registerBlock(new InfinityBlock(Material.STONE), "dwarf_cobble", InfinityGroups.INFINITY);
    public static final RegistryObject<Block> DWARF_WOOD = registerBlock(new InfinityBlock(Material.WOOD), "dwarf_wood", InfinityGroups.INFINITY);
    public static final RegistryObject<Block> FORGE = registerBlock(new BlockForge(Material.HEAVY_METAL), "forge", InfinityGroups.INFINITY);
    public static final RegistryObject<Block> MANIPULATOR = registerBlock(new BlockManipulator(Material.METAL), "manipulator", InfinityGroups.INFINITY);
    public static final RegistryObject<Block> QUICK_SAND = registerBlock(new BlockQuickSand(Material.SAND), "quick_sand", InfinityGroups.INFINITY);
    public static final RegistryObject<Block> POWER_ORB = registerBlock(new BlockOrb(Material.STONE), "power_orb", InfinityGroups.INFINITY, 1);
    public static final RegistryObject<Block> AETHER = registerBlock(new BlockAether(Material.BARRIER), "aether", InfinityGroups.INFINITY, 1);

    public static <T extends Block> RegistryObject<T> registerBlock(T block, String name, ItemGroup group, int amount) {

        if (group != null) {
            Item itemBlock = new BlockItem(block, new Item.Properties().tab(group).stacksTo(amount));
            InfinityItems.addItem(itemBlock, name);
        }

        return registerBlock(block, name);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(T block, String name, ItemGroup group) {
        return registerBlock(block, name, group, 64);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(T block, String name) {
        return InfinityRegistry.BLOCK_REGISTRY.register(name, () -> block);
    }
}
