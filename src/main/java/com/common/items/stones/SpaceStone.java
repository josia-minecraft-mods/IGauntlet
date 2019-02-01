package com.common.items.stones;

import com.Infinity;
import com.config.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import com.tabs.InfinityTabs;
import com.util.IHasModel;
import com.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class SpaceStone extends Item implements IHasModel {

    public SpaceStone(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);
        setMaxDamage(4500);

        ModItems.ITEMS.add(this);
    }


    int timeout = 0;

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);


        EntityPlayer player = (EntityPlayer) entityIn;

        int maxtimeout = 20 * 20;
        if (isSelected) {
            timeout++;
        }
        if (timeout > maxtimeout) {
            timeout = 0;
            int max = ModConfig.Gauntlet.MaximumTeleportRange;
            int min = ModConfig.Gauntlet.MinimumTeleportRange;
            int random = (int) (Math.random() * max + min);
            BlockPos pos1 = new BlockPos(random, random, random);
            BlockPos pos2 = worldIn.getTopSolidOrLiquidBlock(pos1);
            player.setLocationAndAngles(pos2.getX(), pos2.getY(), pos2.getZ(), 1, 1);
            player.sendStatusMessage(new TextComponentTranslation("stones.space.randomtp"), true);
        }

        if(timeout == maxtimeout / 2) {
            player.sendStatusMessage(new TextComponentTranslation("stones.space.donthold"), true);
        }
    }



    @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
