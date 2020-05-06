package com.jmteam.igauntlet.common.world.dimension.dwarf;

import com.jmteam.igauntlet.common.init.InfinityBiomes;
import com.jmteam.igauntlet.common.init.InfinityDimensions;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

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
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorDwarf(world, getSeed());
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }
}
