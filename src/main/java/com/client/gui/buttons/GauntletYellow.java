package com.client.gui.buttons;

import com.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GauntletYellow extends GuiButton {

    final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/gauntlet_yellow.png");

    int buttonWidth = 70;
    int buttonHeight = 80;
    int u = 175;
    int v = 1;

    public GauntletYellow(int buttonId, int x, int y) {
        super(buttonId, x, y, 70, 80, "");
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (visible) {
            mc.renderEngine.bindTexture(texture);
            if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
                hovered = true;
            } else {
                hovered = false;
            }
            if (hovered) {
                v = 18;
            } else {
                v = 1;
            }
            drawTexturedModalRect(x, y, u, v, width, height);
        }
    }
}
