package com.igauntlet.init;

import com.igauntlet.common.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class InfinityBlocks {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block ASH_PILE = new BlockAshPile("ash_pile", Material.SAND, true);
    public static final Block URU_ORE = new BlockUruOre("uru_ore", Material.ROCK, true);
    public static final Block TESSERACT = new BlockTesseract("tesseract", Material.CLAY, true);
    public static final Block DWARF_STONE = new BlockDwarfStone("dwarf_stone", Material.GROUND, true);
    public static final Block DWARF_DIRT = new BlockDwarfDirt("dwarf_dirt", Material.GRASS, true);
    public static final Block DWARF_COBBLE = new BlockDwarfCobble("dwarf_cobble", Material.GROUND, true);
    public static final Block DWARF_WOOD = new BlockDwarfWood("dwarf_wood", Material.WOOD, true);
    public static final Block FORGE = new BlockForge("forge", Material.ANVIL, true);
    public static final Block MANIPULATOR = new BlockManipulator("manipulator", Material.IRON, true);
    public static final Block QUICK_SAND = new BlockQuickSand("quick_sand", Material.SAND, false);
}
