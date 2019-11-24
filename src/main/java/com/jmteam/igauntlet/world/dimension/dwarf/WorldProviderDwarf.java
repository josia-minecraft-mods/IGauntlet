package com.jmteam.igauntlet.world.dimension.dwarf;

import com.jmteam.igauntlet.common.init.InfinityBiomes;
import com.jmteam.igauntlet.common.init.InfinityDimensions;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;

public class WorldProviderDwarf extends WorldProvider {

    public WorldProviderDwarf() {
        this.biomeProvider = new BiomeProviderSingle(InfinityBiomes.DWARF_DIMENSION);
    }

    @Override
    public DimensionType getDimensionType() {
        return InfinityDimensions.DWARF;
    }

    @Nullable
    @Override
    public String getSaveFolder() {
        return "Dwarf";
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return super.createChunkGenerator();
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }
}
