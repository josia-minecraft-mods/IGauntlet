package com.jmteam.igauntlet.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockFancyGlowstone extends BlockBase {

    public BlockFancyGlowstone(Material material) {
        super(material);
        setSoundType(SoundType.GLASS);
        setHarvestLevel("pickaxe", 0);
        setHardness(15.0F);
        setResistance(15.0F);
    }
}