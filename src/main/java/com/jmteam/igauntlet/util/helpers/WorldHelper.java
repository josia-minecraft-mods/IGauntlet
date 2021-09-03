package com.jmteam.igauntlet.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import java.util.ArrayList;
import java.util.List;

public class WorldHelper {

    public static void setBlockState(World world, BlockState state, BlockPos pos) {
        BlockState old = world.getBlockState(pos);
        world.setBlockAndUpdate(pos, state);
        world.sendBlockUpdated(pos, old, world.getBlockState(pos), 2);
    }

    public static BlockPos getTopSolidOrLiquidBlock(World world, BlockPos pos) {
        return world.getHeightmapPos(Heightmap.Type.WORLD_SURFACE, pos);
    }

    public static List<BlockPos> getAllInBounds(BlockPos pos1, BlockPos pos2) {
        List<BlockPos> posList = new ArrayList<>();
        BlockPos blockPos1 = new BlockPos(Math.min(pos1.getX(), pos2.getX()), Math.min(pos1.getY(), pos2.getY()), Math.min(pos1.getZ(), pos2.getZ()));
        BlockPos blockPos2 = new BlockPos(Math.max(pos1.getX(), pos2.getX()), Math.max(pos1.getY(), pos2.getY()), Math.max(pos1.getZ(), pos2.getZ()));

        for (int x = blockPos1.getX(); x < blockPos2.getX(); x++) {
            for (int y = blockPos1.getY(); y < blockPos2.getY(); y++) {
                for (int z = blockPos1.getZ(); z < blockPos2.getZ(); z++) {
                    posList.add(new BlockPos(x, y, z));
                }
            }
        }

        return posList;
    }

    public static List<BlockPos> getSameBlocksFromPosRanged(World world, BlockPos pos, int range) {
        Block b = world.getBlockState(pos).getBlock();
        List<BlockPos> beginValues = getAllInBounds(pos.offset(-range, -range, -range), pos.offset(range, range, range));
        List<BlockPos> posList = new ArrayList<>();

        for (int x = 0; x < beginValues.size(); x++) {
            BlockPos blockPos = beginValues.get(x);

            if (world.getBlockState(blockPos).getBlock() == b) {
                posList.add(blockPos);
            }
        }

        return posList;
    }
}
