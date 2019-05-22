package com.jmteam.igauntlet.util.helpers;

import com.jmteam.igauntlet.network.NetworkHandler;
import com.jmteam.igauntlet.network.packets.PacketStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GemHelper {

    public static void setGem(int stone) {
        NetworkHandler.NETWORK.sendToServer(new PacketStone(stone));
    }

    public static int ActiveGem(EntityPlayer player) {
        ItemStack stack = player.getActiveItemStack();
        NBTTagCompound nbt = stack.getTagCompound();
        return nbt.getInteger("currentstone");
    }

    public static int ActiveGem(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        return nbt.getInteger("currentstone");
    }
}
