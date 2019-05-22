package com.jmteam.igauntlet.world;

import com.jmteam.igauntlet.world.dimension.dwarf.BiomeDwarf;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.*;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class InfinityBiomes {
    public static final Biome DWARF_DIMENSION = new BiomeDwarf();

    public static void registerBiomes() {
        initBiome(DWARF_DIMENSION, "Dwarf", BiomeType.WARM, Type.SPOOKY, Type.DENSE, Type.DRY);
    }

    private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types) {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10)); // TODO Fix so it doesn't spawn in overworld!
        return biome;
    }
}