package com.jmteam.igauntlet.util.gauntlet;

import com.jmteam.igauntlet.common.blocks.InfinityBlock;
import com.jmteam.igauntlet.common.init.InfinityBlocks;
import com.jmteam.igauntlet.common.tileentity.TileEntityAshPile;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.stream.Collectors;

public class GemHelper {

    public static void reviveAshPiles(PlayerEntity player, int range) {
        World world = player.world;
        Iterator<BlockPos> blockPosIterator =  BlockPos.getAllInBox(player.getPosition().subtract(new Vec3i(range, range, range)), player.getPosition().add(new Vec3i(range, range, range))).iterator();

        if(blockPosIterator.hasNext()) {
            BlockPos pos = blockPosIterator.next();
            BlockState state = world.getBlockState(pos);

            if(state.getBlock() == InfinityBlocks.ash_pile) {
                TileEntity te = world.getTileEntity(pos);

                if(te != null && te instanceof TileEntityAshPile) {
                    TileEntityAshPile ashPile = (TileEntityAshPile) te;
                    world.addEntity(ashPile.getEntity());
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }
        }
    }

    public enum StoneType {
        NONE, MIND, REALITY, TIME, SPACE, POWER, SOUL
    }
}
