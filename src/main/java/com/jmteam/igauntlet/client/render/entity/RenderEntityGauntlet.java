package com.jmteam.igauntlet.client.render.entity;

import com.jmteam.igauntlet.common.entity.EntityGauntlet;
import com.jmteam.igauntlet.common.init.InfinityItems;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderEntityGauntlet extends EntityRenderer<EntityGauntlet> {

    public RenderEntityGauntlet(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(EntityGauntlet entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180 - entityYaw));
        RenderHelper.turnBackOn();
        matrixStackIn.translate(0, 0.5, 0);
        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(InfinityItems.INFINITY_GAUNTLET.get()), ItemCameraTransforms.TransformType.NONE, packedLightIn, packedLightIn, matrixStackIn, bufferIn);
        RenderHelper.turnOff();
        matrixStackIn.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(EntityGauntlet p_110775_1_) {
        return new ResourceLocation("");
    }
}
