package com.jmteam.igauntlet.util.gauntlet;

import com.jmteam.igauntlet.common.init.InfinityNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class GauntletHelper {

    public static GemHelper.StoneType getActiveStone(ItemStack stack) {
        CompoundNBT nbt = stack.getTag();

        if(nbt != null && nbt.contains(InfinityNBT.SELECTED_STONE)) {
            return GemHelper.StoneType.valueOf(nbt.getString(InfinityNBT.SELECTED_STONE));
        }else{
            if(nbt == null) {
                stack.setTag(new CompoundNBT());
                stack.getTag().putString(InfinityNBT.SELECTED_STONE, GemHelper.StoneType.NONE.name());
            }
        }

        return GemHelper.StoneType.NONE;
    }
}
