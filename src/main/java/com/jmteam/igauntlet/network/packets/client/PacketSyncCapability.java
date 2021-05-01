package com.jmteam.igauntlet.network.packets.client;

import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class PacketSyncCapability {

    private UUID player;
    private CompoundNBT compound;


    public PacketSyncCapability() {
    }

    public PacketSyncCapability(UUID player, CompoundNBT compound) {
        this.player = player;
        this.compound = compound;
    }

    public static void encode(PacketSyncCapability packet, PacketBuffer buffer) {
        buffer.writeUniqueId(packet.player);
        buffer.writeCompoundTag(packet.compound);
    }

    public static PacketSyncCapability decode(PacketBuffer buffer) {
        UUID uuid = buffer.readUniqueId();
        CompoundNBT compound = buffer.readCompoundTag();

        return new PacketSyncCapability(uuid, compound);
    }

    public static class Handler {

        public static void handle(PacketSyncCapability packet, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                Minecraft mc = Minecraft.getInstance();
                PlayerEntity player = mc.world.getPlayerByUuid(packet.player);

                if (player != null) {
                    CapabilityInfinity.get(player).deserializeNBT(packet.compound);
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }

}
