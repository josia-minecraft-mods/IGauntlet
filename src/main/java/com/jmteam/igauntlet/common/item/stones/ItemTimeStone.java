package com.jmteam.igauntlet.common.item.stones;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ItemTimeStone extends ItemStoneBase {

    public ItemTimeStone() {
        super();
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (isSelected && !worldIn.isClientSide() &&  worldIn.getServer().getAllLevels() != null) {
            for (ServerWorld serverworld : worldIn.getServer().getAllLevels()) {
                    serverworld.setDayTime(serverworld.getDayTime() + 50);
            }
        }
    }
}
