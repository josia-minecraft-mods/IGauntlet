package com.client.gui;

import com.config.ModConfig;
import com.network.packets.MessageNotAdded;
import com.network.packets.MessageStone;
import com.network.NetworkHandler;
import com.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class GuiGauntlet extends GuiScreen {


    static final int GUI_WIDTH = 256;
    static final int GUI_HEIGHT = 256;



    GuiButton button1;
    GuiButton button2;
    GuiButton button3;
    GuiButton button4;
    GuiButton button5;
    GuiButton button6;



    public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/gui_gauntlet.png");

    public GuiGauntlet() {}


    public class ButtonStones extends GuiButton {

        int BUTTON_WIDTH = 50;
        int BUTTON_HEIGHT = 60;

        public ButtonStones(int buttonId, int x, int y, String buttonText) {
            super(buttonId, x, y, buttonText);
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            this.width = BUTTON_WIDTH;
            this.height = BUTTON_HEIGHT;
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);

            int j = 14737632;

            if (packedFGColour != 0) {
                j = packedFGColour;
            } else if (!this.enabled) {
                j = 10526880;
            } else if (this.hovered) {
                j = 16777120;
            }
            this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
        }

    }

    public static int REALITY = 1;
    public static int SPACE = 2;
    public static int SOUL = 3;
    public static int TIME = 4;
    public static int POWER = 5;
    public static int MIND = 6;

    @Override
    public void initGui() {
        buttonList.clear();

        if(ModConfig.Gauntlet.GUITEXT) {
            buttonList.add(button1 = new ButtonStones(TIME, (width / 2) - 80, (height / 2) - 105, " Time"));
            buttonList.add(button2 = new ButtonStones(SOUL, (width / 2) + 30, (height / 2) - 105, "Soul"));
            buttonList.add(button3 = new ButtonStones(POWER, (width / 2) - 100, (height / 2) - 10, "Power"));
            buttonList.add(button4 = new ButtonStones(SPACE, (width / 2) - 25, (height / 2) + 50, "Space"));
            buttonList.add(button5 = new ButtonStones(REALITY, (width / 2) + 50, (height / 2) - 10, " Reality"));
            buttonList.add(button6 = new ButtonStones(MIND, (width / 2) - 25, (height / 2) - 40, " Mind"));
        }else{
            buttonList.add(button1 = new ButtonStones(TIME, (width / 2) - 80, (height / 2) - 105, ""));
            buttonList.add(button2 = new ButtonStones(SOUL, (width / 2) + 30, (height / 2) - 105, ""));
            buttonList.add(button3 = new ButtonStones(REALITY, (width / 2) - 100, (height / 2) - 10, ""));
            buttonList.add(button4 = new ButtonStones(SPACE, (width / 2) - 25, (height / 2) + 50, ""));
            buttonList.add(button5 = new ButtonStones(POWER, (width / 2) + 50, (height / 2) - 10, ""));
            buttonList.add(button6 = new ButtonStones(MIND, (width / 2) - 25, (height / 2) - 40, ""));
        }
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

            case 1:
                STONE = REALITY;
                break;

            case 2:
                STONE = SPACE;
                break;

            case 3:
                STONE = SOUL;
                break;

            case 4:
                STONE = TIME;
                break;

            case 5:
                STONE = POWER;
                break;

            case 6:
                STONE = MIND;
                break;
        }

        if(button.id == REALITY || button.id == SPACE || button.id == SOUL || button.id == MIND || button.id == TIME) {
            NetworkHandler.NETWORK.sendToServer(new MessageNotAdded(STONE, button.id));
        }

        NetworkHandler.NETWORK.sendToServer(new MessageStone(STONE));
        Minecraft.getMinecraft().displayGuiScreen(null);
        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
