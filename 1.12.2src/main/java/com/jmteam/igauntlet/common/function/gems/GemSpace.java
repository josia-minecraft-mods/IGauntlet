package com.jmteam.igauntlet.common.function.gems;

import com.jmteam.igauntlet.client.gui.GuiSpace;
import com.jmteam.igauntlet.common.init.InfinityItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GemSpace {

    @SideOnly(Side.CLIENT)
    public static void OpenSpaceGui(EntityPlayer playerIn) {
        if (playerIn.getHeldItemOffhand().getItem() != InfinityItems.infinity_gauntlet)
            Minecraft.getMinecraft().displayGuiScreen(new GuiSpace());
    }
}
