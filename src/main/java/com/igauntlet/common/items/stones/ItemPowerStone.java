package com.igauntlet.common.items.stones;

import com.igauntlet.common.damage.ISource;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.helpers.PlayerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

        // TODO Make this cleaner!

        if(stack.getTagCompound() == null) {
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            stack.setTagCompound(nbtTagCompound);
        }

        EntityPlayer player = (EntityPlayer) entityIn;
        Random random = new Random();
        int randomn = random.nextInt(8);
        if (isSelected && stack.getTagCompound() != null) {
            if (!(randomn == 3) && !stack.getTagCompound().getBoolean("checked")) {
                PlayerHelper.sendMessageClient(player, "stones.power.spaired", true);
            } else {
                player.attackEntityFrom(ISource.MAGIC, player.getHealth());
            }
            stack.getTagCompound().setBoolean("checked", true);
        } else {
            stack.getTagCompound().setBoolean("checked", false);
        }
    }
}