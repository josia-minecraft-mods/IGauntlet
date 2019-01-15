package com.client.gui;

import com.Main;
import com.common.items.InfinityGauntlet;
import com.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

import java.awt.*;
import java.io.IOException;

public class GuiGauntlet extends GuiScreen {


    static final int GUI_WIDTH = 256;
    static final int GUI_HEIGHT = 256;

    GuiButton button1;

    final int BUTTON1 = 0;

    public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/gui_gauntlet.png");

    public GuiGauntlet() {

    }


      public class ButtonStones extends GuiButton {

          int BUTTON_WIDTH = 75;
          int BUTTON_HEIGHT = 32;

          public ButtonStones(int buttonId, int x, int y, String buttonText) {
              super(buttonId, x, y, buttonText);
          }

          @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {

        }

    }

    @Override
    public void initGui()
    {
        buttonList.clear();

        buttonList.add(button1 = new GuiButton(0, 400, 150,50,60, ""));


        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(TEXTURE);
        int x = (width - GUI_WIDTH) / 2;
        int y = (height - GUI_HEIGHT) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, GUI_WIDTH, GUI_HEIGHT);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public static int STONE = 0;


    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        switch (button.id) {

            case 0:
               STONE = 3;
        }

        Minecraft.getMinecraft().displayGuiScreen(null);

        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
