package com.jmteam.igauntlet.common.world.dimension;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

import java.util.Random;

public class IBiomeDecorator extends BiomeDecorator {

    public IBiomeDecorator() {
    }

    @Override
    public void decorate(World worldIn, Random random, Biome biome, BlockPos pos) {
    }

    @Override
    protected void genDecorations(Biome biomeIn, World worldIn, Random random) {
    }
}
