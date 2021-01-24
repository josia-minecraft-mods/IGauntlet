package com.jmteam.igauntlet.network;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.network.packets.client.PacketSyncCapability;
import com.jmteam.igauntlet.network.packets.server.PacketSetStone;
import com.jmteam.igauntlet.network.packets.server.PacketSnap;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * Honestly i don't know if it works, i don't have a packet to test or a feature
 */
public class NetworkHandler {

    public static int id = 0;
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(IGauntlet.MODID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void register() {
        IGauntlet.LOGGER.info("Registering Packets");
        INSTANCE.registerMessage(id++, PacketSetStone.class, PacketSetStone::encode, PacketSetStone::decode, PacketSetStone.Handler::handle);
        INSTANCE.registerMessage(id++, PacketSnap.class, PacketSnap::encode, PacketSnap::decode, PacketSnap.Handler::handle);
        INSTANCE.registerMessage(id++, PacketSyncCapability.class, PacketSyncCapability::encode, PacketSyncCapability::decode, PacketSyncCapability.Handler::handle); 
    }

    public static void sendToAllClients(Object packet, World world) {
        world.getServer().getPlayerList().getPlayers().forEach(p -> sendTo(p, packet));
    }

    public static void sendTo(ServerPlayerEntity playerEntity, Object packet) {
        INSTANCE.sendTo(packet, playerEntity.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendServerPacket(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}
