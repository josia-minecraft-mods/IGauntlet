package com.jmteam.igauntlet.common.blocks.stoneholders;

import com.jmteam.igauntlet.common.blocks.InfinityBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BlockOrb extends InfinityBlock {

    public static final VoxelShape POWERORBAAB = VoxelShapes.create(0.1875, 0, 0.1875, 0.8125, 0.6875, 0.8125);

    public BlockOrb(Material material) {
        super(material);
    }

    @Override
    public int getMaxSize() {
        return 1;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return POWERORBAAB;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return POWERORBAAB;
    }
}
