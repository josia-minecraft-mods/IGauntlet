package com.network;

import com.client.gui.GuiGauntlet;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageStone implements IMessage {

    private EntityPlayer player;

    public MessageStone(){

    }

    public MessageStone(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }


    public static class Handler implements IMessageHandler<MessageStone, IMessage> {

        public Handler() {
        }

        @Override
        public IMessage onMessage(MessageStone message, MessageContext ctx) {
            return null;
        }
    }
}
