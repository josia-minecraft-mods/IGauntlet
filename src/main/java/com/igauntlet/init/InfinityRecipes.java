package com.igauntlet.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InfinityRecipes {
    public static void init() {
        GameRegistry.addSmelting(InfinityBlocks.URU_ORE, new ItemStack(InfinityItems.URU_INGOT, 1), 1.5f);
    }
}
