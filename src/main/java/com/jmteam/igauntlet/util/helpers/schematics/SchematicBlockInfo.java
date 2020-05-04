package com.jmteam.igauntlet.util.helpers.schematics;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class SchematicBlockInfo {

    public int blockState;
    public String nbtTag;
    public transient NBTTagCompound compound;
    public boolean isTileEntity = false;
    public long reference;


    public SchematicBlockInfo(IBlockState state, TileEntity tileEntity, BlockPos referencePos) {
        this.blockState = Block.getStateId(state);
        this.reference = referencePos.toImmutable().toLong();

        if (tileEntity != null) {
            isTileEntity = true;
            NBTTagCompound tagCompound = tileEntity.serializeNBT();

            if (tagCompound != null) {
                nbtTag = tagCompound.toString();
            }
        }
    }

    public IBlockState getBlockState() {
        return Block.getStateById(blockState);
    }

    public NBTTagCompound getTileTag() {
        return compound;
    }

    public boolean isTileEntity() {
        return isTileEntity;
    }

    public BlockPos getReference() {
        return BlockPos.fromLong(reference);
    }
}

