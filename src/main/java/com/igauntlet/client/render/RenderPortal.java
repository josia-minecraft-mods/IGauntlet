package com.igauntlet.client.render;

import com.igauntlet.Infinity;
import com.igauntlet.client.models.ModelPortal;
import com.igauntlet.common.entity.EntityPortal;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderPortal extends RenderLiving<EntityPortal> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Infinity.MODID + ":textures/entity/portal.png");

    public RenderPortal(RenderManager renderManager) {
        super(renderManager, new ModelPortal(), 0.0F);
    }

    @Override
    protected void applyRotations(EntityPortal entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityPortal entity) {
        return TEXTURES;
    }
}
