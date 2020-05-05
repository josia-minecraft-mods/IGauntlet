package com.jmteam.igauntlet.common.blocks.stoneholders;

import com.jmteam.igauntlet.common.blocks.BlockBase;
import net.minecraft.block.material.Material;

public class BlockAether extends BlockBase {

    public BlockAether(Material material) {
        super(material);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
