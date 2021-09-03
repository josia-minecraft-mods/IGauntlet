package com.jmteam.igauntlet.common.item.stones;

import com.jmteam.igauntlet.common.init.InfinityMessages;
import com.jmteam.igauntlet.common.init.InfinityNBT;
import com.jmteam.igauntlet.util.gauntlet.GemHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemSpaceStone extends ItemStoneBase {

    public ItemSpaceStone() {
        super();
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if (entityIn instanceof PlayerEntity) {
            if (isSelected && stack.getItem() == this) {

                PlayerEntity playerEntity = (PlayerEntity) entityIn;

                if (!stack.hasTag()) {
                    CompoundNBT compound = new CompoundNBT();
                    compound.putInt(InfinityNBT.TIMER, 0);
                    stack.setTag(compound);
                }

                if (!worldIn.isClientSide() && worldIn.getGameTime() % 20 == 0) {
                    int counter = stack.getTag().getInt(InfinityNBT.TIMER);

                    // TODO Config
                    if (counter < 15) {
                        if (counter == 7) {
                            playerEntity.displayClientMessage(InfinityMessages.getComponent(InfinityMessages.STONE_SPACE_NEAR_DRIFT), true);
                        }

                        stack.getTag().putInt(InfinityNBT.TIMER, counter + 1);
                    } else {
                        stack.getTag().putInt(InfinityNBT.TIMER, 0);
                        GemHelper.StoneType.SPACE.getGem().handleItemHoldingAction(playerEntity);
                    }
                }
            }
        }
    }
}
