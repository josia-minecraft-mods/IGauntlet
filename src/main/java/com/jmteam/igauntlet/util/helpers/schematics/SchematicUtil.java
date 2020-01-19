package com.jmteam.igauntlet.util.helpers.schematics;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josia50
 */

@Mod.EventBusSubscriber
public class SchematicUtil {
    public static List<Schematic> schematics = new ArrayList<>();

    public static List<SchematicChunk> queue = new ArrayList<>();

    public static void addSchematic(Schematic schematic) {
        schematics.add(schematic);
    }

    public static Schematic mapSchematic(World world, BlockPos blockPos1, BlockPos blockPos2, BlockPos standingPos, String name) {
        Schematic schematic = new Schematic();


        for (BlockPos pos : BlockPos.getAllInBox(blockPos1, blockPos2)) {

            IBlockState state = world.getBlockState(pos);
            BlockPos reference = standingPos.subtract(new Vec3i(pos.getX(), pos.getY(), pos.getZ()));
            SchematicBlockInfo blockInfo = new SchematicBlockInfo(state, world.getTileEntity(pos), reference);

            schematic.addBlockInfo(blockInfo);
        }
        schematic.setName(name);


        return schematic;
    }

    public static void generateSchematic(Schematic schem, BlockPos pos, World world) {

        if (schem == null) return;

        SchematicChunk chunk = new SchematicChunk();

        for (SchematicBlockInfo blockInfo : schem.getBlockInfos()) {
            if (schem.getBlockInfos().size() == 1) {
                BlockPos r = blockInfo.getReference();
                BlockPos pPos = pos.add(r.getX(), -r.getY(), r.getZ());

                world.setBlockState(pPos, blockInfo.getBlockState());

                if (blockInfo.isTileEntity) {
                    blockInfo.tileTag.setInteger("x", pPos.getX());
                    blockInfo.tileTag.setInteger("y", pPos.getY());
                    blockInfo.tileTag.setInteger("z", pPos.getZ());
                    TileEntity tileEntity = world.getTileEntity(pPos);
                    tileEntity.readFromNBT(blockInfo.tileTag);
                    tileEntity.markDirty();
                }
                world.markAndNotifyBlock(pPos, world.getChunk(pPos), world.getBlockState(pos), world.getBlockState(pPos), 1);
            } else {
                if (!(chunk.getSchematicBlocks().size() == 1)) {
                    chunk.setDimID(world.provider.getDimension());
                    chunk.setQueuePos(pos);
                }

                chunk.addBlock(blockInfo);

                if (chunk.schematicBlocks.size() % 256 == 0) {
                    queue.add(chunk);
                    chunk = new SchematicChunk();
                }
            }
        }
        if (chunk.schematicBlocks.size() > 0) {
            queue.add(chunk);
        }
    }

    @SubscribeEvent
    public static void handleSchematicQueue(TickEvent.WorldTickEvent event) {
        if (event.side.isServer()) {
            World world = event.world;

            if (queue.size() > 0) {
                SchematicChunk chunk = queue.get(0);

                if (world.provider.getDimension() != chunk.dimID) return;


                if (!chunk.isDone()) {
                    for (SchematicBlockInfo blockInfo : chunk.getSchematicBlocks()) {

                        BlockPos r = blockInfo.getReference();
                        BlockPos pPos = chunk.getQueuePos().add(r.getX(), -r.getY(), r.getZ());
                        world.setBlockState(pPos, blockInfo.getBlockState());

                        if (blockInfo.isTileEntity) {
                            blockInfo.tileTag.setInteger("x", pPos.getX());
                            blockInfo.tileTag.setInteger("y", pPos.getY());
                            blockInfo.tileTag.setInteger("z", pPos.getZ());
                            TileEntity tileEntity = world.getTileEntity(pPos);
                            tileEntity.readFromNBT(blockInfo.tileTag);
                            tileEntity.markDirty();
                        }
                        world.markAndNotifyBlock(pPos, world.getChunk(pPos), world.getBlockState(pPos), world.getBlockState(pPos), 1);
                    }
                    chunk.setDone(true);
                }

                if (chunk.isDone())
                    queue.remove(0);
            }
        }
    }
}
