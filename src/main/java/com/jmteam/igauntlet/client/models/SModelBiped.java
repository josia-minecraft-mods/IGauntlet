package com.jmteam.igauntlet.client.models;//Made by Josia50
//Paste this code into your mod.

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SModelBiped extends ModelBiped {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer arms;
	private final ModelRenderer legs;

	public SModelBiped() {
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -32.0F, -4.0F, 8, 8, 8, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 16, 16, -4.0F, -24.0F, -2.0F, 8, 12, 4, 0.0F, false));

		arms = new ModelRenderer(this);
		arms.setRotationPoint(0.0F, 24.0F, 0.0F);
		arms.cubeList.add(new ModelBox(arms, 40, 16, 4.0F, -24.0F, -2.0F, 4, 12, 4, 0.0F, false));
		arms.cubeList.add(new ModelBox(arms, 40, 16, -8.0F, -24.0F, -2.0F, 4, 12, 4, 0.0F, false));

		legs = new ModelRenderer(this);
		legs.setRotationPoint(0.0F, 24.0F, 0.0F);
		legs.cubeList.add(new ModelBox(legs, 0, 16, 0.0F, -12.0F, -2.0F, 4, 12, 4, 0.0F, false));
		legs.cubeList.add(new ModelBox(legs, 0, 16, -4.0F, -12.0F, -2.0F, 4, 12, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		body.render(f5);
		arms.render(f5);
		legs.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}