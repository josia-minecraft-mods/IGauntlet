package com.jmteam.igauntlet.common.function.gems;


import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.util.handlers.client.ModKeyBinds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class GemSoul {

    public static void ProcessTakenSoul(EntityPlayer player) {
        IInfinityCap cap = CapabilityInfinity.get(player);

        if (cap.isPosessing()) {
            Entity p = cap.getPosessedEntity();

            if (p instanceof EntityLiving) {
                EntityLiving l = (EntityLiving) p;

                if (ModKeyBinds.SPECIAL.isKeyDown()) {
                    processEnderman(l);
                    processCreeper(l, player);
                }
                processGeneral(l, player);
            }
        }
    }

    public static void processGeneral(EntityLiving p, EntityPlayer player) {

        // TODO Save coords so you get tp'd back before you went all goofy
        Vec3d vec = player.getLookVec();
        if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()
                || Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown()
                || Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {

            if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() && p.onGround) {
                p.motionX = vec.x / 4;
                p.motionZ = vec.z / 4;
            }

            if (Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() && p.onGround) {
                p.motionX = -vec.x / 4;
                p.motionZ = -vec.z / 4;
            }

            if (p.getPosition().getY() < 0) clearPosessing(player);


        } else {
            p.motionX = 0;
            p.motionZ = 0;
        }

        if (Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown() && p.onGround) {
            clearPosessing(player);
        }

        if (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {
            if (p.onGround)
                p.motionY = 0.4;

            p.motionX = vec.x / 8;
            p.motionZ = vec.z / 8;
        }

        if (Minecraft.getMinecraft().gameSettings.keyBindSprint.isKeyDown()) {
            p.motionX = vec.x / 3;
            p.motionZ = vec.z / 3;
        }

        if (p.getHealth() <= 0) {
            clearPosessing(player);
        }

        p.rotationYaw = player.rotationYawHead;
        p.rotationPitch = player.rotationPitch;
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
            p.world.createExplosion(p, p.posX, p.posY, p.posZ, 5, true);
            p.setDead();
            clearPosessing(player);
        }
    }


    public static void clearPosessing(EntityPlayer p) {
            CapabilityInfinity.get(p).clearPosessing();
    }
}
