package com.jmteam.igauntlet.client.events;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.client.init.InfinityKeyBinds;
import com.jmteam.igauntlet.network.Networkhandler;
import com.jmteam.igauntlet.network.packets.PacketSnap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IGauntlet.MODID, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onClientTickEvent(final TickEvent.ClientTickEvent event) {

        if (event.phase != TickEvent.Phase.END) return;


        if(InfinityKeyBinds.SNAP.isPressed()) {
            Networkhandler.sendServerPacket(new PacketSnap(PacketSnap.SnapType.SNAP));
        }
    }
}
