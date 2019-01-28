package com.common.items.gems;

import com.client.gui.GuiSpace;
import com.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GemSpace {

    @SideOnly(Side.CLIENT)
    public static void OpenSpaceGui(EntityPlayer playerIn) {
        if(playerIn.getHeldItemOffhand().getItem() != ModItems.INFINITY_GAUNTLET)
        Minecraft.getMinecraft().displayGuiScreen(new GuiSpace());
    }
}
