package com.common.blocks;


import com.Infinity;
import com.client.gui.GuiGauntlet;
import com.common.items.InfinityGauntlet;
import com.common.items.gems.GemTime;
import com.common.tileentity.TileAshPile;
import com.init.ModBlocks;
import com.init.ModItems;
import com.util.IHasModel;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AshPile extends BlockFalling implements IHasModel, ITileEntityProvider {

    public static final AxisAlignedBB DUST_AABB = new AxisAlignedBB(0.296875,0,0.296875,0.6900,0.1875 / 2,0.6900);

    public AshPile(String name, Material material, boolean tab)
    {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setSoundType(SoundType.SAND);
        setHardness(0.0F);
        setResistance(0.1F);
        setLightOpacity(1);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
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


    @Override
    public void registerModels()
    {
        Infinity.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int data) {
        return new TileAshPile();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity teash = worldIn.getTileEntity(pos);
        if(teash != null && teash instanceof TileAshPile && !worldIn.isRemote) {
            TileAshPile teasht = (TileAshPile) teash;
            // playerIn.sendMessage(new TextComponentString("ID:" + teasht.getEntity()));
        }
        return true;
    }
}
