package com.jmteam.igauntlet.util.helpers.schematics;

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
}
