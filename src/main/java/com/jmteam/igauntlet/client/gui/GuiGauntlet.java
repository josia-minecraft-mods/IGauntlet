package com.jmteam.igauntlet.client.gui;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.util.InfinityConfig;
import com.jmteam.igauntlet.util.helpers.GemHelper;
import com.jmteam.igauntlet.util.helpers.GemHelper.StoneType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class GuiGauntlet extends GuiScreen {


    public static final int GUI_WIDTH = 256;
    public static final int GUI_HEIGHT = 256;
    public static final ResourceLocation TEXTURE = new ResourceLocation(Infinity.MODID, "textures/gui/gui_gauntlet.png");

    private ButtonStones time;
    private ButtonStones soul;
    private ButtonStones power;
    private ButtonStones space;
    private ButtonStones reality;
    private ButtonStones mind;


    public GuiGauntlet() {
    }

    @Override
    public void initGui() {
        buttonList.clear();
        boolean b = InfinityConfig.Gauntlet.GuiText;
        int id = -1;

        buttonList.add(time = new ButtonStones(id++, (width / 2) - 80, (height / 2) - 105, b ? " Time" : "", StoneType.TIME));
        buttonList.add(soul = new ButtonStones(id++, (width / 2) + 30, (height / 2) - 105, b ? "Soul" : "", StoneType.SOUL));
        buttonList.add(power = new ButtonStones(id++, (width / 2) - 100, (height / 2) - 10, b ? "Power" : "", StoneType.POWER));
        buttonList.add(space = new ButtonStones(id++, (width / 2) - 25, (height / 2) + 50, b ? "Space" : "", StoneType.SPACE));
        buttonList.add(reality = new ButtonStones(id++, (width / 2) + 50, (height / 2) - 10, b ? " Reality" : "", StoneType.REALITY));
        buttonList.add(mind = new ButtonStones(id++, (width / 2) - 25, (height / 2) - 40, b ? " Mind" : "", StoneType.MIND));

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

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        ButtonStones stone = (ButtonStones) button;

        if (stone.getType() != null) {
            GemHelper.setGem(stone.getType());
        }

        Minecraft.getMinecraft().displayGuiScreen(null);
        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public class ButtonStones extends GuiButton {

        int BUTTON_WIDTH = 50;
        int BUTTON_HEIGHT = 60;
        StoneType type;

        public ButtonStones(int buttonId, int x, int y, String buttonText, StoneType type) {
            super(buttonId, x, y, buttonText);
            this.type = type;
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

        public StoneType getType() {
            return type;
        }
    }

}
