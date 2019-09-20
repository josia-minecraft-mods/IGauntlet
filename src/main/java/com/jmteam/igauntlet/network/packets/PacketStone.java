package com.jmteam.igauntlet.network.packets;

import com.jmteam.igauntlet.common.init.InfinityItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static com.jmteam.igauntlet.common.init.InfinityNbtKeys.CURRENTSTONE;


public class PacketStone implements IMessage {

    public static int stone;

    public PacketStone() {
    }

    public PacketStone(int stone) {
        this.stone = stone;
    }

    public void fromBytes(ByteBuf buf) {
        this.stone = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.stone);
    }

    public static class Handler implements IMessageHandler<PacketStone, IMessage> {

        @Override
        public IMessage onMessage(PacketStone message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayerMP player = ctx.getServerHandler().player;
                ItemStack stack = player.getActiveItemStack();

                if (stack.getItem() == InfinityItems.infinity_gauntlet) {
                    if(stack.getTagCompound() == null) {
                        NBTTagCompound nbt = new NBTTagCompound();
                        nbt.setInteger(CURRENTSTONE, stone);
                        stack.setTagCompound(nbt);
                    }else{
                        stack.getTagCompound().setInteger(CURRENTSTONE, stone);
                    }
                }
            });
            return null;
        }
    }
}
