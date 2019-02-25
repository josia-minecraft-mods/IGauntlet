package com.igauntlet.util.helpers;

import com.igauntlet.common.damage.IDamageSource;
import com.igauntlet.common.entity.EntityLaser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GauntletHelper {

    public static void ShootLaser(EntityPlayer entityplayer, World worldIn, int damage, int r, int g, int b) {
        Vec3d v3 = entityplayer.getLook(1);
        EntityLaser laser = new EntityLaser(worldIn, entityplayer, damage, IDamageSource.LASER, new Vec3d(r, g, b));
        laser.shoot(v3.x, v3.y, v3.z, 1.5F, (float) 0);
        worldIn.spawnEntity(laser);
    }
}
