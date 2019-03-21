package com.igauntlet.common.items.stones;

import com.igauntlet.Infinity;
import com.igauntlet.init.InfinityItems;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.helpers.IHasModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RealityStone extends Item implements IHasModel {

    public RealityStone(String name) {
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

        if (isSelected) {
            player.capabilities.allowFlying = true;
        }else{
            player.capabilities.allowFlying = false;
        }
        player.sendPlayerAbilities();
    }

    @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
