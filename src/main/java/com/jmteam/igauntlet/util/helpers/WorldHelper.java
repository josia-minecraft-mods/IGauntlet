package com.jmteam.igauntlet.util.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.List;

public class WorldHelper {

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

    public static List<BlockPos> getAllInBounds(BlockPos pos1, BlockPos pos2) {
        List<BlockPos> posList = new ArrayList<>();
        BlockPos blockPos1 = new BlockPos(Math.min(pos1.getX(), pos2.getX()), Math.min(pos1.getY(), pos2.getY()), Math.min(pos1.getZ(), pos2.getZ()));
        BlockPos blockPos2 = new BlockPos(Math.max(pos1.getX(), pos2.getX()), Math.max(pos1.getY(), pos2.getY()), Math.max(pos1.getZ(), pos2.getZ()));

        for(int x = blockPos1.getX(); x < blockPos2.getX(); x++) {
            for(int y = blockPos1.getY(); y < blockPos2.getY(); y++) {
                for(int z = blockPos1.getZ(); z < blockPos2.getZ(); z++) {
                    posList.add(new BlockPos(x, y, z));
                }
            }
        }

        return posList;
    }
}
