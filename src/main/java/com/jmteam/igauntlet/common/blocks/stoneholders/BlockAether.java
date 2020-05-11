package com.jmteam.igauntlet.common.blocks.stoneholders;

import com.jmteam.igauntlet.util.helpers.IHaveItem;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class BlockAether extends BlockFalling implements IHaveItem {

    public static final AxisAlignedBB AETHER_AABB = new AxisAlignedBB(0.375, 0, 0.3375, 0.625, 0.7, 0.6625);

    public BlockAether(Material material) {
        super(material);
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AETHER_AABB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return AETHER_AABB;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
