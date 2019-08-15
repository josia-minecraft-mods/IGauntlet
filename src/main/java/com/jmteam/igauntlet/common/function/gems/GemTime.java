package com.jmteam.igauntlet.common.function.gems;

import com.jmteam.igauntlet.common.blocks.BlockAshPile;
import com.jmteam.igauntlet.common.tileentity.TileAshPile;
import com.jmteam.igauntlet.util.InfinityConfig;
import com.jmteam.igauntlet.util.helpers.PlayerHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GemTime {

    public static void ReviveAsh(BlockPos p, World worldIn) {
        IBlockState block = worldIn.getBlockState(p);
        boolean canRevive = InfinityConfig.Gauntlet.ReviveAsh;

        if (block.getBlock() instanceof BlockAshPile && canRevive) {
            SummonCreature(worldIn, p);
        }

    }

    public static void SummonCreature(World worldIn, BlockPos pos) {
        TileAshPile ash_te = (TileAshPile) worldIn.getTileEntity(pos);
        if (ash_te != null && ash_te instanceof TileAshPile) {
            ash_te.summonEntity();
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }


    public static void FreezeTime(EntityPlayer player, World world, boolean freeze, int extensionrange) {
        FreezeEntities(player, freeze, extensionrange);
        FreezeThrowable(player, freeze, extensionrange);
    }

    public static void FreezeEntities(EntityPlayer player, boolean freeze, int extensionrange) {
        for (EntityLiving entity : player.world.getEntitiesWithinAABB(EntityLiving.class, player.getEntityBoundingBox().grow(extensionrange, extensionrange, extensionrange))) {
            if (freeze) {
                entity.setNoAI(true);
                entity.setEntityInvulnerable(true);
            } else {
                entity.setNoAI(false);
                entity.setEntityInvulnerable(false);
            }
        }
        if (freeze) {
            PlayerHelper.sendMessage(player, "stones.time.frozen", true);
        } else {
            PlayerHelper.sendMessage(player, "stones.time.unfrozen", true);
        }
    }

    public static void FreezeThrowable(EntityPlayer player, boolean freeze, int extensionrange) {
        for (Entity entity : player.world.getEntitiesWithinAABB(Entity.class, player.getEntityBoundingBox().grow(extensionrange, extensionrange, extensionrange))) {
            if (entity instanceof IProjectile) {
                if (freeze) {
                    entity.setNoGravity(true);
                    entity.setVelocity(0, 0, 0);
                } else {
                    entity.setNoGravity(false);
                }
                entity.velocityChanged = true;
            }
        }
    }

}
