package com.proxy;

import com.common.items.InfinityGauntlet;
import com.common.items.mindstone;
import com.common.items.realitystone;
import com.common.items.timestone;
import com.common.items.spacestone;
import com.common.items.powerstone;
import com.common.items.soulstone;
import com.init.ModEntities;
import com.util.handlers.RenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {

    public static void registerRenders() {
    }


    @Override
    public void preInit(FMLPreInitializationEvent event) {
        RenderHandler.registerEntityRenders();
        ModEntities.registerEntities();
    }

    @Override
    public void init(FMLInitializationEvent e) {

    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }
}
