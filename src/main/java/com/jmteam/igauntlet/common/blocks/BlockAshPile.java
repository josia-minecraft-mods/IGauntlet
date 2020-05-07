package com.jmteam.igauntlet.common.blocks;

import com.jmteam.igauntlet.util.IHaveItem;
import com.jmteam.igauntlet.util.registry.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BlockAshPile extends FallingBlock implements IHaveItem {

    public static final VoxelShape DUST_SHAPE = VoxelShapes.create(0.296875, 0, 0.296875, 0.6900, 0.1875 / 2, 0.6900);
    public BlockItem itemBlock;


    public BlockAshPile(Material material) {
        super(Block.Properties.create(material).hardnessAndResistance(0.1f).sound(SoundType.SAND));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return DUST_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return DUST_SHAPE;
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
