package com.igauntlet.common.items.function.gems;

import com.igauntlet.common.blocks.AshPile;
import com.igauntlet.common.tileentity.TileAshPile;
import com.igauntlet.util.helpers.PlayerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GemTime {

    public static void ReviveAsh(BlockPos pos, World worldIn, EntityPlayer player) {
        IBlockState block = worldIn.getBlockState(pos);

        if (block.getBlock() instanceof AshPile) {
            Block blk = Blocks.AIR;
            IBlockState state0 = blk.getDefaultState();
            SummonCreature(worldIn, player, pos);
            worldIn.setBlockState(pos, state0);
        }
    }

    public static void SummonCreature(World worldIn, EntityPlayer player, BlockPos pos) {
        TileEntity ash_te = worldIn.getTileEntity(pos);
        if (ash_te != null && ash_te instanceof TileAshPile) {
            worldIn.spawnEntity(((TileAshPile) ash_te).getEntity());
        }
    }



    public static void FreezeTime(EntityPlayer player, World world,int freeze, int extensionrange) {
        FreezeEntities(player, freeze, extensionrange);
        FreezeThrowable(player, freeze, extensionrange);
    }

    public static void FreezeEntities(EntityPlayer player,int freeze, int extensionrange) {
        for (EntityLiving entity : player.world.getEntitiesWithinAABB(EntityLiving.class, player.getEntityBoundingBox().grow(extensionrange, extensionrange, extensionrange))) {
            if (freeze == 1) {
                entity.setNoAI(true);
                entity.setEntityInvulnerable(true);
            } else {
                    entity.setNoAI(false);
                    entity.setEntityInvulnerable(false);
            }
        }
        if (freeze == 1) {
            PlayerHelper.sendMessage(player, "stones.time.frozen", true);
        }else{
            PlayerHelper.sendMessage(player, "stones.time.unfrozen", true);
        }
    }

    public static void FreezeThrowable(EntityPlayer player, int freeze,int extensionrange) {
        for (Entity entity : player.world.getEntitiesWithinAABB(Entity.class, player.getEntityBoundingBox().grow(extensionrange, extensionrange, extensionrange))) {
            if (entity instanceof EntityArrow || entity instanceof EntityFireball || entity instanceof EntityFireworkRocket || entity instanceof EntityThrowable) {
                if (freeze == 1) {
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
