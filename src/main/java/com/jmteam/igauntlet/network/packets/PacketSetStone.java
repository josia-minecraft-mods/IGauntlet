package com.jmteam.igauntlet.network.packets;

import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.common.init.InfinityNBT;
import com.jmteam.igauntlet.util.gauntlet.GemHelper.StoneType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSetStone {

    public StoneType type;

    public PacketSetStone(StoneType type) {
        super();
        this.type = type;
    }

    public static void encode(PacketSetStone packet, PacketBuffer buf) {
        buf.writeString(packet.type.name());
    }

    public static PacketSetStone decode(PacketBuffer buf) {
        return new PacketSetStone(StoneType.valueOf(buf.readString()));
    }


    public static class Handler {
        public static void handle(PacketSetStone packet, Supplier<NetworkEvent.Context> ctx) {

            ctx.get().enqueueWork(() -> {

                ServerPlayerEntity player = ctx.get().getSender();

                if (player != null) {
                    ItemStack stack = player.getHeldItem(Hand.OFF_HAND);
                    if (stack.getItem() == InfinityItems.infinity_gauntlet) {

                        if (stack.getTag() == null) stack.setTag(new CompoundNBT());

                        if (stack.getTag() != null) {
                            stack.getTag().putString(InfinityNBT.SELECTED_STONE, packet.type.name());
                        }
                    }
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
