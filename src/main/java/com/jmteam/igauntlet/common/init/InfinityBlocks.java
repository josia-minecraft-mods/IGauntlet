package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.common.blocks.*;
import com.jmteam.igauntlet.common.blocks.stoneholders.BlockAether;
import com.jmteam.igauntlet.common.blocks.stoneholders.BlockOrb;
import com.jmteam.igauntlet.common.blocks.stoneholders.BlockTesseract;
import com.jmteam.igauntlet.common.tileentity.TileEntityAshPile;
import com.jmteam.igauntlet.common.tileentity.TileEntityQuickSand;
import com.jmteam.igauntlet.common.tileentity.TileEntityTesseract;
import com.jmteam.igauntlet.util.registry.InfinityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class InfinityBlocks {

    public static void init() {
    }

    public static RegistryObject<Block> ASH_PILE = addTileEntity(new BlockAshPile(Material.SAND), "ash_pile", TileEntityAshPile::new, InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> URU_ORE = registerBlock(new BlockOre(Material.ROCK, InfinityItems.URU_INGOT, 1,1), "uru_ore", InfinityGroups.INFINITY, true);
    public static RegistryObject<BlockTesseract> TESSERACT = addTileEntity(new BlockTesseract(TileEntityTesseract::new), "tesseract", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> DWARF_STONE = registerBlock(new InfinityBlock(Block.Properties.create(Material.ORGANIC)), "dwarf_stone", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> DWARF_DIRT = registerBlock(new InfinityBlock(Block.Properties.create(Material.PLANTS).hardnessAndResistance(15).harvestTool(ToolType.SHOVEL).harvestLevel(1).sound(SoundType.WET_GRASS)), "dwarf_dirt", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> DWARF_COBBLE = registerBlock(new InfinityBlock(Material.ORGANIC), "dwarf_cobble", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> DWARF_WOOD = registerBlock(new InfinityBlock(Material.WOOD), "dwarf_wood", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> FORGE = registerBlock(new BlockForge(Material.ANVIL), "forge", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> MANIPULATOR = registerBlock(new BlockManipulator(Material.IRON), "manipulator", InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> QUICK_SAND = addTileEntity(new BlockQuickSand(Material.SAND), "quick_sand", TileEntityQuickSand::new, InfinityGroups.INFINITY, true);
    public static RegistryObject<Block> POWER_ORB = registerBlock(new BlockOrb(Material.ROCK), "power_orb", InfinityGroups.INFINITY, true, 1);
    public static RegistryObject<Block> AETHER = registerBlock(new BlockAether(Material.BARRIER), "aether", InfinityGroups.INFINITY, true, 1);

    public static <T extends Block> RegistryObject<T> registerBlock(T block, String name, ItemGroup group, boolean item, int amount) {

        if (item) {
            Item itemBlock = new BlockItem(block, new Item.Properties().group(group).maxStackSize(amount));
            InfinityRegistry.ITEMS.register(name, () -> itemBlock);
        }

        return InfinityRegistry.BLOCKS.register(name, () -> block);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(T block, String name, ItemGroup group, boolean item) {
        return registerBlock(block, name, group, item, 64);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(T block, String name) {
        return registerBlock(block, name, null, false);
    }

    private static <T extends Block> RegistryObject<T> addTileEntity(T base, String name, Supplier<TileEntity> tileEntitySupplier, ItemGroup group, boolean item) {
        InfinityTileEntities.addType(name, tileEntitySupplier, base);
        return registerBlock(base, name, group, item);
    }

    private static <T extends BlockInfinityTileEntityBase> RegistryObject<T> addTileEntity(T base, String name) {
        InfinityTileEntities.addType(name, base.tileEntitySupplier, base);
        return registerBlock(base, name);
    }

    private static <T extends BlockInfinityTileEntityBase> RegistryObject<T> addTileEntity(T base, String name, ItemGroup group, boolean item) {
        InfinityTileEntities.addType(name, base.tileEntitySupplier, base);
        return registerBlock(base, name, group, item);
    }

    private static <T extends Block> RegistryObject<T> addTileEntity(T base, String name, Supplier<TileEntity> tileEntitySupplier) {
        return addTileEntity(base, name, tileEntitySupplier, null, false);
    }
}
