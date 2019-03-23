package com.igauntlet.network.packets;

import com.igauntlet.common.entity.EntityPortal;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageWritePortal implements IMessage {

    public static int X;
    public static int Y;
    public static int Z;

    public MessageWritePortal() {
    }

    public MessageWritePortal(int x, int y, int z) {
        this.X = x;
        this.Y = y;
        this.Z = z;
    }

    public void fromBytes(ByteBuf buf) {
        this.X = buf.readInt();
        this.Y = buf.readInt();
        this.Z = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.X);
        buf.writeInt(this.Y);
        buf.writeInt(this.Z);
    }

    public static class Handler implements IMessageHandler<MessageWritePortal, IMessage> {

        @Override
        public IMessage onMessage(MessageWritePortal message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayer player = ctx.getServerHandler().player;
                EntityPortal portal = new EntityPortal(player.world);
                portal.getEntityData().setInteger("x", X);
                portal.getEntityData().setInteger("y", Y);
                portal.getEntityData().setInteger("z", Z);
                portal.getEntityData().setBoolean("isinit", true);
                portal.setNoGravity(true);
                portal.setNoAI(true);
                portal.setEntityInvulnerable(true);
                portal.setPosition(player.posX + 0.5, player.posY, player.posZ + 0.5);
                player.world.spawnEntity(portal);
            });
            return null;
        }
    }
}
