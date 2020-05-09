package com.jmteam.igauntlet.client.render;

import com.jmteam.igauntlet.common.entity.EntityGauntlet;
import com.jmteam.igauntlet.common.init.InfinityItems;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class RenderEntityGauntlet extends EntityRenderer<EntityGauntlet> {

    public RenderEntityGauntlet(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(EntityGauntlet entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
         super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.rotate(Vector3f.YP.rotation(entityYaw - 135));
     /*   matrixStackIn.push();
        BlockState state;
        matrixStackIn.translate(-0.5,0,-0.5);
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
        state = Blocks.ENDER_CHEST.getDefaultState();
        blockRenderer.renderBlock(state, matrixStackIn, bufferIn, packedLightIn, packedLightIn, EmptyModelData.INSTANCE);
        matrixStackIn.pop();*/

        matrixStackIn.push();

        RenderHelper.enableStandardItemLighting();
        matrixStackIn.translate(0, 0.5, 0);
        Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack(InfinityItems.infinity_gauntlet), ItemCameraTransforms.TransformType.NONE, packedLightIn, packedLightIn, matrixStackIn, bufferIn);
        RenderHelper.disableStandardItemLighting();
        matrixStackIn.pop();
    }

    @Override
    public ResourceLocation getEntityTexture(EntityGauntlet entity) {
        return new ResourceLocation("");
    }
}
