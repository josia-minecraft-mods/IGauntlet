package com.jmteam.igauntlet.network;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.network.packets.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Infinity.MODID);
    private static int id = -1;

    public static void init() {
        NETWORK.registerMessage(PacketStone.Handler.class, PacketStone.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketNotAdded.Handler.class, PacketNotAdded.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketSpace.Handler.class, PacketSpace.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketNoCoords.Handler.class, PacketNoCoords.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketSnap.Handler.class, PacketSnap.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketWritePortal.Handler.class, PacketWritePortal.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketPortalTeleport.Handler.class, PacketPortalTeleport.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketCapSync.Handler.class, PacketCapSync.class, id++, Side.CLIENT);
        NETWORK.registerMessage(PacketPosessFunction.Handler.class, PacketPosessFunction.class, id++, Side.SERVER);
    }
}
