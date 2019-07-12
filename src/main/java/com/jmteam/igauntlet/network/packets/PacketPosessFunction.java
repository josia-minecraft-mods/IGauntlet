package com.jmteam.igauntlet.network.packets;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.function.gems.GemSoul;
import com.jmteam.igauntlet.common.init.InfinityItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static com.jmteam.igauntlet.common.init.InfinityNbtKeys.CURRENTSTONE;


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
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayerMP player = ctx.getServerHandler().player;
                ItemStack stack = player.getActiveItemStack();
                IInfinityCap cap = CapabilityInfinity.get(player);

                if(message.func.equals("special")) {
                    if (cap.isPosessing() && cap.getPosessedEntity() != null) {
                        GemSoul.processEnderman(cap.getPosessedEntity());
                        GemSoul.processCreeper(cap.getPosessedEntity(), player);
                    }
                }
            });
            return null;
        }
    }
}
