package com.jmteam.igauntlet.util;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class Graphics {

    /** Older minecraft GUI code*/
    public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(x, (y + height), 0).uv(((float) textureX * 0.00390625F), ((float) (textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.vertex((x + width), (y + height), 0).uv(((float) (textureX + width) * 0.00390625F), ((float) (textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.vertex((x + width), y, 0).uv(((float) (textureX + width) * 0.00390625F), ((float) textureY * 0.00390625F)).endVertex();
        bufferbuilder.vertex(x, y, 0).uv(((float) (textureX) * 0.00390625F), ((float) textureY * 0.00390625F)).endVertex();
        tessellator.end();
    }
}
