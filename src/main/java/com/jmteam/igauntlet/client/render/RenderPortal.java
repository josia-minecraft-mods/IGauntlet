package com.jmteam.igauntlet.client.render;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.client.models.ModelPortal;
import com.jmteam.igauntlet.common.entity.EntityPortal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderPortal extends Render<EntityPortal> {

    public static final ModelPortal portal = new ModelPortal();
    public static final ResourceLocation TEXTURES = new ResourceLocation(Infinity.MODID + ":textures/entity/portal.png");

    public RenderPortal(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityPortal entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURES);
        GlStateManager.rotate(entity.rotationYaw, 0,1,0);
        portal.render(entity, 0,0,0,0,0,0.0625f);
        GlStateManager.popMatrix();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityPortal entity) {
        return TEXTURES;
    }
}
