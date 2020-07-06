package com.jmteam.igauntlet.common.item.stones;

import com.jmteam.igauntlet.common.item.ItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTimeStone extends ItemStoneBase {

    public ItemTimeStone() {
        super();
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(isSelected) {
            worldIn.setDayTime(worldIn.getDayTime() + 10);
        }
    }
}
