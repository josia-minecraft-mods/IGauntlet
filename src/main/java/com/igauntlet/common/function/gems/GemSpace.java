package com.igauntlet.common.function.gems;

import com.igauntlet.client.gui.GuiSpace;
import com.igauntlet.init.InfinityItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GemSpace {

    @SideOnly(Side.CLIENT)
    public static void OpenSpaceGui(EntityPlayer playerIn) {
        if (playerIn.getHeldItemOffhand().getItem() != InfinityItems.INFINITY_GAUNTLET)
            Minecraft.getMinecraft().displayGuiScreen(new GuiSpace());
    }
}
