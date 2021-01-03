package com.jmteam.igauntlet.common.tileentity;

import com.jmteam.igauntlet.common.init.InfinityNBT;
import com.jmteam.igauntlet.common.init.InfinityTileEntities;
import com.jmteam.igauntlet.util.helpers.EntityHelper;
import com.jmteam.igauntlet.util.helpers.WorldHelper;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityAshPile extends InfinityTileEntityBase implements ITickableTileEntity {

    public String entity = "";
    private int timer = 0;

    public TileEntityAshPile() {
        super(InfinityTileEntities.ASH_PILE.get());
    }

    @Override
    public void tick() {
        // TODO Config for ash pile fade
        if (!world.isRemote && getWorld().getGameTime() % 20 == 0) {
            if (timer >= 60)  {
                WorldHelper.setBlockState(world, Blocks.AIR.getDefaultState(), pos);
                timer = 0;
            }
            timer++;
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
        timer = compound.getInt(InfinityNBT.TIMER);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        compound.putString("entity_data", entity);
        compound.putInt(InfinityNBT.TIMER, timer);

        return compound;
    }
}
