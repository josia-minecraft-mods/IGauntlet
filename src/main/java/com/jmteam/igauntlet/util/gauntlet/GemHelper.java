package com.jmteam.igauntlet.util.gauntlet;

import com.jmteam.igauntlet.common.init.InfinityBlocks;
import com.jmteam.igauntlet.common.init.InfinityMessages;
import com.jmteam.igauntlet.common.tileentity.TileEntityAshPile;
import com.jmteam.igauntlet.util.gauntlet.gems.*;
import com.jmteam.igauntlet.util.helpers.WorldHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Supplier;

public class GemHelper {

    public static void reviveAshPiles(PlayerEntity player, int range) {
        World world = player.level;
        List<BlockPos> blockPosObjects = WorldHelper.getAllInBounds(player.blockPosition().offset(-range, -range, -range), player.blockPosition().offset(range, range, range));
        int revivedCount = 0;

        for (int i = 0; i < blockPosObjects.size(); i++) {
            BlockPos pos = blockPosObjects.get(i);
            BlockState state = world.getBlockState(pos);

            if (state.getBlock() == InfinityBlocks.ASH_PILE.get()) {
                TileEntity te = world.getBlockEntity(pos);

                if (te != null && te instanceof TileEntityAshPile) {
                    TileEntityAshPile ashPile = (TileEntityAshPile) te;
                    LivingEntity entity = ashPile.getEntity();

                    if (entity != null) {
                        entity.setHealth(entity.getMaxHealth());
                        world.addFreshEntity(entity);
                        WorldHelper.setBlockState(world, Blocks.AIR.defaultBlockState(), pos);
                        revivedCount++;
                    }
                }
            }
        }

        if (revivedCount == 0)
            player.displayClientMessage(InfinityMessages.getComponent(InfinityMessages.REVIVE_NOT_FOUND), true);
        else {
            player.displayClientMessage(InfinityMessages.getComponent(revivedCount > 1 ? InfinityMessages.REVIVE_AMOUNT_MULTIPLE : InfinityMessages.REVIVE_AMOUNT, String.valueOf(revivedCount)), true);
        }
    }

    public static boolean createAshPile(World world, BlockPos pos, LivingEntity entity) {
        if (entity != null) {
            BlockPos placePos = pos;

            if (world.getBlockState(placePos).getBlock() != Blocks.AIR) {
                if (world.getBlockState(pos.above()).getBlock() != Blocks.AIR) return false;

                placePos = pos.above();
            }

            WorldHelper.setBlockState(world, InfinityBlocks.ASH_PILE.get().defaultBlockState(), placePos);
            TileEntity te = world.getBlockEntity(placePos);

            if (te != null && te instanceof TileEntityAshPile) {
                TileEntityAshPile ashPile = (TileEntityAshPile) te;
                ashPile.setEntity(entity);
                ashPile.setChanged();
                return true;
            }
        }

        return false;
    }

    public static void notSetupMessage(PlayerEntity player) {
        if (!player.level.isClientSide()) {
            player.displayClientMessage(InfinityMessages.getComponent(InfinityMessages.STONE_NO_FUNCTION), true);
        }
    }

    public enum StoneType {

        NONE,
        MIND(GemMind::new),
        TIME(GemTime::new),
        REALITY(GemReality::new),
        SPACE(GemSpace::new),
        POWER(GemPower::new),
        SOUL(GemSoul::new);

        private GemBase gem;

        StoneType() {
        }

        StoneType(Supplier<GemBase> gem) {
            this.gem = gem.get();
        }

        public GemBase getGem() {
            return gem;
        }
    }
}
