package com.jmteam.igauntlet.common.items.stones;

import com.jmteam.igauntlet.common.init.InfinityItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRealityStone extends Item {

    public ItemRealityStone() {
        setMaxStackSize(1);
        setMaxDamage(4500);
    }


    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);


        EntityPlayer player = (EntityPlayer) entityIn;

        if (player.isCreative()) return;
        player.capabilities.allowFlying = isSelected || player.getHeldItemOffhand().getItem() == InfinityItems.reality_stone;
        player.sendPlayerAbilities();
    }
}
