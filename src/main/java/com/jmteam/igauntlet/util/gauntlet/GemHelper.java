package com.jmteam.igauntlet.util.gauntlet;

import com.jmteam.igauntlet.common.init.InfinityBlocks;
import com.jmteam.igauntlet.common.tileentity.TileEntityAshPile;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.Iterator;

public class GemHelper {

    public static void reviveAshPiles(PlayerEntity player, int range) {
        World world = player.world;
        Iterator<BlockPos> blockPosIterator = BlockPos.getAllInBox(player.getPosition().subtract(new Vec3i(range, range, range)), player.getPosition().add(new Vec3i(range, range, range))).iterator();

        if (blockPosIterator.hasNext()) {
            BlockPos pos = blockPosIterator.next();
            BlockState state = world.getBlockState(pos);

            if (state.getBlock() == InfinityBlocks.ash_pile) {
                TileEntity te = world.getTileEntity(pos);

                if (te != null && te instanceof TileEntityAshPile) {
                    TileEntityAshPile ashPile = (TileEntityAshPile) te;
                    world.addEntity(ashPile.getEntity());
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }
        }
    }

    public static boolean createAshPile(World world, BlockPos pos, LivingEntity entity) {
        if (entity != null) {
            BlockPos placePos = pos;

            if (world.getBlockState(placePos).getBlock() != Blocks.AIR) {
                if (world.getBlockState(pos.up()).getBlock() != Blocks.AIR) return false;

                placePos = pos.up();
            }

            world.setBlockState(placePos, InfinityBlocks.ash_pile.getDefaultState());
            TileEntity te = world.getTileEntity(placePos);

            if (te != null && te instanceof TileEntityAshPile) {
                TileEntityAshPile ashPile = (TileEntityAshPile) te;
                ashPile.setEntity(entity);
                return true;
            }
        }

        return false;
    }

    public enum StoneType {
        NONE, MIND, REALITY, TIME, SPACE, POWER, SOUL
    }
}
