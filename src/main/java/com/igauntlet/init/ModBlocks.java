package com.igauntlet.init;


import com.igauntlet.common.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block ASH_PILE = new AshPile("ash_pile", Material.SAND, true);
    public static final Block URU_ORE = new UruOre("uru_ore", Material.ROCK, true);
    public static final Block TESSERACT = new Tesseract("tesseract", Material.CLAY, true);
    public static final Block DWARF_STONE = new DwarfStone("dwarf_stone", Material.GROUND, true);
    public static final Block DWARF_DIRT = new DwarfDirt("dwarf_dirt", Material.GRASS, true);
    public static final Block DWARF_COBBLE = new DwarfCobble("dwarf_cobble", Material.GROUND, true);
    public static final Block DWARF_WOOD = new DwarfWood("dwarf_wood", Material.WOOD, true);
    public static final Block FORGE = new Forge("forge", Material.ANVIL, true);
    public static final Block MANIPULATOR = new Manipulator("manipulator", Material.IRON, true);


}
