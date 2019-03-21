package com.igauntlet.network.packets;

import com.igauntlet.common.function.gems.GemReality;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageFlight implements IMessage {

    public static boolean fly;
    public static EntityPlayer entityPlayer;

    public MessageFlight() {

    }

    public MessageFlight(boolean flight, EntityPlayer player) {
        this.fly = flight;
        this.entityPlayer = player;
    }

    public void fromBytes(ByteBuf buf) {
        this.fly = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.fly);
    }

    public static class Handler implements IMessageHandler<MessageFlight, IMessage> {

        @Override
        public IMessage onMessage(MessageFlight message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                GemReality.SurvivalFlight(entityPlayer, fly);
            });
            return null;
        }

    }
}
