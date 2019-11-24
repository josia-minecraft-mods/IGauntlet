package com.jmteam.igauntlet.common.items.stones;

import com.jmteam.igauntlet.common.damage.IDamageSource;
import com.jmteam.igauntlet.util.InfinityConfig;
import com.jmteam.igauntlet.util.helpers.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTimeStone extends Item {
    private int time = 0;

    public ItemTimeStone() {
        setMaxStackSize(1);
        setMaxDamage(4500);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);

        if (isSelected && InfinityConfig.AllowedGems.TimeStone) {
            worldIn.getWorldInfo().setWorldTime(worldIn.getWorldTime() + InfinityConfig.Gauntlet.TimeStone.TimeSpeed);

            time++;

            if (time >= 200) {
                EntityHelper.AttackBySource(entityIn, IDamageSource.HOLDING, 0.001F);
                time = 0;
            }
        }
    }
}
