package com.jmteam.igauntlet.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLeatherwood extends BlockBase {

    public BlockLeatherwood(Material material) {
        super(material);
        setSoundType(SoundType.WOOD);
        setHarvestLevel("axe", 1);
        setHardness(15.0F);
        setResistance(15.0F);
    }
}