package com.jmteam.igauntlet.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemWalkman extends Item {

    public ItemWalkman() {
        // TODO Add Item Property Override to make Casette Show
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        // TODO open GUI with container (First Make one)
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
