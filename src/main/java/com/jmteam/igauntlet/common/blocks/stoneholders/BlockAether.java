package com.jmteam.igauntlet.common.blocks.stoneholders;

import com.jmteam.igauntlet.util.registry.InfinityRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BlockAether extends FallingBlock {
    public static final VoxelShape AETHER_SHAPE = VoxelShapes.create(0.375, 0, 0.3375, 0.625, 0.7, 0.6625);

    public BlockAether(Material material) {
        super(Properties.create(material).hardnessAndResistance(0.1f).sound(SoundType.STONE));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return AETHER_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return AETHER_SHAPE;
    }
}
