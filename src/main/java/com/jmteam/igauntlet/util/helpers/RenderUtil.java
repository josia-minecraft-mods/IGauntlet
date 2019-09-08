package com.jmteam.igauntlet.util.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static net.minecraft.client.renderer.OpenGlHelper.lastBrightnessX;
import static net.minecraft.client.renderer.OpenGlHelper.lastBrightnessY;

/**
 * Credits to Lucraft for allowing us to use this
 */
public class RenderUtil {
    public static void setupRenderLightning() {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_CONSTANT_ALPHA);
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569F);
        setLightmapTextureCoords(240, 240);
    }

    public static void finishRenderLightning() {
        restoreLightmapTextureCoords();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }


    public static void setLightmapTextureCoords(float x, float y) {
        lastBrightnessX = OpenGlHelper.lastBrightnessX;
        lastBrightnessY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, x, y);
    }

    public static void restoreLightmapTextureCoords() {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
    }


    public static void drawGlowingLine(Vec3d start, Vec3d end, float thickness, Color color) {
        drawGlowingLine(start, end, thickness, color, 1F);
    }

    public static void drawGlowingLine(Vec3d start, Vec3d end, float thickness, Color color, float alpha) {
        if (start == null || end == null)
            return;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bb = tessellator.getBuffer();
        int smoothFactor = Minecraft.getMinecraft().gameSettings.ambientOcclusion;
        int layers = 10 + smoothFactor * 20;

        GlStateManager.pushMatrix();
        start = start.scale(-1D);
        end = end.scale(-1D);
        GlStateManager.translate(-start.x, -start.y, -start.z);
        start = end.subtract(start);
        end = end.subtract(end);

        {
            double x = end.x - start.x;
            double y = end.y - start.y;
            double z = end.z - start.z;
            double diff = MathHelper.sqrt(x * x + z * z);
            float yaw = (float) (Math.atan2(z, x) * 180.0D / 3.141592653589793D) - 90.0F;
            float pitch = (float) -(Math.atan2(y, diff) * 180.0D / 3.141592653589793D);
            GlStateManager.rotate(-yaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(pitch, 1.0F, 0.0F, 0.0F);
        }

        for (int layer = 0; layer <= layers; ++layer) {
            if (layer < layers) {
                GlStateManager.color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 1.0F / layers / 2);
                GlStateManager.depthMask(false);
            } else {
                GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
                GlStateManager.depthMask(true);
            }
            double size = thickness + (layer < layers ? layer * (1.25D / layers) : 0.0D);
            double d = (layer < layers ? 1.0D - layer * (1.0D / layers) : 0.0D) * 0.1D;
            double width = 0.0625D * size;
            double height = 0.0625D * size;
            double length = start.distanceTo(end) + d;

            bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
            bb.pos(-width, height, length).endVertex();
            bb.pos(width, height, length).endVertex();
            bb.pos(width, height, -d).endVertex();
            bb.pos(-width, height, -d).endVertex();
            bb.pos(width, -height, -d).endVertex();
            bb.pos(width, -height, length).endVertex();
            bb.pos(-width, -height, length).endVertex();
            bb.pos(-width, -height, -d).endVertex();
            bb.pos(-width, -height, -d).endVertex();
            bb.pos(-width, -height, length).endVertex();
            bb.pos(-width, height, length).endVertex();
            bb.pos(-width, height, -d).endVertex();
            bb.pos(width, height, length).endVertex();
            bb.pos(width, -height, length).endVertex();
            bb.pos(width, -height, -d).endVertex();
            bb.pos(width, height, -d).endVertex();
            bb.pos(width, -height, length).endVertex();
            bb.pos(width, height, length).endVertex();
            bb.pos(-width, height, length).endVertex();
            bb.pos(-width, -height, length).endVertex();
            bb.pos(width, -height, -d).endVertex();
            bb.pos(width, height, -d).endVertex();
            bb.pos(-width, height, -d).endVertex();
            bb.pos(-width, -height, -d).endVertex();
            tessellator.draw();
        }

        GlStateManager.popMatrix();
    }

    public static void drawOutline(Vec3d size, Color color) {
        GlStateManager.pushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GlStateManager.color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        Tessellator tes = Tessellator.getInstance();
        BufferBuilder buf = tes.getBuffer();
        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buf.pos(0, 0, 0).tex(0, 0).endVertex();
        buf.pos(0, size.y, 0).tex(0, 1).endVertex();
        buf.pos(size.x, size.y, 0).tex(1, 1).endVertex();
        buf.pos(size.x, 0, 0).tex(1, 0).endVertex();
        tes.draw();
        GlStateManager.color(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GlStateManager.popMatrix();
    }
}
