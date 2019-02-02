package com.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSpaceDrift implements IMessage {


    public BlockPos pos;
    public int id;

    public MessageSpaceDrift() {
    }

    public MessageSpaceDrift(BlockPos pos, int id) {
        this.pos = pos;
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(this.id);
    }

    public static class Handler implements IMessageHandler<MessageSpaceDrift, IMessage> {

        @Override
        public IMessage onMessage(MessageSpaceDrift message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                WorldServer world = ctx.getServerHandler().player.getServerWorld();
                BlockPos pos = world.getTopSolidOrLiquidBlock(message.pos);
                ctx.getServerHandler().player.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
            });
            return null;
        }

    }

}
