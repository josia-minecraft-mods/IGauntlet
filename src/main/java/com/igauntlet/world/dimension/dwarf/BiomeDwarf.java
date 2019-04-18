package com.igauntlet.world.dimension.dwarf;

import com.igauntlet.common.blocks.InfinityBlocks;
import com.igauntlet.world.dimension.IBiomeDecorator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class BiomeDwarf extends Biome {

    public BiomeDwarf() {
        super(new BiomeProperties("Dwarf").setHeightVariation(0.00F).setBaseHeight(2F).setRainDisabled().setTemperature(0.4F));
        this.topBlock = InfinityBlocks.dwarf_stone.getDefaultState();
        this.fillerBlock = InfinityBlocks.dwarf_dirt.getDefaultState();
        this.decorator = new IBiomeDecorator();
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.decorator.generateFalls = false;
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
    }

}
