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

    GuiButton button1; GuiButton button2; GuiButton button3; GuiButton button4; GuiButton button5; GuiButton button6;

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
       // buttonList.add(button2 = new GuiButton(1, 400, 150,50,60, ""));
       // buttonList.add(button3 = new GuiButton(2, 400, 150,50,60, ""));
       // buttonList.add(button4 = new GuiButton(3, 400, 150,50,60, ""));
       // buttonList.add(button5 = new GuiButton(4, 400, 150,50,60, ""));
       // buttonList.add(button6 = new GuiButton(5, 400, 150,50,60, ""));


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
               STONE = 1;

            case 1:
                STONE = 2;

            case 2:
                STONE = 3;

            case 3:
                STONE = 4;

            case 4:
                STONE = 5;

            case 5:
                STONE = 6;
        }

        Minecraft.getMinecraft().displayGuiScreen(null);

        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
