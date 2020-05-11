package com.jmteam.igauntlet.common.blocks.stoneholders;

import com.jmteam.igauntlet.util.registry.IHaveItem;
import com.jmteam.igauntlet.util.registry.RegistryHelper;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BlockAether extends FallingBlock implements IHaveItem {
    public static final VoxelShape AETHER_SHAPE = VoxelShapes.create(0.375, 0, 0.3375, 0.625, 0.7, 0.6625);
    public BlockItem itemBlock;


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

    @Override
    public int getMaxSize() {
        return 1;
    }

    @Override
    public void setItem(BlockItem item) {
        this.itemBlock = item;
    }

    public Block setGroup(ItemGroup group) {
        RegistryHelper.setCreativeTab(itemBlock, group);
        return this;
    }

}
