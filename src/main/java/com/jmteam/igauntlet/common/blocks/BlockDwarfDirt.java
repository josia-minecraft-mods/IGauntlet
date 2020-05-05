package com.jmteam.igauntlet.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockDwarfDirt extends BlockBase {

    public BlockDwarfDirt(Material material) {
        super(material);
        setSoundType(SoundType.STONE);
        setHarvestLevel("shovel", 1);
        setHardness(15.0F);
        setResistance(15.0F);
    }
}