package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.common.blocks.*;
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

    public static Block ash_pile = RegisterBlock(new BlockAshPile(Material.SAND), "ash_pile", true);
    public static Block uru_ore = RegisterBlock(new BlockUruOre(Material.ROCK), "uru_ore", true);
    public static Block tesseract = RegisterBlock(new BlockTesseract(Material.CLAY), "tesseract", true);
    public static Block dwarf_stone = RegisterBlock(new BlockDwarfStone(Material.GROUND), "dwarf_stone", true);
    public static Block dwarf_dirt = RegisterBlock(new BlockDwarfDirt(Material.GRASS), "dwarf_dirt", true);
    public static Block dwarf_cobble = RegisterBlock(new BlockDwarfCobble(Material.GROUND), "dwarf_cobble", true);
    public static Block dwarf_wood = RegisterBlock(new BlockDwarfWood(Material.WOOD), "dwarf_wood", true);
    public static Block forge = RegisterBlock(new BlockForge(Material.ANVIL), "forge", true);
    public static Block manipulator = RegisterBlock(new BlockManipulator(Material.IRON), "manipulator", true);
    public static Block quick_sand = RegisterBlock(new BlockQuickSand(Material.SAND), "quick_sand", false);


    public static Block RegisterBlock(Block block, String name, boolean tab) {
        block.setRegistryName(name);
        block.setTranslationKey(name);
        InfinityBlocks.BLOCKS.add(block);

        if (block instanceof IHaveItem) {
            if (((IHaveItem) block).hasItem()) {
                ItemBlock itemBlock = (ItemBlock) new ItemBlock(block).setRegistryName(name);
                if (tab) block.setCreativeTab(InfinityTabs.infinityTabs);

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
