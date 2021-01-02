package com.jmteam.igauntlet.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class InfinityBlock extends Block {

    public InfinityBlock(Material material) {
        super(Block.Properties.create(material).harvestLevel(0).hardnessAndResistance(15.0f).harvestTool(ToolType.PICKAXE));
    }

    public InfinityBlock(Properties properties) {
        super(properties.harvestLevel(0).hardnessAndResistance(15.0f).harvestTool(ToolType.PICKAXE));
    }
}
