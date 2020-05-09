package com.jmteam.igauntlet.client.gui;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.client.gui.buttons.ButtonStones;
import com.jmteam.igauntlet.util.Graphics;
import com.jmteam.igauntlet.util.gauntlet.GemHelper.StoneType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class GuiGauntlet extends Screen {


    static final int GUI_WIDTH = 256;
    static final int GUI_HEIGHT = 256;

    public static final ResourceLocation TEXTURE = new ResourceLocation(IGauntlet.MODID, "textures/gui/gui_gauntlet.png");

    public GuiGauntlet() {
        super(new TranslationTextComponent("gui.stone_selection"));
    }

    @Override
    protected void init() {
        super.init();
        int halfW = (width / 2);
        int halfH = (height / 2);
        boolean t = true;

        this.addButton(new ButtonStones(halfW - 80, halfH - 105, " Time", StoneType.TIME, t));

        this.addButton(new ButtonStones(halfW + 30, halfH - 105, " Soul", StoneType.SOUL, t));
        this.addButton(new ButtonStones(halfW - 100, halfH - 10, " Power", StoneType.POWER, t));

        this.addButton(new ButtonStones(halfW - 25, halfH + 50, " Space", StoneType.SPACE, t));
        this.addButton(new ButtonStones(halfW + 50, halfH - 10, " Reality", StoneType.REALITY, t));
        this.addButton(new ButtonStones(halfW - 25, halfH - 40, " Mind", StoneType.MIND, t));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {

        this.renderBackground();
        Minecraft.getInstance().getTextureManager().bindTexture(TEXTURE);
        int x = (width - GUI_WIDTH) / 2;
        int y = (height - GUI_HEIGHT) / 2;
        Graphics.drawTexturedModalRect(x, y, 0, 0, GUI_WIDTH, GUI_HEIGHT);
        super.render(p_render_1_, p_render_2_, p_render_3_);
    }
}
