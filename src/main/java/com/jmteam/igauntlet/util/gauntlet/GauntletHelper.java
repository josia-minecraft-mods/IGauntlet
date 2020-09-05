package com.jmteam.igauntlet.util.gauntlet;

import com.jmteam.igauntlet.common.init.InfinityNBT;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

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

    public static boolean filterSnap(LivingEntity entity, boolean inSnap) {

        // global boolean value is used for checking if inside snap or not

        if (entity instanceof PlayerEntity && inSnap) {
            return false;
        }

        return !(entity instanceof ArmorStandEntity);
    }

    public static void filterSnapList(PlayerEntity playerEntity, List<LivingEntity> livingEntities) {
        List<LivingEntity> removeList = new ArrayList<>();

        for (int x = 0; x < livingEntities.size(); x++) {
            LivingEntity e = livingEntities.get(x);

            // If it's not inside snap, it'll only clear the unwanted entities
            if (!GauntletHelper.filterSnap(e, false)) {
                removeList.add(e);
            }
        }
        removeList.add(playerEntity);
        livingEntities.removeAll(removeList);
    }
}
