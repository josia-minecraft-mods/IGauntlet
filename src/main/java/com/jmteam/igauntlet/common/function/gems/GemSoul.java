package com.jmteam.igauntlet.common.function.gems;


import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;

public class GemSoul {

    public static void ProcessTakenSoul(EntityPlayer player) {
        IInfinityCap cap = CapabilityInfinity.get(player);

        if (cap.isPosessing()) {
            Entity p = cap.getPosessedEntity();


            if (p instanceof EntityLiving) {
                EntityLiving l = (EntityLiving) p;

                // processGeneral(l, player);
            }
        }
    }

    public static void processGeneral(EntityLiving p, EntityPlayer player, String f) {

        // TODO Save coords so you get tp'd back before you went all goofy
        Vec3d vec = player.getLookVec();
        if (!f.equals("")) {

            if (!isFlyingEntity(p)) {
                if (f.equalsIgnoreCase("forward") && p.onGround) {
                    p.motionX = vec.x / 4;
                    p.motionZ = vec.z / 4;
                }

                if (f.equalsIgnoreCase("back") && p.onGround) {
                    p.motionX = -vec.x / 4;
                    p.motionZ = -vec.z / 4;
                }

                if (p.getPosition().getY() < 0) clearPosessing(player);

            } else {
                if (isFlyingEntity(p)) {
                    p.motionX = vec.x / 4;
                    p.motionY = vec.y / 3;
                    p.motionZ = vec.z / 4;
                }
            }

        } else {
            p.motionX = 0;
            p.motionZ = 0;
        }

        if (f.equalsIgnoreCase("sneak")) {
            clearPosessing(player);
        }

        if (f.equalsIgnoreCase("jump")) {
            if (p.onGround)
                p.motionY = 0.4;

            p.motionX = vec.x / 8;
            p.motionZ = vec.z / 8;
        }

        if (f.equalsIgnoreCase("sprint")) {
            p.motionX = vec.x / 3;
            p.motionZ = vec.z / 3;
        }

        if (p.getHealth() <= 0) {
            clearPosessing(player);
        }

        p.rotationYaw = player.rotationYawHead;
        p.rotationPitch = player.rotationPitch;
    }

    public static void useSpecialFunction(EntityLiving l, EntityPlayer player) {
        processEnderman(l);
        processCreeper(l, player);
    }

    public static boolean isFlyingEntity(EntityLiving e) {
        if (e instanceof EntityFlying) return true;
        if (e instanceof EntityBat) return true;
        return false;
    }


    public static void processEnderman(EntityLiving p) {
        if (p instanceof EntityEnderman) {
            BlockPos fpos = p.getPosition();
            int looped = 0;

            for (BlockPos pos : BlockPos.getAllInBox((int) p.posX - 20, (int) p.posY - 10, (int) p.posZ - 20, (int) p.posX + 20, (int) p.posY + 10, (int) p.posZ + 20)) {
                if (p.world.getBlockState(pos.down()).getBlock() != Blocks.AIR) {
                    fpos = pos;
                    looped++;
                    if (looped >= 10)
                        break;
                }
            }
            p.setPositionAndUpdate(fpos.getX(), fpos.getY(), fpos.getZ());
            // TODO Add tp sound
        }
    }

    public static void processCreeper(EntityLiving p, EntityPlayer player) {
        if (p instanceof EntityCreeper) {
            p.world.createExplosion(p, p.posX, p.posY, p.posZ, 3, true);
            p.setDead();
            clearPosessing(player);
        }
    }

    public static void startPosessing(EntityPlayer player, EntityLiving e, IInfinityCap cap) {
        cap.setPosessedEntity(e);
        cap.setLastPos(player.getPosition());
        cap.setPosessing(true);
        player.setEntityInvulnerable(true);
        player.startRiding(e);
        // TODO Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
    }


    public static void clearPosessing(EntityPlayer p) {
        IInfinityCap cap = CapabilityInfinity.get(p);
        cap.clearPosessing();
        cap.sync();
        p.dismountRidingEntity();
        p.setPositionAndUpdate(cap.getLastPos().getX(), cap.getLastPos().getY(), cap.getLastPos().getZ());
        p.sendStatusMessage(new TextComponentTranslation("gauntlet.soul.tpback"), true);
    }
}
