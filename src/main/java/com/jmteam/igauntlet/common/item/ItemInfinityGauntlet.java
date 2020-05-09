package com.jmteam.igauntlet.common.item;

import com.jmteam.igauntlet.client.gui.GuiGauntlet;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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

        if (handIn == Hand.OFF_HAND && worldIn.isRemote) {
            openStoneSelectionGUI();
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @OnlyIn(Dist.CLIENT)
    public void openStoneSelectionGUI() {
        Minecraft.getInstance().displayGuiScreen(new GuiGauntlet());
    }
}
