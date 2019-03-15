package com.igauntlet.util.handlers;

import com.igauntlet.Infinity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Infinity.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void CancelEnemy(LivingSetAttackTargetEvent event) {
        EntityLivingBase entity = event.getEntityLiving();

        if (entity != null && !entity.world.isRemote && entity.getEntityData().getBoolean("friendly")) {
            if (event.getTarget() != null) {
                entity.setRevengeTarget(null);

                if (entity instanceof EntityLiving) {
                    ((EntityLiving) entity).setAttackTarget(null);
                }
            }
        }
    }
}
