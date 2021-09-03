package com.jmteam.igauntlet.client.screen;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.client.screen.buttons.ButtonStones;
import com.jmteam.igauntlet.config.InfinityConfig;
import com.jmteam.igauntlet.util.Graphics;
import com.jmteam.igauntlet.util.gauntlet.GemHelper.StoneType;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class StoneSelectionScreen extends Screen {


    static final int GUI_WIDTH = 256;
    static final int GUI_HEIGHT = 256;

    public static final ResourceLocation TEXTURE = new ResourceLocation(IGauntlet.MODID, "textures/gui/gui_gauntlet.png");

    public StoneSelectionScreen() {
        super(new TranslationTextComponent("gui.stone_selection"));
    }

    @Override
    protected void init() {
        super.init();
        int halfW = (width / 2);
        int halfH = (height / 2);
        boolean showText = InfinityConfig.stoneGUIText;

        this.addButton(new ButtonStones(halfW - 80, halfH - 105, " Time", StoneType.TIME, showText));

        this.addButton(new ButtonStones(halfW + 30, halfH - 105, " Soul", StoneType.SOUL, showText));
        this.addButton(new ButtonStones(halfW - 100, halfH - 10, " Power", StoneType.POWER, showText));

        this.addButton(new ButtonStones(halfW - 25, halfH + 50, " Space", StoneType.SPACE, showText));
        this.addButton(new ButtonStones(halfW + 50, halfH - 10, " Reality", StoneType.REALITY, showText));
        this.addButton(new ButtonStones(halfW - 25, halfH - 40, " Mind", StoneType.MIND, showText));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        Minecraft.getInstance().getTextureManager().bind(TEXTURE);
        int x = (width - GUI_WIDTH) / 2;
        int y = (height - GUI_HEIGHT) / 2;
        Graphics.drawTexturedModalRect(x, y, 0, 0, GUI_WIDTH, GUI_HEIGHT);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}