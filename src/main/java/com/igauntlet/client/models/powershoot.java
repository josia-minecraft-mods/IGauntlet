package com.igauntlet.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class powershoot extends ModelBase {

    private final ModelRenderer bone;

    public powershoot() {
        textureWidth = 48;
        textureHeight = 48;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 11.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 0, 0, -3.0F, 0.0F, -7.0F, 6, 0, 16, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 0, 16, 0.0F, -3.0F, -7.0F, 0, 6, 16, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 11, 28, 2.0F, -2.0F, -7.0F, 0, 4, 16, 0.0F, false));
        bone.cubeList.add(new ModelBox(bone, 11, 28, -2.0F, -2.0F, -7.0F, 0, 4, 16, 0.0F, true));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        bone.render(f5);
    }


    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}