package com.jmteam.igauntlet.util.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class WorldUtil {

    public static void setBlockState(World world, BlockState state, BlockPos pos) {
        BlockState old = world.getBlockState(pos);
        world.setBlockState(pos, state);
        world.notifyBlockUpdate(pos, old, world.getBlockState(pos), 2);
    }

    public static BlockPos getTopSolidOrLiquidBlock(World world, BlockPos pos) {
        BlockPos finalPos = pos;
        BlockPos pos1 = new BlockPos(pos.getX(), 256, pos.getZ());
        BlockState state1 = world.getBlockState(pos1);

        for (int y = 255; y > 0; y--) {
            BlockPos temp = new BlockPos(pos.getX(), y, pos.getZ());
            BlockState state = world.getBlockState(temp);
            finalPos = temp;

            if (state1.getBlock() == Blocks.AIR && world.getBlockState(pos1.up()).getBlock() == Blocks.AIR) {
                if (state.getBlock() != Blocks.AIR) {
                    break;
                }
            }

            pos1 = temp;
            state1 = world.getBlockState(temp);
        }

        return finalPos;
    }
}
