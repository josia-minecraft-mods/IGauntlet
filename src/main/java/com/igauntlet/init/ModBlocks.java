package com.igauntlet.init;

import com.igauntlet.common.blocks.AshPile;

import com.igauntlet.common.blocks.Tesseract;
import com.igauntlet.common.blocks.UruOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block ASH_PILE = new AshPile("ash_pile", Material.SAND, true);
    public static final Block URU_ORE = new UruOre("uru_ore", Material.ROCK, true);
    public static final Block TESSERACT = new Tesseract("tesseract", Material.CLAY, true);

}
