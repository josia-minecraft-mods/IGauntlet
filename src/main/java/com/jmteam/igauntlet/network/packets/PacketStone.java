package com.jmteam.igauntlet.network.packets;

import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.util.helpers.GemHelper.StoneType;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static com.jmteam.igauntlet.common.init.InfinityNbtKeys.CURRENTSTONE;


public class PacketStone implements IMessage {

    public StoneType stone;

    public PacketStone() {
    }

    public PacketStone(StoneType stone) {
        this.stone = stone;
    }

    public void fromBytes(ByteBuf buf) {
        this.stone = StoneType.valueOf(ByteBufUtils.readUTF8String(buf));
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, stone.name());
    }

    public static class Handler implements IMessageHandler<PacketStone, IMessage> {

        @Override
        public IMessage onMessage(PacketStone message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayerMP player = ctx.getServerHandler().player;
                ItemStack stack = player.getActiveItemStack();

                if (stack.getItem() == InfinityItems.infinity_gauntlet) {
                    if (stack.getTagCompound() == null) {
                        NBTTagCompound nbt = new NBTTagCompound();
                        nbt.setString(CURRENTSTONE, message.stone.name());
                        stack.setTagCompound(nbt);
                    } else {
                        stack.getTagCompound().setString(CURRENTSTONE, message.stone.name());
                    }
                }
            });
            return null;
        }
    }
}
