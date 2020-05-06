package com.jmteam.igauntlet.common.blocks;

import com.jmteam.igauntlet.common.item.InfinityItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class InfinityBlock extends Block {

    private InfinityItemBlock blockItem;


    public InfinityBlock(Material material) {
        super(Properties.create(material).harvestLevel(0).harvestTool(ToolType.PICKAXE));
    }

    public InfinityBlock(Properties properties) {
        super(properties);
    }

    public Block setGroup(ItemGroup... groups) {
        if (blockItem != null) {
            blockItem.setGroup(groups);
        }
        return this;
    }

    public void setBlockItem(InfinityItemBlock blockItem) {
        this.blockItem = blockItem;
    }
}
