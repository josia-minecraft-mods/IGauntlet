package com.jmteam.igauntlet.common.item;

import com.jmteam.igauntlet.client.gui.GuiGauntlet;
import com.jmteam.igauntlet.common.init.InfinityGroups;
import com.jmteam.igauntlet.common.init.InfinityNBT;
import com.jmteam.igauntlet.util.gauntlet.GauntletHelper;
import com.jmteam.igauntlet.util.gauntlet.GemHelper.StoneType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemInfinityGauntlet extends ItemBase {

    public ItemInfinityGauntlet() {
        super(new Item.Properties().group(InfinityGroups.INFINITY));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        boolean main_hand = handIn == Hand.MAIN_HAND;
        ItemStack stack = playerIn.getHeldItem(handIn);

        if (!main_hand && worldIn.isRemote) {
            openStoneSelectionGUI();
        } else if (!worldIn.isRemote && main_hand) {

            if (GauntletHelper.invalidStone(playerIn, stack)) {
                return ActionResult.resultFail(stack);
            }

            GauntletHelper.getActiveStone(stack).getGem().handleRightClick(playerIn);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        Hand hand = context.getHand();
        boolean main_hand = hand == Hand.MAIN_HAND;
        PlayerEntity playerIn = context.getPlayer();
        ItemStack stack = playerIn.getHeldItem(hand);

        if (!main_hand && context.getWorld().isRemote) {
            openStoneSelectionGUI();
        } else if (!context.getWorld().isRemote && main_hand) {
            if (GauntletHelper.invalidStone(playerIn, stack)) {
                return ActionResultType.FAIL;
            }

            GauntletHelper.getActiveStone(stack).getGem().handleUsedClick(context);
        }

        return super.onItemUse(context);
    }

    @OnlyIn(Dist.CLIENT)
    public void openStoneSelectionGUI() {
        Minecraft.getInstance().displayGuiScreen(new GuiGauntlet());
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if (stack.getTag() == null && stack.getItem() == this) {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString(InfinityNBT.SELECTED_STONE, StoneType.NONE.name());
            stack.setTag(nbt);
        }
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return new StringTextComponent(TextFormatting.GOLD + "" + TextFormatting.BOLD + I18n.format("item.igauntlet.infinity_gauntlet"));
    }
}
