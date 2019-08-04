package com.jmteam.igauntlet.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemWalkman extends Item {

    public ItemWalkman() {}

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
     // TODO open GUI with container
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
