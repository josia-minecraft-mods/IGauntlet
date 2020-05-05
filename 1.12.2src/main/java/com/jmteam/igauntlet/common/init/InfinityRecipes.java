package com.jmteam.igauntlet.common.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InfinityRecipes {

    public static void init() {
        GameRegistry.addSmelting(InfinityBlocks.uru_ore, new ItemStack(InfinityItems.uru_ingot, 1), 1.5f);
    }
}
