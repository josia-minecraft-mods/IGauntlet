package com.jmteam.igauntlet.common.blocks.stoneholders;

import com.jmteam.igauntlet.common.blocks.BlockBase;
import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.common.init.InfinityNbtKeys;
import com.jmteam.igauntlet.common.tileentity.TileTesseract;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockTesseract extends BlockBase implements ITileEntityProvider {

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
            ItemStack stack = playerIn.getHeldItem(hand);
            TileEntity te = worldIn.getTileEntity(pos);

            if (te instanceof TileTesseract) {
                TileTesseract tileTesseract = (TileTesseract) te;
                if (stack.getItem() != null) {
                    if (stack.getItem() == InfinityItems.space_stone) {
                        if (tileTesseract.addStone()) {
                            stack.setCount(0); // TODO Change with a holds stone boolean
                            return true;
                        }
                    }
                }
                tileTesseract.removeStone(playerIn);
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

        TileTesseract tileTesseract = (TileTesseract) worldIn.getTileEntity(pos);
        ItemStack stack = new ItemStack(this, 1);

        if (tileTesseract != null) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setBoolean(InfinityNbtKeys.HAS_STONE, tileTesseract.has_stone);
            stack.setTagCompound(compound);
            worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack));
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.AIR;
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);


        if (stack != null) {

            if (stack.getTagCompound() != null) {
                TileEntity te = worldIn.getTileEntity(pos);

                if (te != null && te instanceof TileTesseract) {
                    TileTesseract tileTesseract = (TileTesseract) te;
                    tileTesseract.setHas_stone(stack.getTagCompound().getBoolean(InfinityNbtKeys.HAS_STONE));
                }
            }
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTesseract();
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
