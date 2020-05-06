package com.jmteam.igauntlet.common.items.stones;

import com.jmteam.igauntlet.util.InfinityConfig;
import com.jmteam.igauntlet.util.helpers.PlayerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSpaceStone extends Item {

    public ItemSpaceStone() {
        setMaxStackSize(1);
        setMaxDamage(4500);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);


        EntityPlayer player = (EntityPlayer) entityIn;
        int maxtimeout = InfinityConfig.Gauntlet.SpaceStone.SpaceDriftTimeout;

        if (isSelected && !worldIn.isRemote) {

            if (stack.getTagCompound() == null) {
                stack.setTagCompound(new NBTTagCompound());
            } else {
                if (stack.getTagCompound().getLong("time") == 0 || !stack.getTagCompound().hasKey("time")) {
                    stack.getTagCompound().setLong("time", System.currentTimeMillis());
                    stack.getTagCompound().setBoolean("warned", false);
                }
            }

            if (stack.getTagCompound() != null) {
                NBTTagCompound nbtTagCompound = stack.getTagCompound();
                long time = nbtTagCompound.getLong("time");

                if (worldIn.getWorldTime() % 20 == 0 && time != 0) {

                    if (((System.currentTimeMillis() - time) / 1000L) > (maxtimeout / 4) && !nbtTagCompound.getBoolean("warned")) {
                        PlayerHelper.sendMessage(player, "stones.space.donthold", true);
                        nbtTagCompound.setBoolean("warned", true);
                    }

                    if (((System.currentTimeMillis() - time) / 1000L) >= maxtimeout) {
                        PlayerHelper.sendMessage(player, "stones.space.drifted", true);
                        stack.getTagCompound().setLong("time", System.currentTimeMillis());
                        nbtTagCompound.setBoolean("warned", false);

                        int max = InfinityConfig.Gauntlet.SpaceStone.MaximumDriftRange;
                        int min = InfinityConfig.Gauntlet.SpaceStone.MinimumDriftRange;
                        int random = (int) (Math.random() * max + min);
                        BlockPos pos1 = new BlockPos(itemRand.nextBoolean() ? random : -random + player.posX + itemRand.nextInt(50), random, (itemRand.nextBoolean() ? random : -random) + player.posZ + itemRand.nextInt(50));
                        PlayerHelper.teleportToPosition(player, worldIn.getTopSolidOrLiquidBlock(pos1));
                    }
                }
            }
        } else {
            if (stack.getTagCompound() != null) {
                if (stack.getTagCompound().getLong("time") != 0) {
                    stack.getTagCompound().setLong("time", 0);
                    stack.getTagCompound().setBoolean("warned", false);
                }
            }
        }
    }
}
