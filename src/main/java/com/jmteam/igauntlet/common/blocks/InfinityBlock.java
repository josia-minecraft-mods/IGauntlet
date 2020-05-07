package com.jmteam.igauntlet.common.blocks;

import com.jmteam.igauntlet.common.item.InfinityItemBlock;
import com.jmteam.igauntlet.util.IHaveItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class InfinityBlock extends Block implements IHaveItem {

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

    @Override
    public int getMaxSize() {
        return 64;
    }

    @Override
    public Item setGroup(ItemGroup group) {
        if (blockItem != null) {
            blockItem.setGroup(group);
        }

        return blockItem;
    }
}
