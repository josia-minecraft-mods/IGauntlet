package com.jmteam.igauntlet.common.events;

import com.jmteam.igauntlet.common.entity.EntityGauntlet;
import com.jmteam.igauntlet.common.init.InfinityItems;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ServerEvents {

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) event.getEntity();

            if (itemEntity.getItem().getItem() == InfinityItems.infinity_gauntlet) {
                EntityGauntlet gauntlet = new EntityGauntlet(itemEntity.getItem(), event.getWorld());
                PlayerEntity player = itemEntity.world.getServer().getPlayerList().getPlayerByUUID(itemEntity.getThrowerId());

                if (player != null) {
                    gauntlet.setMotion(itemEntity.getMotion());
                    gauntlet.setPositionAndRotation(itemEntity.getPosX(), itemEntity.getPosY(), itemEntity.getPosZ(), MathHelper.floor((player.rotationYaw) + 180), 0);
                    event.getWorld().addEntity(gauntlet);
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void worldTickEvent(TickEvent.WorldTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
        }
    }
}
