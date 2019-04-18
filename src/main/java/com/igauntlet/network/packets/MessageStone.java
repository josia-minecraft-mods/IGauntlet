package com.igauntlet.network.packets;

import com.igauntlet.common.items.InfinityItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class MessageStone implements IMessage {

    public static int stone;

    public MessageStone() {
    }

    public MessageStone(int stone) {
        this.stone = stone;
    }

    public void fromBytes(ByteBuf buf) {
        this.stone = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.stone);
    }

    public static class Handler implements IMessageHandler<MessageStone, IMessage> {

        @Override
        public IMessage onMessage(MessageStone message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayerMP player = ctx.getServerHandler().player;
                ItemStack stack = player.getActiveItemStack();

                if (stack.getItem() == InfinityItems.infinity_gauntlet) {
                    NBTTagCompound nbt = stack.getTagCompound();
                    nbt.setInteger("currentstone", stone);
                    stack.setTagCompound(nbt);
                }
            });
            return null;
        }
    }
}
