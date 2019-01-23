package com.client.gui;

import com.config.ModConfig;
import com.network.MessageStone;
import com.network.NetworkHandler;
import com.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
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
    private EntityPlayer player = Minecraft.getMinecraft().player;


    public GuiGauntlet() {

    }


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

    @Override
    public void initGui() {
        buttonList.clear();

        if(ModConfig.Gauntlet.GUITEXT) {
            buttonList.add(button1 = new ButtonStones(0, (width / 2) - 80, (height / 2) - 105, " Mind"));
            buttonList.add(button2 = new ButtonStones(1, (width / 2) + 30, (height / 2) - 105, "Time"));
            buttonList.add(button3 = new ButtonStones(2, (width / 2) - 100, (height / 2) - 10, "Soul"));
            buttonList.add(button4 = new ButtonStones(3, (width / 2) - 25, (height / 2) + 50, "Space"));
            buttonList.add(button5 = new ButtonStones(4, (width / 2) + 50, (height / 2) - 10, "Reality"));
            buttonList.add(button6 = new ButtonStones(5, (width / 2) - 25, (height / 2) - 40, " Power"));
        }else{
            buttonList.add(button1 = new ButtonStones(0, (width / 2) - 80, (height / 2) - 105, ""));
            buttonList.add(button2 = new ButtonStones(1, (width / 2) + 30, (height / 2) - 105, ""));
            buttonList.add(button3 = new ButtonStones(2, (width / 2) - 100, (height / 2) - 10, ""));
            buttonList.add(button4 = new ButtonStones(3, (width / 2) - 25, (height / 2) + 50, ""));
            buttonList.add(button5 = new ButtonStones(4, (width / 2) + 50, (height / 2) - 10, ""));
            buttonList.add(button6 = new ButtonStones(5, (width / 2) - 25, (height / 2) - 40, ""));
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

        String NO = "This function isn't  in the game yet F: " + button.id;

        switch (button.id) {

            case 0:
                STONE = 1;
                sendChatMessage(NO);
                break;

            case 1:
                STONE = 2;
                sendChatMessage(NO);
                break;

            case 2:
                STONE = 3;
                sendChatMessage(NO);
                break;

            case 3:
                STONE = 4;
                sendChatMessage(NO);
                break;

            case 4:
                STONE = 5;
                sendChatMessage(NO);
                break;

            case 5:
                STONE = 6;
                break;
        }
        Minecraft.getMinecraft().displayGuiScreen(null);

        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
