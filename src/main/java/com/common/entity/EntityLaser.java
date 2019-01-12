package com.common.entity;


import io.netty.buffer.ByteBuf;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityLaser extends EntityThrowable implements IEntityAdditionalSpawnData {

    public float damage;
    private DamageSource source;

    public EntityLaser(World worldIn) {
        super(worldIn);
    }

    public EntityLaser(World worldIn, EntityLivingBase throwerIn, float damage, DamageSource source, Vec3d color) {
        super(worldIn, throwerIn);
        this.damage = damage;
        this.source = source;
    }


    @Override
    protected void onImpact(RayTraceResult result) {
        if (result == null || isDead)
            return;

        if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
            if (result.entityHit == this.thrower) return;
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
        } else if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
            IBlockState block = world.getBlockState(result.getBlockPos());

            if (block.getBlock() == Blocks.TNT) {
                BlockTNT tnt = (BlockTNT) block;
                tnt.explode(world, result.getBlockPos(), block, getThrower());
            }
        }

        if (!this.world.isRemote)
            this.setDead();
    }
    @Override
    protected float getGravityVelocity() {
        return 0.00001F;
    }


    @Override
    public void writeSpawnData(ByteBuf buffer) {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeEntityToNBT(nbt);
        ByteBufUtils.writeTag(buffer, nbt);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        this.readEntityFromNBT(ByteBufUtils.readTag(additionalData));
    }
}