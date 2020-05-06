package com.jmteam.igauntlet.common.entity;

import com.jmteam.igauntlet.util.helpers.PlayerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityPortal extends Entity {

    private BlockPos pos;
    private boolean init;

    public EntityPortal(World world) {
        super(world);
    }

    public EntityPortal(World worldIn, int X, int Y, int Z, float rot, boolean init) {
        super(worldIn);
        this.pos = new BlockPos(X, Y, Z);
        this.rotationYaw = rot;
        this.init = init;
    }

    @Override
    protected void entityInit() {

    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
        super.onCollideWithPlayer(player);

        if (init) {
            PlayerHelper.teleportToSafePosition(player, pos);
            init = !init;
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.ticksExisted >= 70 || !init) this.setDead();
    }
}
