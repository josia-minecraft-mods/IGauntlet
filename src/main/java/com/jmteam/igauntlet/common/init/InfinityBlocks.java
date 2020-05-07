package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.common.blocks.BlockInfinityTileEntityBase;
import com.jmteam.igauntlet.common.blocks.InfinityBlock;
import com.jmteam.igauntlet.common.item.InfinityItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class InfinityBlocks {

    public static List<Block> BLOCKS = new ArrayList<>();

    public static Block registerBlock(Block block, String name) {
        block.setRegistryName(name);
        BLOCKS.add(block);

        Item itemBlock = new BlockItem(block, new Item.Properties()).setRegistryName(name);
        InfinityItems.ITEMS.add(itemBlock);

        return block;
    }

    public static InfinityBlock registerBlock(InfinityBlock block, String name) {
        block.setRegistryName(name);
        BLOCKS.add(block);

        InfinityItemBlock itemBlock = (InfinityItemBlock) new InfinityItemBlock(block, block.getMaxSize()).setRegistryName(name);
        block.setBlockItem(itemBlock);
        InfinityItems.ITEMS.add(itemBlock);

        return block;
    }

    private static InfinityBlock addTileEntity(BlockInfinityTileEntityBase base, String name) {
        InfinityTileEntities.addType(name, base.tileEntitySupplier, base);
        registerBlock(base, name);

        return base;
    }
}
