package com.jmteam.igauntlet.common.blocks;

import com.jmteam.igauntlet.util.helpers.IHaveItem;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockDwarfDirt extends Block implements IHaveItem {

    public BlockDwarfDirt(Material material) {
        super(material);
        setSoundType(SoundType.STONE);
        setHarvestLevel("shovel", 1);
        setHardness(15.0F);
        setResistance(15.0F);
    }

    @Override
    public boolean hasItem() {
        return true;
    }
}