package com.jmteam.igauntlet.client.render.entity;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.common.entity.EntitySquirrelGirl;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderSquirrelGirl extends RenderLiving<EntitySquirrelGirl> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Infinity.MODID + ":textures/entity/squirrelgirl.png");

    public RenderSquirrelGirl(RenderManager renderManager) {
        super(renderManager, new ModelPlayer(1.0f, true), 0.0F);
    }

    @Override
    protected void applyRotations(EntitySquirrelGirl entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySquirrelGirl entity) {
        return TEXTURES;
    }
}
