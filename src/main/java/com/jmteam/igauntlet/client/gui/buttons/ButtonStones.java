package com.jmteam.igauntlet.client.gui.buttons;

import com.jmteam.igauntlet.network.NetworkHandler;
import com.jmteam.igauntlet.network.packets.server.PacketSetStone;
import com.jmteam.igauntlet.util.gauntlet.GemHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.widget.ExtendedButton;

public class ButtonStones extends ExtendedButton {
    GemHelper.StoneType type;
    boolean text;

    public ButtonStones(int x, int y, String buttonText, GemHelper.StoneType stoneType, boolean text) {
        super(x, y, 50, 60, new TranslationTextComponent(buttonText), null);
        this.type = stoneType;
        this.text = text;
    }

    @Override
    public void renderButton(MatrixStack mStack, int mouseX, int mouseY, float partial) {
        if (this.visible && text) {
            Minecraft mc = Minecraft.getInstance();
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            this.renderBg(mStack, mc, mouseX, mouseY);
            int color = getFGColor();

            if (this.isHovered && this.packedFGColor == Widget.UNSET_FG_COLOR)
                color = 0xFFFFA0; // Slightly Yellow

            String buttonText = this.getMessage().getString();
            int strWidth = mc.fontRenderer.getStringWidth(buttonText);
            int ellipsisWidth = mc.fontRenderer.getStringWidth("...");
            if (strWidth > width - 6 && strWidth > ellipsisWidth)
                buttonText = mc.fontRenderer.trimStringToWidth(ITextProperties.func_240652_a_(buttonText), width - 6 - ellipsisWidth) + "...";


            this.drawCenteredString(mStack, mc.fontRenderer, buttonText, this.x + this.width / 2, this.y + (this.height - 8) / 2, color);
        }
    }

    @Override
    public void onPress() {
        NetworkHandler.sendServerPacket(new PacketSetStone(type));
        Minecraft.getInstance().displayGuiScreen(null);
    }

}
