package com.jmteam.igauntlet.common.blocks;

import com.jmteam.igauntlet.common.tileentity.TileEntityQuickSand;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class BlockQuickSand extends FallingBlock {

    public BlockItem item;

    public BlockQuickSand(Material material) {
        super(Block.Properties.of(material).sound(SoundType.SAND));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(BlockState state, World world, BlockPos blockPos, Entity entity) {
        entity.makeStuckInBlock(state, new Vector3d(0.25D, 0.05F, 0.25D));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack(Blocks.SAND, 1));
    }

    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        TileEntity te = worldIn.getBlockEntity(pos);

        if (!worldIn.isClientSide()) {
            if (te instanceof TileEntityQuickSand) {
                ((TileEntityQuickSand) te).setPlaced();
            }
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityQuickSand();
    }
}
