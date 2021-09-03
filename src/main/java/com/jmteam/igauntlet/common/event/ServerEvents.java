package com.jmteam.igauntlet.common.event;

import com.jmteam.igauntlet.common.entity.EntityGauntlet;
import com.jmteam.igauntlet.common.init.InfinityItems;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class ServerEvents {

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) event.getEntity();

            System.out.println(itemEntity.getItem().getItem());

            // Spawn Gauntlet
            if (itemEntity.getItem().getItem() == InfinityItems.INFINITY_GAUNTLET.get()) {

                System.out.println("a");

                EntityGauntlet gauntlet = new EntityGauntlet(itemEntity.getItem(), event.getWorld());
                PlayerEntity player = itemEntity.level.getServer().getPlayerList().getPlayer(itemEntity.getThrower());

                if (player != null) {
                    gauntlet.setDeltaMovement(itemEntity.getDeltaMovement());
                    gauntlet.moveTo(itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), MathHelper.floor((player.yRot) + 180), 0);
                    event.getWorld().addFreshEntity(gauntlet);
                    event.setCanceled(true);
                }
            }
        }
    }
}
