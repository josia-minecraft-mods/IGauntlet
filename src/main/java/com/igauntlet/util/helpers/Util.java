package com.igauntlet.util.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

import static net.minecraft.client.renderer.OpenGlHelper.*;

public class Util {
    public static void setupRenderLightning() {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_CONSTANT_ALPHA);
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569F);
        setLightmapTextureCoords(240, 240, 0);
    }

    public static void finishRenderLightning() {
        restoreLightmapTextureCoords();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void restoreLightmapTextureCoords() {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
    }

    public static void drawGlowingLine(Vec3d start, Vec3d end, float thickness, Vec3d color) {
        drawGlowingLine(start, end, thickness, color, 1F);
    }

    public static void drawGlowingLine(Vec3d start, Vec3d end, float thickness, Vec3d color, float alpha) {
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
                GlStateManager.color((float) color.x, (float) color.y, (float) color.z, 1.0F / layers / 2);
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

}
