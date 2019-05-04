package com.jmteam.igauntlet.common.items.stones;

import com.jmteam.igauntlet.util.InfinityConfig;
import com.jmteam.igauntlet.network.NetworkHandler;
import com.jmteam.igauntlet.network.packets.MessageSpace;
import com.jmteam.igauntlet.tabs.InfinityTabs;
import com.jmteam.igauntlet.util.helpers.PlayerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSpaceStone extends Item {

    public ItemSpaceStone(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);
        setMaxDamage(4500);
    }


    int timeout = 0;

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);


        EntityPlayer player = (EntityPlayer) entityIn;
        int maxtimeout = InfinityConfig.Gauntlet.SpaceStone.SpaceDriftTimeout * 20;

        if (worldIn.isRemote) {
            if (isSelected) {
                timeout++;


                if (timeout == 50 % maxtimeout) {
                    PlayerHelper.sendMessageClient(player, "stones.space.donthold", true);
                }

                if (timeout == maxtimeout) {
                    PlayerHelper.sendMessageClient(player, "stones.space.drifted", true);
                }
            }

            if (timeout > maxtimeout) {
                timeout = 0;
                int max = InfinityConfig.Gauntlet.SpaceStone.MaximumDriftRange;
                int min = InfinityConfig.Gauntlet.SpaceStone.MinimumDriftRange;
                int random = (int) (Math.random() * max + min);
                BlockPos pos1 = new BlockPos(random, random, random);
                BlockPos pos2 = worldIn.getTopSolidOrLiquidBlock(pos1);
                NetworkHandler.NETWORK.sendToServer(new MessageSpace(pos2, player.getEntityId()));
            }
        }
    }
}
