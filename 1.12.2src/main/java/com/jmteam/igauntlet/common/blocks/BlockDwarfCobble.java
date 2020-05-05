package com.jmteam.igauntlet.common.blocks;


import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockDwarfCobble extends BlockBase {

    public BlockDwarfCobble(Material material) {
        super(material);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 2);
        setHardness(15.0F);
        setResistance(15.0F);
    }
}