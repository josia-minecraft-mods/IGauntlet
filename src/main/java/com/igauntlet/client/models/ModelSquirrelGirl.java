package com.igauntlet.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Squirrel.tcn - TechneToTabulaImporter
 * Created using Tabula 7.0.0
 */
public class ModelSquirrelGirl extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer rightarm;
    public ModelRenderer leftarm;
    public ModelRenderer rightleg;
    public ModelRenderer leftleg;

    public ModelSquirrelGirl() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.rightleg = new ModelRenderer(this, 0, 16);
        this.rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.leftarm = new ModelRenderer(this, 40, 16);
        this.leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.rightarm = new ModelRenderer(this, 40, 16);
        this.rightarm.setRotationPoint(-4.0F, 2.0F, 0.0F);
        this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.leftleg = new ModelRenderer(this, 0, 16);
        this.leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.rightleg.render(f5);
        this.head.render(f5);
        this.leftarm.render(f5);
        this.rightarm.render(f5);
        this.leftleg.render(f5);
        this.body.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
