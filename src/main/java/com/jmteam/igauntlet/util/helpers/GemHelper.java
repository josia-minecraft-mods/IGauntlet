package com.jmteam.igauntlet.util.helpers;

import com.jmteam.igauntlet.network.NetworkHandler;
import com.jmteam.igauntlet.network.packets.MessageStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GemHelper {

    public static void setGem(int stone) {
        NetworkHandler.NETWORK.sendToServer(new MessageStone(stone));
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


    //Doesn't do anything for now , going to use a ArrayList.
    // Will add 1-6 numbers in array and check for them if all are present.
    public static boolean AllStones(EntityPlayer player) {
        ItemStack stack = player.getActiveItemStack();
        NBTTagCompound nbt = stack.getTagCompound();
        return true;
    }


}
