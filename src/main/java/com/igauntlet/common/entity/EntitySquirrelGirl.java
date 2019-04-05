package com.igauntlet.common.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.world.World;

public class EntitySquirrelGirl extends EntityCow {

    public EntitySquirrelGirl(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.0D));
        this.tasks.addTask(8, new EntityAIWander(this, 1.0D));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0);
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }
}
