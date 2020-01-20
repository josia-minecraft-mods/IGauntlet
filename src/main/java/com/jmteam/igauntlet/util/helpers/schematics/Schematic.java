package com.jmteam.igauntlet.util.helpers.schematics;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Schematic {

    public List<SchematicBlockInfo> blockInfos = new ArrayList<>();
    public String name = "";

    public Schematic() {
    }

    public void addBlockInfo(SchematicBlockInfo info) {
        blockInfos.add(info);
    }

    public List<SchematicBlockInfo> getBlockInfos() {
        return blockInfos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void generate(World world, BlockPos pos, boolean ignoreAir) {
        SchematicUtil.generateSchematic(this, pos, world, ignoreAir);
    }
}
