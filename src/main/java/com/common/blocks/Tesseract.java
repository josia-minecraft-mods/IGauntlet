package com.common.blocks;

import com.Infinity;
import com.init.ModBlocks;
import com.init.ModItems;
import com.tabs.InfinityTabs;
import com.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Tesseract extends Block implements IHasModel {

    public static final AxisAlignedBB TESS_AABB = new AxisAlignedBB(0.34375,0,0.34375,0.65625,0.3125,0.65625);

    public Tesseract(String name, Material material, boolean tab)
    {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setSoundType(SoundType.METAL);
        setHardness(0.0F);
        setResistance(0.1F);
        setLightOpacity(1);

        if(tab)
            setCreativeTab(InfinityTabs.infinityTabs);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public void registerModels()
    {
        Infinity.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return TESS_AABB;
    }
}
