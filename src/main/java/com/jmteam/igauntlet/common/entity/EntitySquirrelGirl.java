package com.jmteam.igauntlet.common.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntitySquirrelGirl extends EntityLiving {

    public EntitySquirrelGirl(World worldIn) {
        super(worldIn);
        setSize(1,1);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return super.getDeathSound();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }
}
