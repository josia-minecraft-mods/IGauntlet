package com.jmteam.igauntlet.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockDwarfDirt extends InfinityBlock {

    public BlockDwarfDirt(Material material) {
        super(Block.Properties.create(material).hardnessAndResistance(15).harvestTool(ToolType.SHOVEL).harvestLevel(1).sound(SoundType.WET_GRASS));
    }
}