package com.jmteam.igauntlet.common.events;

import com.jmteam.igauntlet.common.entity.EntityGauntlet;
import com.jmteam.igauntlet.common.init.InfinityItems;
import net.minecraft.entity.item.ItemEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLCommonLaunchHandler;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ServerEvents {

    @SubscribeEvent
    public static void onEntityTick(EntityJoinWorldEvent event) {
        if(event.getEntity() instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) event.getEntity();

            if(itemEntity.getItem().getItem() == InfinityItems.infinity_gauntlet) {
                EntityGauntlet gauntlet = new EntityGauntlet(itemEntity.getItem(), event.getWorld());
                gauntlet.setMotion(itemEntity.getMotion());
                gauntlet.setPosition(itemEntity.getPosX(), itemEntity.getPosY(), itemEntity.getPosZ());
                gauntlet.setPositionAndRotation(itemEntity.getPosX(), itemEntity.getPosY(), itemEntity.getPosZ(), itemEntity.world.getServer().getPlayerList().getPlayerByUUID(itemEntity.getThrowerId()).rotationYaw - 45,0);
                event.getWorld().addEntity(gauntlet);
                event.setCanceled(true);
            }
        }
    }
}
