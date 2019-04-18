package com.igauntlet.common.items.stones;

import com.igauntlet.common.items.InfinityItems;
import com.igauntlet.tabs.InfinityTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRealityStone extends Item {

    public ItemRealityStone(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);
        setMaxDamage(4500);
    }


    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);


        EntityPlayer player = (EntityPlayer) entityIn;

        if(player.isCreative()) return;
        if (isSelected || player.getHeldItemOffhand().getItem() == InfinityItems.reality_stone) {
            player.capabilities.allowFlying = true;
        }else{
            player.capabilities.allowFlying = false;
        }
        player.sendPlayerAbilities();
    }
}
