package com.igauntlet.world.dimension.dwarf;

import com.igauntlet.init.ModBiomes;
import com.igauntlet.init.ModDimensions;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldProviderDwarf extends WorldProvider {

    private static BiomeProviderSingle biomeP = new BiomeProviderSingle(ModBiomes.DWARF_DIMENSION);

    public WorldProviderDwarf() {

    }

    @Override
    public DimensionType getDimensionType() {
        return ModDimensions.DWARF;
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
