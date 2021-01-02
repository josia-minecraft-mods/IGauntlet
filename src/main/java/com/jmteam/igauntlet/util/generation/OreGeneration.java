package com.jmteam.igauntlet.util.generation;

import com.jmteam.igauntlet.common.init.InfinityBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {

    public static void init() {
        for(Biome biome : ForgeRegistries.BIOMES) {
            addOreGen(biome, OreFeatureConfig.FillerBlockType.NATURAL_STONE, InfinityBlocks.URU_ORE.get().getDefaultState(), 7, 20,0,40);
        }
    }

    public static void addOreGen(Biome biome, OreFeatureConfig.FillerBlockType baseBlock, BlockState blockState, int chance, int offsetBottom, int offsetTop, int maxHeight)
    {
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(baseBlock, blockState, 8))
                .withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(chance, offsetBottom, offsetTop, maxHeight))));

    }
}
