package com.jmteam.igauntlet.util.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldUtil {

    public static void setBlockState(World world, BlockState state, BlockPos pos) {
        BlockState old = world.getBlockState(pos);
        world.setBlockState(pos, state);
        world.notifyBlockUpdate(pos, old, world.getBlockState(pos), 2);
    }
}
