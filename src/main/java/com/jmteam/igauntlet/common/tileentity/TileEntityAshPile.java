package com.jmteam.igauntlet.common.tileentity;

import com.jmteam.igauntlet.util.helpers.EntityHelper;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.tileentity.ITickableTileEntity;

public class TileEntityAshPile extends InfinityTileEntityBase implements ITickableTileEntity {

    public String entity = "";
    public long created = System.currentTimeMillis();

    public TileEntityAshPile() {
        super();
    }

    @Override
    public void tick() {
        // TODO Config for ash pile fade
        if(!world.isRemote && created != 0 && ((System.currentTimeMillis() - created) / 1000L) >= 60 && getWorld().getGameTime() % 20 == 0) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    public void setEntity(LivingEntity entity) {
        CompoundNBT nbt = entity.serializeNBT();
        this.entity = nbt.toString();
        markDirty();
    }

    public LivingEntity getEntity() {
        try {
            if (!entity.isEmpty()) {
                CompoundNBT compoundNBT = JsonToNBT.getTagFromJson(this.entity);
                return (LivingEntity) EntityHelper.createEntityFromNBT(compoundNBT, world);
            }
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void clearEntity() {
        entity = "";
        markDirty();
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        entity = compound.getString("entity_data");
        created = compound.getLong("created");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        compound.putString("entity_data", entity);
        compound.putLong("created", created);

        return compound;
    }
}
