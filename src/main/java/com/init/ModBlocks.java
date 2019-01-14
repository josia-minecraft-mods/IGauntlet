package com.init;

import com.common.blocks.AshPile;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block ASH_PILE = new AshPile("ash_pile", Material.SAND);

}
