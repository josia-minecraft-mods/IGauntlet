package com.igauntlet.common.items.stones;

import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.helpers.PlayerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ItemPowerStone extends Item {

    public ItemPowerStone(String name) {
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
        Random random = new Random();
        int randomn = random.nextInt(8);
        if (randomn == 3) {
            PlayerHelper.sendMessageClient(player, "stones.power.spaired", true);
        }
        else {
            player.setDead();
        }
    }
}