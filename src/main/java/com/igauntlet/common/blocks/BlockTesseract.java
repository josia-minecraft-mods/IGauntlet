package com.igauntlet.common.blocks;

import com.igauntlet.Infinity;
import com.igauntlet.common.tileentity.TileTesseract;
import com.igauntlet.init.InfinityBlocks;
import com.igauntlet.init.InfinityItems;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.helpers.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTesseract extends Block implements IHasModel, ITileEntityProvider {

    public static final AxisAlignedBB TESS_AABB = new AxisAlignedBB(0.34375, 0, 0.34375, 0.65625, 0.3125, 0.65625);

    public BlockTesseract(String name, Material material, boolean tab) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setSoundType(SoundType.METAL);
        setHardness(0.0F);
        setResistance(0.1F);
        setLightLevel(0.5F);
        setLightOpacity(1);

        if (tab)
            setCreativeTab(InfinityTabs.infinityTabs);

        InfinityBlocks.BLOCKS.add(this);
        InfinityItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
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
                    if (stack.getItem() == InfinityItems.SPACESTONE) {
                        if (tess.AddStone()) {
                            stack.setCount(0);
                            return true;
                        }
                    }
                }
                tess.RemoveStone(playerIn);
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
        return true;
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTesseract();
    }
}
