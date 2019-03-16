package com.igauntlet.common.blocks;

import com.igauntlet.Infinity;
import com.igauntlet.init.InfinityBlocks;
import com.igauntlet.init.InfinityItems;
import com.igauntlet.util.helpers.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {

    public BlockBase(String name, Material material, boolean tab) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);


        InfinityBlocks.BLOCKS.add(this);
        InfinityItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}
