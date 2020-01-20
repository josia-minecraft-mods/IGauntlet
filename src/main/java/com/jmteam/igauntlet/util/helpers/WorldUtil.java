package com.jmteam.igauntlet.util.helpers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;

public class WorldUtil {

    public static boolean setBlock(World world, BlockPos pos, IBlockState iBlockState, int flags) {
        if (world.isOutsideBuildHeight(pos)) {
            return false;
        } else if (!world.isRemote && world.getWorldInfo().getTerrainType() == WorldType.DEBUG_ALL_BLOCK_STATES) {
            return false;
        } else {
            Chunk chunk = world.getChunk(pos);

            pos = pos.toImmutable(); // Forge - prevent mutable BlockPos leaks
            IBlockState iblockstate = chunk.setBlockState(pos, iBlockState);

            if (iblockstate == null) {
                return false;
            } else {
                world.markAndNotifyBlock(pos, chunk, iblockstate, iBlockState, flags);
                return true;
            }
        }
    }
}
