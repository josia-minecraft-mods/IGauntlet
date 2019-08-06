package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.common.blocks.*;
import com.jmteam.igauntlet.common.blocks.stoneholders.BlockTesseract;
import com.jmteam.igauntlet.tabs.InfinityTabs;
import com.jmteam.igauntlet.util.helpers.IHaveItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class InfinityBlocks {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static Block ash_pile = RegisterBlock(new BlockAshPile(Material.SAND), "ash_pile").setCreativeTab(InfinityTabs.infinityTabs);
    public static Block uru_ore = RegisterBlock(new BlockUruOre(Material.ROCK), "uru_ore").setCreativeTab(InfinityTabs.infinityTabs);
    public static Block tesseract = RegisterBlock(new BlockTesseract(Material.CLAY), "tesseract").setCreativeTab(InfinityTabs.infinityTabs);
    public static Block dwarf_stone = RegisterBlock(new BlockDwarfStone(Material.GROUND), "dwarf_stone").setCreativeTab(InfinityTabs.infinityTabs);
    public static Block dwarf_dirt = RegisterBlock(new BlockDwarfDirt(Material.GRASS), "dwarf_dirt").setCreativeTab(InfinityTabs.infinityTabs);
    public static Block dwarf_cobble = RegisterBlock(new BlockDwarfCobble(Material.GROUND), "dwarf_cobble").setCreativeTab(InfinityTabs.infinityTabs);
    public static Block dwarf_wood = RegisterBlock(new BlockDwarfWood(Material.WOOD), "dwarf_wood").setCreativeTab(InfinityTabs.infinityTabs);
    public static Block forge = RegisterBlock(new BlockForge(Material.ANVIL), "forge").setCreativeTab(InfinityTabs.infinityTabs);
    public static Block manipulator = RegisterBlock(new BlockManipulator(Material.IRON), "manipulator").setCreativeTab(InfinityTabs.infinityTabs);
    public static Block quick_sand = RegisterBlock(new BlockQuickSand(Material.SAND), "quick_sand").setCreativeTab(InfinityTabs.infinityTabs);


    public static Block RegisterBlock(Block block, String name) {
        block.setRegistryName(name);
        block.setTranslationKey(name);
        InfinityBlocks.BLOCKS.add(block);

        if (block instanceof IHaveItem) {
            if (((IHaveItem) block).hasItem()) {
                ItemBlock itemBlock = (ItemBlock) new ItemBlock(block).setRegistryName(name);

                InfinityItems.ITEMS.add(itemBlock);
            }
        }
        return block;
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(InfinityBlocks.BLOCKS.toArray(new Block[0]));
    }
}
