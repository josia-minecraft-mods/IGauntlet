package com.jmteam.igauntlet.util.gauntlet;

import com.jmteam.igauntlet.common.init.InfinityMessages;
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

        if (nbt == null) {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.putString(InfinityNBT.SELECTED_STONE, GemHelper.StoneType.NONE.name());
            stack.setTag(compoundNBT);
            nbt = stack.getTag();
        }

        return GemHelper.StoneType.valueOf(nbt.getString(InfinityNBT.SELECTED_STONE));
    }

    public static boolean invalidStone(PlayerEntity player, ItemStack stack) {

        if (!player.level.isClientSide()) {
            if (getActiveStone(stack) == GemHelper.StoneType.NONE) {
                player.displayClientMessage(InfinityMessages.getComponent(InfinityMessages.STONE_NOT_SELECTED), true);
                return true;
            }
        }

        return false;
    }

    public static boolean filterSnap(LivingEntity entity, boolean inSnap) {

        // inSnap = During snap, to check if they should be removed ( entity.remove() )

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
