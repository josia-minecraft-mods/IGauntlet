package com.jmteam.igauntlet.common.blocks;

import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.common.tileentity.TileTesseract;
import com.jmteam.igauntlet.util.helpers.IHaveItem;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTesseract extends Block implements IHaveItem, ITileEntityProvider {

    public static final AxisAlignedBB TESS_AABB = new AxisAlignedBB(0.34375, 0, 0.34375, 0.65625, 0.3125, 0.65625);

    public BlockTesseract(Material material) {
        super(material);
        setSoundType(SoundType.METAL);
        setHardness(0.0F);
        setResistance(0.1F);
        setLightLevel(0.5F);
        setLightOpacity(1);
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
        return TESS_AABB;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItemMainhand();
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof TileTesseract) {
                TileTesseract tess = (TileTesseract) te;
                if (stack.getItem() != null) {
                    if (stack.getItem() == InfinityItems.space_stone) {
                        if (tess.AddStone()) {
                            stack.setCount(0);
                            return true;
                        }
                    }
                }
                tess.RemoveStone(playerIn);
            }
        }
        return true;
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTesseract();
    }

    @Override
    public boolean hasItem() {
        return true;
    }
}
