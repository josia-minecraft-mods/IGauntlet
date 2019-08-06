package com.jmteam.igauntlet.common.blocks.stoneholders;

import com.jmteam.igauntlet.util.helpers.IHaveItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAether extends Block implements IHaveItem {

    public BlockAether(Material material) {
        super(material);
    }

    @Override
    public boolean hasItem() {
        return true;
    }
}
