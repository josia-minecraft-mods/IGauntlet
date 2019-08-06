package com.jmteam.igauntlet.common.blocks.stoneholders;

import com.jmteam.igauntlet.util.helpers.IHaveItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockOrb extends Block implements IHaveItem {

    public BlockOrb(Material material) {
        super(material);
    }

    @Override
    public boolean hasItem() {
        return true;
    }
}
