package com.jmteam.igauntlet.util.gauntlet;

import com.jmteam.igauntlet.common.init.InfinityNBT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;

public class GauntletHelper {

    public static GemHelper.StoneType getActiveStone(ItemStack stack) {
        CompoundNBT nbt = stack.getTag();

        if (nbt != null && nbt.contains(InfinityNBT.SELECTED_STONE)) {
            return GemHelper.StoneType.valueOf(nbt.getString(InfinityNBT.SELECTED_STONE));
        } else {
            if (nbt == null) {
                stack.setTag(new CompoundNBT());
                stack.getTag().putString(InfinityNBT.SELECTED_STONE, GemHelper.StoneType.NONE.name());
            }
        }

        return GemHelper.StoneType.NONE;
    }

    public static boolean invalidStone(PlayerEntity player, ItemStack stack) {

        if (getActiveStone(stack) == GemHelper.StoneType.NONE) {
            if (!player.world.isRemote) {
                player.sendStatusMessage(new TranslationTextComponent("msg.gauntlet.nostone"), true);
                return true;
            }
        }

        return false;
    }
}
