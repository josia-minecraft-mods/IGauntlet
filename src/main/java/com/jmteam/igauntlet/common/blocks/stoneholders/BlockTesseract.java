package com.jmteam.igauntlet.common.blocks.stoneholders;

import com.jmteam.igauntlet.common.blocks.BlockInfinityTileEntityBase;
import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.common.init.InfinityNBT;
import com.jmteam.igauntlet.common.init.InfinityTileEntities;
import com.jmteam.igauntlet.common.tileentity.TileEntityTesseract;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTesseract extends BlockInfinityTileEntityBase {

    public static final VoxelShape TESSERACT_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.34375, 0, 0.34375, 0.65625, 0.3125, 0.65625));

    public BlockTesseract() {
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        if (!worldIn.isClientSide()) {
            TileEntity te = worldIn.getBlockEntity(pos);

            if (te instanceof TileEntityTesseract) {
                TileEntityTesseract tileEntityTesseract = (TileEntityTesseract) te;
                ItemStack stack = player.getItemInHand(handIn);

                if (stack.getItem() == InfinityItems.SPACE_STONE.get()) {

                    if (tileEntityTesseract.addStone()) {
                        stack.setCount(0);
                    }
                } else {

                    tileEntityTesseract.removeStone();
                }
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {


        if (!world.isClientSide()) {
            TileEntity te = world.getBlockEntity(pos);

            if (te != null && te instanceof TileEntityTesseract) {
                TileEntityTesseract tileEntityTesseract = (TileEntityTesseract) te;
                ItemStack stack = new ItemStack(this, 1);
                CompoundNBT compoundNBT = new CompoundNBT();
                compoundNBT.putBoolean(InfinityNBT.HAS_STONE, tileEntityTesseract.hasStone());
                stack.setTag(compoundNBT);
                world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5, stack));
            }
        }

        return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
    }

    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);

        if (!worldIn.isClientSide()) {
            TileEntity te = worldIn.getBlockEntity(pos);

            if (stack.getTag() != null && stack.getTag().contains(InfinityNBT.HAS_STONE)) {
                if (te != null && te instanceof TileEntityTesseract) {
                    ((TileEntityTesseract) te).setHas_stone(stack.getTag().getBoolean(InfinityNBT.HAS_STONE));
                }
            }
        }
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return TESSERACT_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return TESSERACT_SHAPE;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return InfinityTileEntities.TESSERACT.get().create();
    }
}
