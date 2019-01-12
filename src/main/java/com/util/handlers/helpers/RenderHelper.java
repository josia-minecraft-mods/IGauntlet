package com.util.handlers.helpers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import static net.minecraft.client.renderer.OpenGlHelper.lastBrightnessX;
import static net.minecraft.client.renderer.OpenGlHelper.lastBrightnessY;


@SideOnly(Side.CLIENT)
public class RenderHelper {

    public static void setLightmapTextureCoords(float x, float y) {
        lastBrightnessX = lastBrightnessX;
        lastBrightnessY = lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, x, y);
    }

    public static void restoreLightmapTextureCoords() {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
    }

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


    public static void drawGlowingLine(Vec3d start, Vec3d end, float thickness, Vec3d color, float v) {
        drawGlowingLine(start, end, thickness, color, 1F);
    }

}
