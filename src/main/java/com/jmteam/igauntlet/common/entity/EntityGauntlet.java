package com.jmteam.igauntlet.common.entity;

import com.jmteam.igauntlet.common.init.InfinityEntities;
import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.common.init.InfinityMessages;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityGauntlet extends Entity {

    public ItemStack stack;

    public EntityGauntlet(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void defineSynchedData() {

    }

    public EntityGauntlet(ItemStack i, World worldIn) {
        super(InfinityEntities.GAUNTLET.get(), worldIn);
        stack = i;
    }

    @Override
    public void refreshDimensions() {
        double d0 = this.getX();
        double d1 = this.getY();
        double d2 = this.getZ();
        super.refreshDimensions();
        this.moveTo(d0, d1, d2);
    }

    @Override
    public void tick() {
        super.tick();
        //recalculateSize();


        if (!onGround) {
            setDeltaMovement(getDeltaMovement().x, -0.2f, getDeltaMovement().z);
            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            if (!level.isClientSide()) {
                if (stack == null || stack.getItem() != InfinityItems.INFINITY_GAUNTLET.get()) kill();
            }
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT compoundNBT) {
        stack = ItemStack.of(compoundNBT.getCompound("stack"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT compound) {
        CompoundNBT compoundNBT = new CompoundNBT();
        stack.save(compoundNBT);
        compound.put("stack", compoundNBT);
    }

    @Override
    public ActionResultType interactAt(PlayerEntity player, Vector3d vec, Hand hand) {
        if (!level.isClientSide()) {

            boolean hasEmptySlot = player.inventory.getFreeSlot() != -1;

            if (hasEmptySlot && stack != null) {
                player.inventory.add(stack.copy());
                stack = null;
                kill();
            } else {
                if (!hasEmptySlot) player.displayClientMessage(InfinityMessages.getComponent(InfinityMessages.INVENTORY_FULL), true);
                if (stack == null) kill();
            }
        }

        return super.interactAt(player, vec, hand);
    }

    @Override
    public boolean canBeCollidedWith() {
        return onGround;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


}
