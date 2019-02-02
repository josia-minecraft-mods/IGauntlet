package com.igauntlet.common.blocks;

import com.igauntlet.Infinity;
import com.igauntlet.init.ModBlocks;
import com.igauntlet.init.ModItems;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.handlers.helpers.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class Forge extends Block implements IHasModel {

    public Forge(String name, Material material, boolean tab) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 2);
        setHardness(15.0F);
        setResistance(15.0F);

        if(tab)
        setCreativeTab(InfinityTabs.infinityTabs);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels()
    {
        Infinity.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}