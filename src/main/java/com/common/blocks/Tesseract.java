package com.common.blocks;

import com.Infinity;
import com.common.tileentity.TileTesseract;
import com.init.ModBlocks;
import com.init.ModItems;
import com.tabs.InfinityTabs;
import com.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class Tesseract extends Block implements IHasModel, ITileEntityProvider {

    public static final AxisAlignedBB TESS_AABB = new AxisAlignedBB(0.34375, 0, 0.34375, 0.65625, 0.3125, 0.65625);

    public Tesseract(String name, Material material, boolean tab) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setSoundType(SoundType.METAL);
        setHardness(0.0F);
        setResistance(0.1F);
        setLightOpacity(1);

        if (tab)
            setCreativeTab(InfinityTabs.infinityTabs);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
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
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
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
                    if (stack.getItem() == ModItems.SPACESTONE) {
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


    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);

        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileTesseract) {
            TileTesseract tess = (TileTesseract) tileentity;

            ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this));
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setInteger("BlockEntityTag", tess.GetStone());
            itemstack.setTagCompound(nbttagcompound);

            spawnAsEntity(worldIn, pos, itemstack);
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileTesseract) {
            TileTesseract tess = (TileTesseract) tileentity;

            if (stack.hasTagCompound()) {
                NBTTagCompound nbt = stack.getTagCompound();
                int data = nbt.getInteger("BlockEntityTag");
                if (data == 1) {
                    tess.AddStone();
                }
            }
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTesseract();
    }
}
