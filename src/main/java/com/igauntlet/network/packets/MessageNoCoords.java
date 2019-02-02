package com.igauntlet.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class MessageNoCoords implements IMessage {

    public static boolean statusbar;


    public MessageNoCoords() {
    }

    public MessageNoCoords(boolean statusbar) {
        this.statusbar = statusbar;
    }

    public void fromBytes(ByteBuf buf) {
        this.statusbar = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.statusbar);
    }

    public static class Handler implements IMessageHandler<MessageNoCoords, IMessage> {

        @Override
        public IMessage onMessage(MessageNoCoords message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayer player = ctx.getServerHandler().player;
                player.sendStatusMessage(new TextComponentString("You didn't define any coordinates!"), statusbar);
            });
            return null;
        }

    }
}
