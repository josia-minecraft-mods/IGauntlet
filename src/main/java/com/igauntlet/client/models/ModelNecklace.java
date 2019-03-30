package com.igauntlet.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNecklace extends ModelBiped {
	private final ModelRenderer necklace;
	private final ModelRenderer stonesmoothness;
	ModelBiped biped;

	public ModelNecklace(ModelBiped modelBiped) {
		this.biped = modelBiped;
		textureWidth = 32;
		textureHeight = 32;
		this.bipedHeadwear.isHidden = true;

		necklace = new ModelRenderer(this);
		necklace.setRotationPoint(-8.0F, 16.0F, 8.0F);

		stonesmoothness = new ModelRenderer(this);
		stonesmoothness.setRotationPoint(0.0F, 0.0F, 0.0F);
		necklace.addChild(stonesmoothness);
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 6.5F, -11.0F, -12.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 8.5F, -11.0F, -12.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 8.5F, -9.5F, -12.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 6.5F, -9.5F, -12.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 8.5F, -11.0F, -10.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 6.5F, -11.0F, -10.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 8.5F, -9.5F, -10.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 6.5F, -9.5F, -10.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 5.5F, -11.0F, -11.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 6.5F, -9.5F, -11.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 9.5F, -9.5F, -11.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 9.5F, -11.0F, -11.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 5.5F, -11.0F, -10.0F, 1, 1, 0, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 9.5F, -11.0F, -10.0F, 1, 1, 0, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 10.5F, -11.0F, -11.0F, 0, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 5.0F, -11.0F, -11.0F, 0, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 8, 11, 9.5F, -13.0F, -11.0F, 1, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 4, 6, 10.5F, -15.0F, -11.0F, 1, 2, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 4, 11, 5.5F, -13.0F, -11.0F, 1, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 12, 7, 6.5F, -12.0F, -11.0F, 3, 3, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 9.5F, -11.0F, -11.0F, 1, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 6.0F, -11.0F, -11.0F, 1, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 6.5F, -11.0F, -10.0F, 3, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 7.5F, -9.0F, -12.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 7.5F, -10.0F, -10.0F, 1, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 7.5F, -12.0F, -10.0F, 1, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 5, 22, 9.0F, -11.0F, -12.0F, 0, 1, 0, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 6.5F, -11.0F, -12.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 8.5F, -11.0F, -12.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 8.5F, -9.5F, -12.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 6.5F, -9.5F, -12.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 5.5F, -11.0F, -11.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 5.5F, -9.5F, -11.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 9.5F, -11.0F, -11.5F, 1, 1, 0, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 5.5F, -11.0F, -11.5F, 1, 1, 0, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 5.5F, -11.0F, -11.0F, 0, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 7, 7, 11.5F, -16.0F, -11.0F, 1, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 0, 0, 11.5F, -17.0F, -10.0F, 1, 1, 6, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 0, 7, 5.5F, -17.0F, -4.0F, 5, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 0, 11, 4.5F, -15.0F, -11.0F, 1, 2, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 4, 11, 3.5F, -16.0F, -11.0F, 1, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 14, 0, 3.5F, -17.0F, -10.0F, 1, 1, 6, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 16, 16, 4.5F, -17.0F, -4.0F, 1, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 5, 6, 10.5F, -17.0F, -4.0F, 1, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 12, 7, 6.5F, -12.0F, -11.0F, 3, 3, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 6.0F, -11.0F, -11.0F, 1, 1, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 7.5F, -11.5F, -12.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 8.0F, -9.0F, -12.0F, 1, 0, 1, 0.0F, false));
		stonesmoothness.cubeList.add(new ModelBox(stonesmoothness, 6, 24, 6.5F, -11.0F, -12.0F, 0, 1, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		necklace.render(f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.bipedHeadwear.addChild(necklace);
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