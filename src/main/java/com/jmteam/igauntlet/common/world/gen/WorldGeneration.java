package com.jmteam.igauntlet.common.world.gen;

import com.jmteam.igauntlet.common.init.InfinityBlocks;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import static com.jmteam.igauntlet.common.init.InfinityDimensions.DWARFID;


public class WorldGeneration implements IWorldGenerator {
    private final WorldGenerator uru_ore;
    private final WorldGenerator overworld_uru_ore;


    public WorldGeneration() {
        uru_ore = new WorldGenMinable(InfinityBlocks.uru_ore.getDefaultState(), 9, BlockMatcher.forBlock(Blocks.STONE));
        overworld_uru_ore = new WorldGenMinable(InfinityBlocks.overworld_uru_ore.getDefaultState(), 9, BlockMatcher.forBlock(Blocks.STONE));

    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == DWARFID) {
            runGenerator(uru_ore, world, random, chunkX, chunkZ, 20, 20, 60);
        }
        if (world.provider.getDimension() == 0) {
            runGenerator(overworld_uru_ore, world, random, chunkX, chunkZ, 1, 10, 15);
        }
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256)
            throw new IllegalArgumentException("Ore generated out of bounds");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chance; i++) {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);
            gen.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}
