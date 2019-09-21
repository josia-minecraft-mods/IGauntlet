package com.jmteam.igauntlet.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPortal extends ModelBase {
    private final ModelRenderer bone;

    public ModelPortal() {
        textureWidth = 226;
        textureHeight = 223;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.cubeList.add(new ModelBox(bone, 147, 113, -17.0F, -32.0F, 0.0F, 32, 32, -1, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        bone.render(f5);
    }
}