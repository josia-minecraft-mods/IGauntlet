package com.jmteam.igauntlet.util.registry;

import com.jmteam.igauntlet.IGauntlet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraftforge.fml.RegistryObject;

/**
 * Does Entity Creation but simplified
 * Made by Josia50
 */

public class EntityBuilder {

    private String name = "";
    private ResourceLocation id;
    private EntityType.Builder<? extends Entity> builder;
    private EntityClassification classification = EntityClassification.AMBIENT;
    private EntityType.IFactory<?> factoryIn;
    private Vector2f size;

    public static EntityBuilder create() {
        return new EntityBuilder();
    }

    public static EntityBuilder create(String name) {
        return create().setName(name).setId(new ResourceLocation(IGauntlet.MODID, name));
    }

    public EntityBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public <T extends Entity> EntityBuilder entity(EntityType.IFactory<T> factoryIn, EntityClassification classification) {
        this.factoryIn = factoryIn;
        this.classification = classification;
        create(factoryIn, classification);
        return this;
    }


    public <T extends Entity> EntityBuilder create(EntityType.IFactory<T> factoryIn, EntityClassification classificationIn) {
        this.builder = EntityType.Builder.create(factoryIn, classificationIn);
        return this;
    }

    public EntityBuilder setId(ResourceLocation location) {
        this.id = location;
        return this;
    }

    public EntityBuilder setClassification(EntityClassification classification) {
        this.classification = classification;
        return this;
    }

    public EntityBuilder size(float width, float height) {
        this.size = new Vector2f(width, height);
        builder.size(width, height);
        return this;
    }

    public <T extends Entity> RegistryObject<EntityType<T>> build() {
        if (factoryIn == null || classification == null) {
            IGauntlet.LOGGER.warn("[Incorrect Builder] " + "Couldn't register entity: " + name);
            return null;
        }

        EntityType<? extends Entity> entityType = builder.build(id.toString());

        return InfinityRegistry.ENTITY_TYPE_REGISTRY.register(name, () -> (EntityType<T>) entityType);
    }
}
