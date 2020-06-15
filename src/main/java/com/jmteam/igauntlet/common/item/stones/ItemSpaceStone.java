package com.jmteam.igauntlet.common.item.stones;

import com.jmteam.igauntlet.util.gauntlet.GemHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class ItemSpaceStone extends ItemStoneBase {

    public ItemSpaceStone() {
        super();
    }


    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if (entityIn instanceof PlayerEntity) {
            if (isSelected && stack.getItem() == this) {
                if (!stack.hasTag()) {
                    CompoundNBT compound = new CompoundNBT();
                    compound.putInt("timer", 0);
                    stack.setTag(compound);
                } else {
                    if (!worldIn.isRemote && worldIn.getGameTime() % 20 == 0) {
                        int counter = stack.getTag().getInt("timer");

                        // TODO Config
                        if (counter < 15) {
                            stack.getTag().putInt("timer", counter + 1);
                        } else {
                            stack.getTag().putInt("timer", 0);
                            GemHelper.StoneType.SPACE.getGem().handleItemAction((PlayerEntity) entityIn);
                        }
                    }
                }
            }
        }
    }
}
