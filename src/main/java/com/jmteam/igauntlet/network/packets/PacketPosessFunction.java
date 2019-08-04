package com.jmteam.igauntlet.network.packets;

import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.function.gems.GemSoul;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class PacketPosessFunction implements IMessage {

    private String func;

    public PacketPosessFunction() {
    }

    public PacketPosessFunction(String func) {
        this.func = func;
    }

    public void fromBytes(ByteBuf buf) {
        func = ByteBufUtils.readUTF8String(buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, func);
    }

    public static class Handler implements IMessageHandler<PacketPosessFunction, IMessage> {

        @Override
        public IMessage onMessage(PacketPosessFunction message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    EntityPlayerMP player = ctx.getServerHandler().player;
                    IInfinityCap cap = CapabilityInfinity.get(player);
                    String f = message.func;

                    if(f.equalsIgnoreCase("special"))
                        GemSoul.useSpecialFunction(cap.getPosessedEntity(), player);

                    GemSoul.processGeneral(cap.getPosessedEntity(), player, f);
                }
            });
            return null;
        }
    }
}
