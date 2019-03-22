package com.igauntlet.util.handlers;

import com.igauntlet.Infinity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Infinity.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void CancelEnemy(LivingSetAttackTargetEvent event) {
        EntityLivingBase entity = event.getEntityLiving();

        if (entity != null && !entity.world.isRemote && entity.getEntityData().getBoolean("isfriend")) {
            if (event.getTarget() != null) {
                entity.setRevengeTarget(null);

                if (entity instanceof EntityLiving) {
                    ((EntityLiving) entity).setAttackTarget(null);
                }
            }
        }
    }

    @SubscribeEvent
    public static void CancelAttack(LivingAttackEvent e) {
        if(e.getSource() == null || e.getSource().getTrueSource() == null) return;
        if(e.getSource().getTrueSource().getEntityData().getBoolean("isfriend")) {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void DenyExplosion(ExplosionEvent e) {
        Entity entity = e.getExplosion().getExplosivePlacedBy();
        if (entity.getEntityData().getBoolean("isfriend")) {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void EndermanTeleport(EnderTeleportEvent e) {
        if(e.getEntityLiving().getEntityData().getBoolean("isfriend")) {
            e.setCanceled(true);
        }
    }
}
