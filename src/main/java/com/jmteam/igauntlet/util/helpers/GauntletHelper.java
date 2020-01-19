package com.jmteam.igauntlet.util.helpers;

import com.jmteam.igauntlet.common.damage.IDamageSource;
import com.jmteam.igauntlet.common.entity.EntityLaser;
import com.jmteam.igauntlet.common.init.InfinityBlocks;
import com.jmteam.igauntlet.common.tileentity.TileAshPile;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.awt.*;

public class GauntletHelper {

    public static void ShootLaser(EntityPlayer player, World world, float damage, Color color) {
        if (!world.isRemote) ShootLaser(player, world, damage, color, 1, false);
    }

    public static void ShootLaser(EntityPlayer entityplayer, World worldIn, float damage, Color color, double size, boolean constant) {
        Vec3d v = entityplayer.getLookVec();
        EntityLaser laser = new EntityLaser(worldIn, entityplayer, damage, IDamageSource.LASER, color);
        laser.setRotationYawHead(entityplayer.rotationYawHead);
        laser.shoot(v.x, v.y, v.z, 1.5F, 0F);
        worldIn.spawnEntity(laser);
    }

    public static void makeAshPile(World world, BlockPos pos, EntityLiving entity) {
        world.setBlockState(pos, InfinityBlocks.ash_pile.getDefaultState());
        WriteAsh(pos, world, entity);
    }

    public static void WriteAsh(BlockPos pos, World world, EntityLiving entity) {
        TileEntity ash_te = world.getTileEntity(pos);
        if (ash_te != null && ash_te instanceof TileAshPile) {
            TileAshPile ash_te_f = (TileAshPile) ash_te;
            ash_te_f.setEntity(entity);
        }
    }
}
