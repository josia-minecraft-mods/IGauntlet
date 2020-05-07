package com.jmteam.igauntlet.common.blocks.stoneholders;

import com.jmteam.igauntlet.common.blocks.InfinityBlock;
import net.minecraft.block.material.Material;

public class BlockAether extends InfinityBlock {

    public BlockAether(Material material) {
        super(material);
    }

    @Override
    public int getMaxSize() {
        return 1;
    }
}
