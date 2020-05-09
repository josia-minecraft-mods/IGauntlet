package com.jmteam.igauntlet.common.item;

import com.jmteam.igauntlet.client.gui.GuiGauntlet;
import com.jmteam.igauntlet.common.init.InfinityNBT;
import com.jmteam.igauntlet.util.gauntlet.GauntletHelper;
import com.jmteam.igauntlet.util.gauntlet.GemHelper.StoneType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemInfinityGauntlet extends ItemBase {

    public ItemInfinityGauntlet() {
        super(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        boolean main_hand = handIn == Hand.MAIN_HAND;
        ItemStack stack = playerIn.getHeldItem(handIn);

        if (!main_hand && worldIn.isRemote) {
            openStoneSelectionGUI();
        }else if(!worldIn.isRemote && main_hand){

            switch (GauntletHelper.getActiveStone(stack)) {
                // TODO Stone functionality
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @OnlyIn(Dist.CLIENT)
    public void openStoneSelectionGUI() {
        Minecraft.getInstance().displayGuiScreen(new GuiGauntlet());
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);


        if(stack.getTag() == null && stack.getItem() == this) {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString(InfinityNBT.SELECTED_STONE, StoneType.NONE.name());
            stack.setTag(new CompoundNBT());
        }
    }

}
