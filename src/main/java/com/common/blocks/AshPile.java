package com.common.blocks;


import com.tabs.InfinityTabs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class AshPile extends BlockBase {

    public AshPile(String name, Material material)
    {
        super(name, material);
        setSoundType(SoundType.SAND);
        setHardness(0.5F);
        setResistance(0.1F);
        setCreativeTab(InfinityTabs.infinityTabs);
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
