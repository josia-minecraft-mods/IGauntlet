package com.jmteam.igauntlet.util.helpers;

import com.jmteam.igauntlet.network.NetworkHandler;
import com.jmteam.igauntlet.network.packets.PacketStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import static com.jmteam.igauntlet.common.init.InfinityNbtKeys.CURRENTSTONE;

public class GemHelper {

    public static void setGem(int stone) {
        NetworkHandler.NETWORK.sendToServer(new PacketStone(stone));
    }

    public static int ActiveGem(EntityPlayer player) {
        ItemStack stack = player.getActiveItemStack();
        NBTTagCompound nbt = stack.getTagCompound();
        return nbt != null ? nbt.getInteger(CURRENTSTONE) : 0;
    }

    public static int ActiveGem(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        return nbt != null ? nbt.getInteger(CURRENTSTONE) : 0;
    }
}
