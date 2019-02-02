package com.igauntlet.util.handlers.helpers;

import net.minecraft.world.World;

import java.util.Random;

public interface IChunkGenerator {

    void gen(World world, Random rand, int x, int z);

}
