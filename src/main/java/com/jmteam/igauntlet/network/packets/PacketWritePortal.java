package com.jmteam.igauntlet.network.packets;

import com.jmteam.igauntlet.common.entity.EntityPortal;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketWritePortal implements IMessage {

    public static int X;
    public static int Y;
    public static int Z;

    public PacketWritePortal() {
    }

    public PacketWritePortal(int x, int y, int z) {
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

    public static class Handler implements IMessageHandler<PacketWritePortal, IMessage> {

        @Override
        public IMessage onMessage(PacketWritePortal message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayer player = ctx.getServerHandler().player;
                EntityPortal portal = new EntityPortal(player.world, X, Y, Z, player.rotationYaw, true);
                portal.setNoGravity(true);
                portal.setEntityInvulnerable(true);
                portal.setPosition(player.posX + 0.5, player.posY, player.posZ + 0.5);
                player.world.spawnEntity(portal);
            });
            return null;
        }
    }
}
