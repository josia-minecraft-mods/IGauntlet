package com.jmteam.igauntlet.client.events;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.client.init.InfinityKeyBinds;
import com.jmteam.igauntlet.network.Networkhandler;
import com.jmteam.igauntlet.network.packets.PacketSnap;
import com.jmteam.igauntlet.network.packets.PacketSnap.SnapType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IGauntlet.MODID, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onClientTickEvent(final TickEvent.ClientTickEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;

        if (event.phase != TickEvent.Phase.END || player == null) return;

        if (InfinityKeyBinds.SNAP.isPressed()) {
            Networkhandler.sendServerPacket(new PacketSnap(player.isCrouching() ? SnapType.REVIVE : SnapType.SNAP));
        }
    }
}
