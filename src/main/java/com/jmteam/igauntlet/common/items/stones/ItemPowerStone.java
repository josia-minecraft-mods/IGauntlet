package com.jmteam.igauntlet.common.items.stones;

import com.jmteam.igauntlet.common.damage.IDamageSource;
import com.jmteam.igauntlet.common.damage.ISource;
import com.jmteam.igauntlet.tabs.InfinityTabs;
import com.jmteam.igauntlet.util.helpers.PlayerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Random;

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

        if (stack.getTagCompound() == null) {
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            stack.setTagCompound(nbtTagCompound);
            if(!nbtTagCompound.hasKey("checked"))
                stack.getTagCompound().setBoolean("checked", false);
        } else {
            if (isSelected) {
                if (!stack.getTagCompound().getBoolean("checked")) {
                    if (!(randomn == 3)) {
                        PlayerHelper.sendMessageClient(player, "stones.power.spaired", true);
                    } else {
                        player.attackEntityFrom(IDamageSource.POWER, player.getHealth());
                    }
                }
                stack.getTagCompound().setBoolean("checked", true);
            } else {
                stack.getTagCompound().setBoolean("checked", false);
            }

        }
    }
}