package com.jmteam.igauntlet.util.helpers;

import com.jmteam.igauntlet.network.NetworkHandler;
import com.jmteam.igauntlet.network.packets.PacketStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.jmteam.igauntlet.common.init.InfinityNbtKeys.CURRENTSTONE;

public class GemHelper {

    @SideOnly(Side.CLIENT)
    public static void setGem(StoneType stone) {
        NetworkHandler.NETWORK.sendToServer(new PacketStone(stone));
    }

    public static StoneType ActiveGem(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        NBTTagCompound nbt = stack.getTagCompound();

        return nbt != null ? StoneType.valueOf(nbt.getString(CURRENTSTONE)) : StoneType.NONE;
    }

    public static StoneType ActiveGem(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();

        return nbt != null ? StoneType.valueOf(nbt.getString(CURRENTSTONE)) : StoneType.NONE;
    }

    public enum StoneType {
        NONE, MIND, REALITY, TIME, SPACE, POWER, SOUL
    }
}
