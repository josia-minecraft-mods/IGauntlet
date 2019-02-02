package com.igauntlet.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class entityshoot extends ModelBase {
    private final ModelRenderer bone;

    public entityshoot() {
        textureWidth = 48;
        textureHeight = 48;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 11.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 0, 0, -1.5F, -1.0F, -6.0F, 3, 3, 16, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 3, 2, 0.0F, -0.75F, -7.0F, 0, 3, 14, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 1, 6, -2.0F, 0.5F, -6.75F, 4, 0, 13, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        bone.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}