package com.igauntlet.common.entity;

import com.igauntlet.network.NetworkHandler;
import com.igauntlet.network.packets.MessagePortalTeleport;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityPortal extends EntityCow {

    public EntityPortal(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.0D));
        this.tasks.addTask(8, new EntityAIWander(this, 0.0D));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0);
    }


    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        super.onCollideWithPlayer(entityIn);
        int x = this.getEntityData().getInteger("x");
        int y = this.getEntityData().getInteger("y");
        int z = this.getEntityData().getInteger("z");
        if(!this.getEntityData().getBoolean("isinit")) return;
        BlockPos pos = new BlockPos(x,y,z);
        NetworkHandler.NETWORK.sendToServer(new MessagePortalTeleport(pos));
        this.setDead();
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(this.ticksExisted >= 100 || this.collidedHorizontally || this.collidedVertically) {
            this.setDead();
        }
    }
}
