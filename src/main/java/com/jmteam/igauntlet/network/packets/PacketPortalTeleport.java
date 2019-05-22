package com.jmteam.igauntlet.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketPortalTeleport implements IMessage {

    public BlockPos pos;

    public PacketPortalTeleport() {
    }

    public PacketPortalTeleport(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
    }

    public static class Handler implements IMessageHandler<PacketPortalTeleport, IMessage> {

        @Override
        public IMessage onMessage(PacketPortalTeleport message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                WorldServer world = ctx.getServerHandler().player.getServerWorld();
                BlockPos pos = world.getTopSolidOrLiquidBlock(message.pos);
                ctx.getServerHandler().player.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
            });
            return null;
        }

    }

}

