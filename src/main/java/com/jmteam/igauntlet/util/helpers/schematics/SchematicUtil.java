package com.jmteam.igauntlet.util.helpers.schematics;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.util.helpers.WorldUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

import java.io.File;
import java.io.FileWriter;
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
        if (schematic != null) {
            File file = new File("mods/edgeofdarkness/schematics/" + schematic.name + ".json");

            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write(Infinity.GSON.toJson(schematic));
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public static Schematic mapSchematic(World world, BlockPos blockpos1, BlockPos blockpos2, String name, boolean ignoreAir) {
        Schematic schematic = new Schematic();

        BlockPos blockpos3 = new BlockPos(Math.min(blockpos1.getX(), blockpos2.getX()), Math.min(blockpos1.getY(), blockpos2.getY()), Math.min(blockpos1.getZ(), blockpos2.getZ()));
        BlockPos blockpos4 = new BlockPos(Math.max(blockpos1.getX(), blockpos2.getX()), Math.max(blockpos1.getY(), blockpos2.getY()), Math.max(blockpos1.getZ(), blockpos2.getZ()));

        int size = (int) (blockpos3.getDistance(blockpos4.getX(), blockpos4.getY(), blockpos4.getZ())) / 4;
        BlockPos ref = blockpos3.add(size, 0, size);

        for (int y = blockpos3.getY(); y <= blockpos4.getY(); ++y) {
            for (int x = blockpos3.getX(); x <= blockpos4.getX(); ++x) {
                for (int z = blockpos3.getZ(); z <= blockpos4.getZ(); ++z) {
                    BlockPos pos = new BlockPos(x, y, z);
                    IBlockState state = world.getBlockState(pos);

                    if (state.getBlock() == Blocks.AIR && ignoreAir) continue;

                    BlockPos reference = ref.subtract(new Vec3i(pos.getX(), pos.getY(), pos.getZ()));
                    SchematicBlockInfo blockInfo = new SchematicBlockInfo(state, world.getTileEntity(pos), reference);
                    schematic.addBlockInfo(blockInfo);
                }
            }
        }

        schematic.setName(name);

        return schematic;
    }

    public static void generateSchematic(Schematic schem, BlockPos pos, World world, boolean ignoreAir) {

        if (schem == null) return;

        List<SchematicChunk> chunks = new ArrayList<>();
        SchematicChunk chunk = new SchematicChunk();
        BlockUpdater updater = new BlockUpdater(pos, world.provider.getDimension());

        for (SchematicBlockInfo blockInfo : schem.getBlockInfos()) {
            if (schem.getBlockInfos().size() <= 512) {
                BlockPos r = blockInfo.getReference();
                BlockPos pPos = pos.add(-r.getX(), -r.getY(), -r.getZ());
                updater.addPosToUpdate(pPos);

                WorldUtil.setBlock(world, pPos, blockInfo.getBlockState(), 2);

                if (blockInfo.isTileEntity) {
                    blockInfo.getTileTag().setInteger("x", pPos.getX());
                    blockInfo.getTileTag().setInteger("y", pPos.getY());
                    blockInfo.getTileTag().setInteger("z", pPos.getZ());
                    TileEntity tileEntity = world.getTileEntity(pPos);
                    tileEntity.readFromNBT(blockInfo.getTileTag());
                    tileEntity.markDirty();
                }
            } else {
                if (chunk.getSchematicBlocks().size() == 1) {
                    chunk.setDimID(world.provider.getDimension());
                    chunk.setIgnoreAir(ignoreAir);
                    chunk.setQueuePos(pos);
                }


                chunk.addBlock(blockInfo);
                updater.addPosToUpdate(pos, blockInfo.getReference());

                if (chunk.schematicBlocks.size() % 512 == 0 && chunk.schematicBlocks.size() != 0) {
                    queue.add(chunk);
                    chunk = new SchematicChunk();
                }
            }
        }

        if (chunk.schematicBlocks.size() > 0) {
            queue.add(chunk);
        }

        if (chunks.size() > 0) {
            queue.addAll(chunks);
        }

        updater.queueUpdater();
    }

    public static void pasteBlocks(SchematicChunk chunk, World world, boolean ignoreAir) {

        for (SchematicBlockInfo blockInfo : chunk.getSchematicBlocks()) {
            BlockPos r = blockInfo.getReference();
            BlockPos pPos = chunk.getQueuePos().add(-r.getX(), -r.getY(), -r.getZ());
            IBlockState state = blockInfo.getBlockState();

            if (state == null || (state.getBlock() == Blocks.AIR && ignoreAir) || state.getBlock() == world.getBlockState(pPos).getBlock())
                continue;
            setAndSetup(blockInfo, pPos, world);
        }
    }

    public static void setAndSetup(SchematicBlockInfo blockInfo, BlockPos pPos, World world) {
        WorldUtil.setBlock(world, pPos, blockInfo.getBlockState(), 2);

        if (blockInfo.isTileEntity) {
            TileEntity tileEntity = world.getTileEntity(pPos);
            try {
                blockInfo.compound = JsonToNBT.getTagFromJson(blockInfo.nbtTag);
            } catch (NBTException e) {
                e.printStackTrace();
            }

            blockInfo.getTileTag().setInteger("x", pPos.getX());
            blockInfo.getTileTag().setInteger("y", pPos.getY());
            blockInfo.getTileTag().setInteger("z", pPos.getZ());
            tileEntity.readFromNBT(blockInfo.getTileTag());
            tileEntity.markDirty();
            world.markAndNotifyBlock(pPos, world.getChunk(pPos), world.getBlockState(pPos), world.getBlockState(pPos), 1);
        }
    }
}
