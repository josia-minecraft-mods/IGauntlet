package com.jmteam.igauntlet.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class InfinityBlock extends InfinityFacingBlock {

    public InfinityBlock(Material material) {
        super(Block.Properties.of(material).harvestLevel(0).strength(15.0f).harvestTool(ToolType.PICKAXE));
    }

    public InfinityBlock(Properties properties) {
        super(properties.harvestLevel(0));
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        return super.use(blockState, world, blockPos, player, hand, rayTraceResult);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return super.getShape(state, worldIn, pos, context);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return super.getCollisionShape(state, worldIn, pos, context);
    }
}
