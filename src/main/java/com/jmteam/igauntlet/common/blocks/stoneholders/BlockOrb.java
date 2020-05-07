package com.jmteam.igauntlet.common.blocks.stoneholders;

import com.jmteam.igauntlet.common.blocks.InfinityBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.AxisAlignedBB;

public class BlockOrb extends InfinityBlock {

    public static final AxisAlignedBB POWERORBAAB = new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.6875, 0.8125);

    public BlockOrb(Material material) {
        super(material);
    }

    @Override
    public int getMaxSize() {
        return 1;
    }
}
