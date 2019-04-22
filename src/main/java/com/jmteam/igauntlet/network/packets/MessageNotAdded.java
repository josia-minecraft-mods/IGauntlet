package com.jmteam.igauntlet.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class MessageNotAdded implements IMessage {

    public static int stone;
    public static int button;


    public MessageNotAdded() {
    }

    public MessageNotAdded(int stone, int button) {
        this.stone = stone;
        this.button = button;
    }

    public void fromBytes(ByteBuf buf) {
        this.stone = buf.readInt();
        this.button = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.stone);
        buf.writeInt(this.button);
    }

    public static class Handler implements IMessageHandler<MessageNotAdded, IMessage> {

        @Override
        public IMessage onMessage(MessageNotAdded message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayer player = ctx.getServerHandler().player;
                player.sendStatusMessage(new TextComponentString("This stone hasn't been added yet! Stone : " + button), true);
            });
            return null;
        }

    }
}
