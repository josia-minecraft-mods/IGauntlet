package com.igauntlet.common.items.stones;

import com.igauntlet.Infinity;
import com.igauntlet.init.InfinityItems;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.helpers.IHasModel;
import com.igauntlet.util.helpers.PlayerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.concurrent.ThreadLocalRandom;

public class ItemPowerStone extends Item implements IHasModel {

    public ItemPowerStone(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);
        setMaxDamage(4500);

        InfinityItems.ITEMS.add(this);
    }

@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
    super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);


    EntityPlayer player = (EntityPlayer) entityIn;
    int min = 1;
    int max = 7;
    int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
    if randomNum == 1, 2, 3, 4, 5, 6 {
        player.setDead();
    }
    else {
        PlayerHelper.sendMessageClient(player, "stones.power.spaired", true);
    }
}
             
     @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
