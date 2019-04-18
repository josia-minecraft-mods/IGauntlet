package com.igauntlet.common.blocks;

import com.igauntlet.common.tileentity.TileAshPile;
import com.igauntlet.util.helpers.IHaveItem;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockAshPile extends BlockFalling implements IHaveItem, ITileEntityProvider {


    public static final AxisAlignedBB DUST_AABB = new AxisAlignedBB(0.296875, 0, 0.296875, 0.6900, 0.1875 / 2, 0.6900);


    public BlockAshPile(Material material) {
        super(material);
        setSoundType(SoundType.SAND);
        setHardness(0.0F);
        setResistance(0.1F);
        setLightOpacity(1);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote) {
            TileAshPile tileAshPile = (TileAshPile) worldIn.getTileEntity(pos);
        }
        return true;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }


    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return DUST_AABB;
    }


    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return DUST_AABB;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int data) {
        return new TileAshPile();
    }

    @Override
    public boolean hasItem() {
        return true;
    }
}
