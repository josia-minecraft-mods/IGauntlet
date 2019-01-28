package com.network;

import com.network.packets.MessageNoCoords;
import com.network.packets.MessageNotAdded;
import com.network.packets.MessageSpace;
import com.network.packets.MessageStone;
import com.util.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    public static void init() {
        NETWORK.registerMessage(MessageStone.Handler.class, MessageStone.class, 1, Side.SERVER);
        NETWORK.registerMessage(MessageNotAdded.Handler.class, MessageNotAdded.class, 2, Side.SERVER);
        NETWORK.registerMessage(MessageSpace.Handler.class, MessageSpace.class, 3, Side.SERVER);
        NETWORK.registerMessage(MessageNoCoords.Handler.class, MessageNoCoords.class, 4, Side.SERVER);
    }
}
