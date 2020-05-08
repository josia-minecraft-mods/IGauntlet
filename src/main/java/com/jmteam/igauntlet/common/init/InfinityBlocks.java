package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.common.blocks.*;
import com.jmteam.igauntlet.common.blocks.stoneholders.BlockOrb;
import com.jmteam.igauntlet.common.blocks.stoneholders.BlockTesseract;
import com.jmteam.igauntlet.common.item.InfinityItemBlock;
import com.jmteam.igauntlet.common.tileentity.TileEntityAshPile;
import com.jmteam.igauntlet.common.tileentity.TileEntityQuickSand;
import com.jmteam.igauntlet.common.tileentity.TileEntityTesseract;
import com.jmteam.igauntlet.util.registry.IHaveItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class InfinityBlocks {

    public static List<Block> BLOCKS = new ArrayList<>();


    public static Block ash_pile = addTileEntity(new BlockAshPile(Material.SAND), "ash_pile", TileEntityAshPile::new).setGroup(InfinityGroups.infinityTab);
    public static Block uru_ore = registerBlock(new BlockUruOre(Material.ROCK), "uru_ore").setGroup(InfinityGroups.infinityTab);
    public static Block tesseract = addTileEntity(new BlockTesseract(TileEntityTesseract::new), "tesseract").setGroup(InfinityGroups.infinityTab);
    public static Block dwarf_stone = registerBlock(new BlockDwarfStone(Material.ORGANIC), "dwarf_stone").setGroup(InfinityGroups.infinityTab);
    public static Block dwarf_dirt = registerBlock(new BlockDwarfDirt(Material.ORGANIC), "dwarf_dirt").setGroup(InfinityGroups.infinityTab);
    public static Block dwarf_cobble = registerBlock(new BlockDwarfCobble(Material.ORGANIC), "dwarf_cobble").setGroup(InfinityGroups.infinityTab);
    public static Block dwarf_wood = registerBlock(new BlockDwarfWood(Material.WOOD), "dwarf_wood").setGroup(InfinityGroups.infinityTab);
    public static Block forge = registerBlock(new BlockForge(Material.ANVIL), "forge").setGroup(InfinityGroups.infinityTab);
    public static Block manipulator = registerBlock(new BlockManipulator(Material.IRON), "manipulator").setGroup(InfinityGroups.infinityTab);
    public static Block quick_sand = addTileEntity(new BlockQuickSand(Material.SAND),"quick_sand", TileEntityQuickSand::new).setGroup(InfinityGroups.infinityTab);
    public static Block power_orb = registerBlock(new BlockOrb(Material.ROCK), "power_orb").setGroup(InfinityGroups.infinityTab);

    public static <T extends Block> T registerBlock(T block, String name) {
        block.setRegistryName(name);
        BLOCKS.add(block);

        if (block instanceof IHaveItem) {
            Item itemBlock = new BlockItem(block, new Item.Properties().maxStackSize(((IHaveItem) block).getMaxSize())).setRegistryName(name);
            ((IHaveItem) block).setItem((BlockItem) itemBlock);
            InfinityItems.ITEMS.add(itemBlock);
        }

        return block;
    }

    public static InfinityBlock registerBlock(InfinityBlock block, String name) {
        block.setRegistryName(name);
        BLOCKS.add(block);

        InfinityItemBlock itemBlock = (InfinityItemBlock) new InfinityItemBlock(block, block.getMaxSize()).setRegistryName(name);
        block.setBlockItem(itemBlock);
        InfinityItems.ITEMS.add(itemBlock);

        return block;
    }

    private static InfinityBlock addTileEntity(BlockInfinityTileEntityBase base, String name) {
        InfinityTileEntities.addType(name, base.tileEntitySupplier, base);
        registerBlock(base, name);

        return base;
    }

    private static <T extends Block> T addTileEntity(T base, String name, Supplier<TileEntity> tileEntitySupplier) {
        InfinityTileEntities.addType(name, tileEntitySupplier, base);
        registerBlock(base, name);

        return base;
    }
}
