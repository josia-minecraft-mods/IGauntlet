package com.igauntlet.common.blocks;

import com.igauntlet.util.helpers.IHaveItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block implements IHaveItem {

    public BlockBase(Material material) {
        super(material);
    }

    @Override
    public boolean hasItem() {
        return true;
    }
}
