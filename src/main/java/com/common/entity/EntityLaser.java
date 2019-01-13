package com.common.entity;


import com.init.ModBlocks;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityLaser extends EntityThrowable implements IEntityAdditionalSpawnData {

    public float damage;
    private DamageSource source;
    public Vec3d color;


    public EntityLaser(World worldIn) {
        super(worldIn);
    }

    public EntityLaser(World worldIn, EntityLivingBase throwerIn, float damage, DamageSource source, Vec3d color) {
        super(worldIn, throwerIn);
        this.damage = damage;
        this.color = color;
        this.source = source;
    }


    @Override
    protected void onImpact(RayTraceResult result) {

        if (result == null || isDead)
            return;

        if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
            if (result.entityHit == this.thrower) return;

            result.entityHit.performHurtAnimation();
            Block blk = ModBlocks.ASH_PILE;
            BlockPos pos0 = new BlockPos(result.entityHit.posX,result.entityHit.posY, result.entityHit.posZ);
            IBlockState state0 = blk.getDefaultState();
            world.setBlockState(pos0, state0);
            result.entityHit.attackEntityFrom(DamageSource.OUT_OF_WORLD, 100);

        } else if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
            this.setDead();
        }

        if(this.ticksExisted == 80) {
            setDead();
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
        compound.setDouble("Color_R", color.x);
        compound.setDouble("Color_G", color.y);
        compound.setDouble("Color_B", color.z);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.damage = compound.getFloat("Damage");
        this.color = new Vec3d(compound.getDouble("Color_R"), compound.getDouble("Color_G"), compound.getDouble("Color_B"));
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        this.readEntityFromNBT(ByteBufUtils.readTag(additionalData));
    }
}