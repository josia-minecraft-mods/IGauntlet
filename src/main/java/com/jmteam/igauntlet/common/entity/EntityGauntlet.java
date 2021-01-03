package com.jmteam.igauntlet.common.entity;

import com.jmteam.igauntlet.common.init.InfinityEntities;
import com.jmteam.igauntlet.common.init.InfinityItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityGauntlet extends Entity {

    public ItemStack stack;

    public EntityGauntlet(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public EntityGauntlet(ItemStack i, World worldIn) {
        super(InfinityEntities.GAUNTLET.get(), worldIn);
        stack = i;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public void recalculateSize() {
        double d0 = this.getPosX();
        double d1 = this.getPosY();
        double d2 = this.getPosZ();
        super.recalculateSize();
        this.setPosition(d0, d1, d2);
    }

    @Override
    protected void registerData() {

    }

    @Override
    public void tick() {
        super.tick();
        //recalculateSize();

        if (!onGround) {
            this.setMotion(new Vec3d(getMotion().x, -0.2f, getMotion().z));
            this.move(MoverType.SELF, this.getMotion());
        } else {
            if (!world.isRemote) {
                if (stack == null || stack.getItem() != InfinityItems.INFINITY_GAUNTLET.get()) onKillCommand();
            }
        }
    }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity player, Vec3d vec, Hand hand) {
        boolean hasEmptySlot = player.inventory.getFirstEmptyStack() != -1;
        if (!world.isRemote && hasEmptySlot && stack != null) {
            player.inventory.addItemStackToInventory(stack);
            onKillCommand();
        } else if (!world.isRemote) {
            if (!hasEmptySlot) player.sendStatusMessage(new TranslationTextComponent("msg.inventoryfull"), true);
            if (stack == null) onKillCommand();
        }

        return super.applyPlayerInteraction(player, vec, hand);
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        stack = ItemStack.read(compound.getCompound("stack"));
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        CompoundNBT compoundNBT = new CompoundNBT();
        stack.write(compoundNBT);
        compound.put("stack", compoundNBT);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
