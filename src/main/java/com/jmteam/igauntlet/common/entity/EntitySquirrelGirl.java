package com.jmteam.igauntlet.common.entity;

import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntitySquirrelGirl extends EntityMob {

    public EntitySquirrelGirl(World worldIn) {
        super(worldIn);
        setSize(1,1);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        tasks.addTask(1, new EntityAIWander(this, 0.2f, 4));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return super.getDeathSound();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }
}
