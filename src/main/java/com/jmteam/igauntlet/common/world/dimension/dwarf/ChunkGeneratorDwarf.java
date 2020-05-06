package com.jmteam.igauntlet.common.world.dimension.dwarf;

import com.jmteam.igauntlet.common.init.InfinityBlocks;
import com.jmteam.igauntlet.common.world.dimension.ChunkGeneratorBase;
import net.minecraft.world.World;

public class ChunkGeneratorDwarf extends ChunkGeneratorBase {

    public ChunkGeneratorDwarf(World worldIn, long seed) {
        super(worldIn, seed);
        filler_block = InfinityBlocks.dwarf_cobble.getDefaultState();
        surface_block = InfinityBlocks.dwarf_stone.getDefaultState();
    }
}
