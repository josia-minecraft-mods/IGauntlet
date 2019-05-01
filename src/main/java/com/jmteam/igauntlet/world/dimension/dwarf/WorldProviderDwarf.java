package com.jmteam.igauntlet.world.dimension.dwarf;

import com.jmteam.igauntlet.world.InfinityBiomes;
import com.jmteam.igauntlet.world.InfinityDimensions;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldProviderDwarf extends WorldProvider {

    private static BiomeProviderSingle biomeP = new BiomeProviderSingle(InfinityBiomes.DWARF_DIMENSION);

    public WorldProviderDwarf() {

    }

    @Override
    public DimensionType getDimensionType() {
        return InfinityDimensions.DWARF;
    }


    @Override
    public BiomeProvider getBiomeProvider() {
        return biomeP;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }
}
