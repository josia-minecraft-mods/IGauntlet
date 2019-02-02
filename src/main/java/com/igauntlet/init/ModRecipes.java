package com.igauntlet.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
    public static void init()
    {
        GameRegistry.addSmelting(ModBlocks.URU_ORE, new ItemStack(ModItems.URU_INGOT, 1),1.5f);
    }
}
