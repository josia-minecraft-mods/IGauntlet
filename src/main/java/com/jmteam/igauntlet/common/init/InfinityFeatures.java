package com.jmteam.igauntlet.common.init;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class InfinityFeatures {

    // Ore Gen
    public static void setupOresGeneration(BiomeLoadingEvent event) {
        if(event.getCategory().equals(Biome.Category.THEEND) || event.getCategory().equals(Biome.Category.NETHER)) return;

        setupOre(event, OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, InfinityBlocks.URU_ORE.get(), 4, 5, 60, 4);
    }

    private static void setupOre(BiomeLoadingEvent event, RuleTest ruleTest, Block block, int veinSize, int minHeight, int maxHeight, int count) {
        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
                new OreFeatureConfig(ruleTest, block.getDefaultState(), veinSize))
                .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                .square().count(count));
    }
}
