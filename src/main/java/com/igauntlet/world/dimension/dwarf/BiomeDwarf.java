package com.igauntlet.world.dimension.dwarf;

import com.igauntlet.init.ModBlocks;
import com.igauntlet.world.dimension.IBiomeDecorator;
import net.minecraft.world.biome.BiomePlains;

public class BiomeDwarf extends BiomePlains {

    public BiomeDwarf()
    {
        super(false, new BiomeProperties("Dwarf").setBaseBiome("plains").setHeightVariation(0.00F).setBaseHeight(2F).setRainDisabled().setTemperature(0.4F));
        this.topBlock = ModBlocks.DWARF_STONE.getDefaultState();
        this.fillerBlock = ModBlocks.DWARF_DIRT.getDefaultState();
        this.decorator = new IBiomeDecorator();
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.decorator.generateFalls = false;
    }

}
