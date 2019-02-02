package com.igauntlet.init;

import com.igauntlet.world.dimension.dwarf.BiomeDwarf;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.*;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModBiomes {
    public static final Biome DWARF_DIMENSION = new BiomeDwarf();

    public static void registerBiomes() {
        initBiome(DWARF_DIMENSION, "Dwarf", BiomeType.WARM, Type.SPOOKY, Type.DENSE, Type.DRY);
    }

    private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types) {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));

        return biome;
    }
}