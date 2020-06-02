package com.jmteam.igauntlet.util.gauntlet;

import com.jmteam.igauntlet.common.blocks.InfinityBlock;
import com.jmteam.igauntlet.common.init.InfinityBlocks;
import com.jmteam.igauntlet.common.tileentity.TileEntityAshPile;
import com.jmteam.igauntlet.util.gauntlet.gems.*;
import com.jmteam.igauntlet.util.helpers.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GemHelper {

    public static void reviveAshPiles(PlayerEntity player, int range) {
        World world = player.world;
        Iterator<BlockPos> blockPosIterator = BlockPos.getAllInBox(player.getPosition().add(-range, -range, -range), player.getPosition().add(range, range, range)).iterator();
        boolean revivedAny = false;

        while (blockPosIterator.hasNext()) {
            BlockPos pos = blockPosIterator.next();
            BlockState state = world.getBlockState(pos);

            if (state.getBlock() == InfinityBlocks.ash_pile) {
                TileEntity te = world.getTileEntity(pos);

                if (te != null && te instanceof TileEntityAshPile) {
                    TileEntityAshPile ashPile = (TileEntityAshPile) te;
                    LivingEntity entity = ashPile.getEntity();
                    revivedAny = true;

                    if (entity != null) {
                        entity.setHealth(entity.getMaxHealth());
                        world.addEntity(entity);
                        WorldUtil.setBlockState(world, Blocks.AIR.getDefaultState(), pos);
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

            WorldUtil.setBlockState(world, InfinityBlocks.ash_pile.getDefaultState(), placePos);
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

    public static List<BlockPos> getAllBlocksRanged(World world, BlockPos pos) {
        Block b = world.getBlockState(pos).getBlock();
        Iterator<BlockPos> blockPosIterator = BlockPos.getAllInBox(pos.add(-20, -20, -20), pos.add(20, 20, 20)).iterator();
        List<BlockPos> posList = new ArrayList<>();


        while (blockPosIterator.hasNext()) {
            BlockPos blockPos = blockPosIterator.next();
            if(world.getBlockState(blockPos).getBlock() == b) {
                posList.add(blockPos.toImmutable());
            }
        }

        return posList;
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
