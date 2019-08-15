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

public class GauntletHelper {

    public static void ShootLaser(EntityPlayer entityplayer, World worldIn, int damage, int r, int g, int b) {
        Vec3d v3 = entityplayer.getLook(1);
        EntityLaser laser = new EntityLaser(worldIn, entityplayer, damage, IDamageSource.LASER, new Vec3d(r, g, b));
        laser.shoot(v3.x, v3.y, v3.z, 1.5F, (float) 0);
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
