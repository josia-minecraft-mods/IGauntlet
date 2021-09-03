package com.jmteam.igauntlet.common.tileentity;

import com.jmteam.igauntlet.common.init.InfinityNBT;
import com.jmteam.igauntlet.common.init.InfinityTileEntities;
import com.jmteam.igauntlet.util.helpers.EntityHelper;
import com.jmteam.igauntlet.util.helpers.WorldHelper;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BlockState;
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
        if (!level.isClientSide() && level.getGameTime() % 20 == 0) {
            if (timer >= 60)  {
                WorldHelper.setBlockState(level, Blocks.AIR.defaultBlockState(), worldPosition);
                timer = 0;
            }
            timer++;
        }
    }

    public void setEntity(LivingEntity entity) {
        CompoundNBT nbt = entity.serializeNBT();
        this.entity = nbt.toString();
        setChanged();
    }

    public LivingEntity getEntity() {
        try {
            if (!entity.isEmpty()) {
                CompoundNBT compoundNBT = JsonToNBT.parseTag(this.entity);
                return (LivingEntity) EntityHelper.createEntityFromNBT(compoundNBT, level);
            }
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void clearEntity() {
        entity = "";
        setChanged();
    }


    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);

        entity = compound.getString("entity_data");
        timer = compound.getInt(InfinityNBT.TIMER);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);

        compound.putString("entity_data", entity);
        compound.putInt(InfinityNBT.TIMER, timer);

        return compound;
    }
}
