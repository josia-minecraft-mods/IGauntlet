package com.jmteam.igauntlet.util.helpers.schematics;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockUpdater {

    public List<BlockPos> posList = new ArrayList<>();
    public BlockPos queuePos;
    public int dimension;

    public BlockUpdater(BlockPos queuePos, int dimension) {
        this.queuePos = queuePos;
        this.dimension = dimension;
    }

    public void addPosToUpdate(BlockPos queuePos, BlockPos referencePos) {
        posList.add(queuePos.add(-referencePos.getX(), -referencePos.getY(), -referencePos.getZ()));
    }

    public void addPosToUpdate(BlockPos pos) {
        posList.add(pos);
    }

    public void queueUpdater() {
        if (posList.size() > 0) BlockUpdaterHandler.tempUpdaters.put(queuePos.toLong(), this);
    }

    public void complete() {
        if (!(posList.size() <= 512)) {
          BlockUpdater updater = new BlockUpdater(queuePos, dimension);

            for (BlockPos pos : posList) {

                updater.addPosToUpdate(pos);

                if (updater.posList.size() % 512 == 0 && updater.posList.size() != 0) {
                    BlockUpdaterHandler.blockUpdaterList.add(updater);
                    updater = new BlockUpdater(queuePos, dimension);
                }
            }

            if (updater.posList.size() > 0) {
                BlockUpdaterHandler.blockUpdaterList.add(updater);
            }

        } else {
            BlockUpdaterHandler.blockUpdaterList.add(this);
        }
    }

    public List<BlockPos> getPosList() {
        return posList;
    }

    public static class BlockUpdaterHandler {
        public static Map<Long, BlockUpdater> tempUpdaters = new HashMap<>();
        public static List<BlockUpdater> blockUpdaterList = new ArrayList<>();


        public static void handle(World world) {
            if (blockUpdaterList.size() > 0) {
               BlockUpdater blockUpdater = blockUpdaterList.get(0);
                tempUpdaters.remove(blockUpdater.queuePos.toLong());

                if (blockUpdater != null) {
                    World updateWorld = world.getMinecraftServer().getWorld(blockUpdater.dimension);

                    for (BlockPos pos : blockUpdater.posList) {
                        updateWorld.markAndNotifyBlock(pos, updateWorld.getChunk(pos), updateWorld.getBlockState(pos), updateWorld.getBlockState(pos), 1);
                    }

                    blockUpdaterList.remove(0);
                } else {
                    blockUpdaterList.remove(0);
                }
            }
        }

    }
}
