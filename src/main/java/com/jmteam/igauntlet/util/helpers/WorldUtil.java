package com.jmteam.igauntlet.util.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldUtil {

    public static void setBlockState(World world, BlockState state, BlockPos pos) {
        BlockState old = world.getBlockState(pos);
        world.setBlockState(pos, state);
        world.notifyBlockUpdate(pos, old, world.getBlockState(pos), 2);
    }

    public static int getTopBlockSolidOrWaterY(World world, BlockPos pos) {
        int x = pos.getX();
        int z = pos.getZ();

           for (int y = world.getServer().getBuildLimit(); y > 0 ; y--) {
                BlockPos pos1 = new BlockPos(x, y, z);
                BlockState state = world.getBlockState(pos1);

                if (state.getBlock() == Blocks.AIR || state.getBlock() == Blocks.WATER) {
                    System.out.println("a");
                    if (world.getBlockState(pos.add(0, 1, 0)).getBlock() == Blocks.AIR && world.getBlockState(pos.down()).getBlock() != Blocks.AIR) {
                        System.out.println("a");
                        return y;
                    }
                }
            }

        return pos.getY();
    }
}
