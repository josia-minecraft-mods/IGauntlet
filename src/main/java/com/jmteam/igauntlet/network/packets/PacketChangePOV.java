package com.jmteam.igauntlet.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;


public class PacketChangePOV implements IMessage {

    private int pov;
    private EntityPlayer player;

    public PacketChangePOV() {
    }

    public PacketChangePOV(EntityPlayer player, int pov) {
        this.player = player;
        this.pov = pov;
    }

    public void fromBytes(ByteBuf buf) {
        if (Minecraft.getMinecraft().player == null) return;
        player = Minecraft.getMinecraft().player.world.getPlayerEntityByUUID(UUID.fromString(ByteBufUtils.readUTF8String(buf)));
        pov = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, player.getGameProfile().getId().toString());
        buf.writeInt(pov);
    }

    public static class Handler implements IMessageHandler<PacketChangePOV, IMessage> {

        @Override
        public IMessage onMessage(PacketChangePOV message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                EntityPlayer player = message.player;
                @Override
                public void run() {
                    if(player != null)
                        Minecraft.getMinecraft().gameSettings.thirdPersonView = message.pov;
                }
            });
            return null;
        }
    }
}
