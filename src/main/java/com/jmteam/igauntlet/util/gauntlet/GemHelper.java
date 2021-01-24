package com.jmteam.igauntlet.util.gauntlet;

import com.jmteam.igauntlet.common.init.InfinityBlocks;
import com.jmteam.igauntlet.common.tileentity.TileEntityAshPile;
import com.jmteam.igauntlet.util.gauntlet.gems.*;
import com.jmteam.igauntlet.util.helpers.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GemHelper {

    public static void reviveAshPiles(PlayerEntity player, int range) {
        World world = player.world;
        List<BlockPos> blockPosObjects = WorldHelper.getAllInBounds(player.getPosition().add(-range, -range, -range), player.getPosition().add(range, range, range));
        boolean revivedAny = false;

        for (int i = 0; i < blockPosObjects.size(); i++) {
            BlockPos pos = blockPosObjects.get(i);
            BlockState state = world.getBlockState(pos);

            if (state.getBlock() == InfinityBlocks.ASH_PILE.get()) {
                TileEntity te = world.getTileEntity(pos);

                if (te != null && te instanceof TileEntityAshPile) {
                    TileEntityAshPile ashPile = (TileEntityAshPile) te;
                    LivingEntity entity = ashPile.getEntity();

                    if (entity != null) {
                        entity.setHealth(entity.getMaxHealth());
                        world.addEntity(entity);
                        WorldHelper.setBlockState(world, Blocks.AIR.getDefaultState(), pos);
                        revivedAny = true;
                    }
                }
            }
        }

        if (!revivedAny) {
            player.sendStatusMessage(new TranslationTextComponent("msg.revive.notfound"), true);
        }
    }

    public static boolean createAshPile(World world, BlockPos pos, LivingEntity entity) {
        if (entity != null) {
            BlockPos placePos = pos;

            if (world.getBlockState(placePos).getBlock() != Blocks.AIR) {
                if (world.getBlockState(pos.up()).getBlock() != Blocks.AIR) return false;

                placePos = pos.up();
            }

            WorldHelper.setBlockState(world, InfinityBlocks.ASH_PILE.get().getDefaultState(), placePos);
            TileEntity te = world.getTileEntity(placePos);

            if (te != null && te instanceof TileEntityAshPile) {
                TileEntityAshPile ashPile = (TileEntityAshPile) te;
                ashPile.setEntity(entity);
                ashPile.markDirty();
                return true;
            }
        }

        return false;
    }

    public static void notSetupMessage(PlayerEntity player) {
        if (!player.world.isRemote) {
            player.sendStatusMessage(new TranslationTextComponent("msg.stone.notsetup"), true);
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

        StoneType() {}

        StoneType(Supplier<GemBase> gem) {
            this.gem = gem.get();
        }

        public GemBase getGem() {
            return gem;
        }
    }
}
