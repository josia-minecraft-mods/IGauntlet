package com.jmteam.igauntlet.common.entity;

import com.jmteam.igauntlet.util.helpers.GauntletHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.awt.*;

public class EntityLaser extends EntityThrowable implements IEntityAdditionalSpawnData {

    public float damage;
    private DamageSource source;
    public Color color;

    public EntityLaser(World worldIn) {
        super(worldIn);
    }

    public EntityLaser(World worldIn, EntityLivingBase throwerIn, float damage, DamageSource source, Color color) {
        super(worldIn, throwerIn);
        this.damage = damage;
        this.color = color;
        this.source = source;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        if (result == null || isDead || world.isRemote)
            return;

        if (result.typeOfHit == Type.ENTITY) {
            Entity entity = result.entityHit;
            if (entity == this.thrower) return;

            if (!(entity instanceof EntityHanging)) {

                if (entity instanceof EntityLiving) {
                    if (entity.getIsInvulnerable()) return;

                    if (entity instanceof EntityEnderman || entity instanceof EntityDragon) {
                        entity.setDead();
                    }
                }

                if (entity instanceof EntityPlayer) {
                    EntityPlayer p = (EntityPlayer) entity;
                    if (p.isCreative()) return;
                }

                if (!(entity instanceof EntityPlayer) && entity instanceof EntityLiving) {
                    GauntletHelper.makeAshPile(world, entity.getPosition(), (EntityLiving) entity);
                }

                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
                this.setDead();
            }


        } else if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
            this.setDead();
        }

        if (!this.world.isRemote)
            this.setDead();
    }


    @Override
    protected float getGravityVelocity() {
        return 0.00000F;
    }


    @Override
    public void writeSpawnData(ByteBuf buffer) {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeEntityToNBT(nbt);
        ByteBufUtils.writeTag(buffer, nbt);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setFloat("Damage", damage);
        compound.setDouble("Color_R", color.getRed());
        compound.setDouble("Color_G", color.getGreen());
        compound.setDouble("Color_B", color.getBlue());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.damage = compound.getFloat("Damage");
        this.color = new Color(compound.getInteger("Color_R"), compound.getInteger("Color_G"), compound.getInteger("Color_B"));
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        this.readEntityFromNBT(ByteBufUtils.readTag(additionalData));
    }

    @Override
    public void onEntityUpdate() {
        if (world.isRemote) return;

        double movingspeed = new Vec3d(posX, posY, posZ).distanceTo(new Vec3d(prevPosX, prevPosY, prevPosZ));
        if (this.ticksExisted >= 400 || movingspeed < 0.01) {
            this.setDead();
        }
    }
}