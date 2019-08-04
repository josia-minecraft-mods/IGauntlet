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
        NETWORK.registerMessage(PacketTeleport.Handler.class, PacketTeleport.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketSnap.Handler.class, PacketSnap.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketWritePortal.Handler.class, PacketWritePortal.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketCapSync.Handler.class, PacketCapSync.class, id++, Side.CLIENT);
        NETWORK.registerMessage(PacketPosessFunction.Handler.class, PacketPosessFunction.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketChangePOV.Handler.class, PacketChangePOV.class, id++, Side.CLIENT);
    }
}
