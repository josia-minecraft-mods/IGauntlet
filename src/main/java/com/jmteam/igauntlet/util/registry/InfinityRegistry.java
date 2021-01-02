package com.jmteam.igauntlet.util.registry;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.common.init.InfinityBlocks;
import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.common.init.InfinitySounds;
import com.jmteam.igauntlet.common.item.InfinityItemBlock;
import com.jmteam.igauntlet.util.helpers.ReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InfinityRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IGauntlet.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IGauntlet.MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, IGauntlet.MODID);

    public static void register() {
        InfinityBlocks.init(); // Either this or we initialize a with // new InfinityBlocks(); either way would work
        InfinityItems.init();
        InfinitySounds.init();

        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    /* Use reflection to set Creative Tab after initialize */
    public static Item setCreativeTab(Item i, ItemGroup group) {
        if (i != null) {

            if (i instanceof InfinityItemBlock) {
                ((InfinityItemBlock) i).setGroup(group);
                return i;
            }

            if (i instanceof BlockItem) {
                BlockItem item = (BlockItem) i;
                ReflectionHelper.setValuePrivateDeclaredField("group", ReflectionHelper.getClassFromSuperClasses(item, Item.class), item, group);
            }
        }

        return i;
    }
}
