package com.jmteam.igauntlet.client.init;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.common.init.InfinityTileEntities;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.function.Function;

public class TileEntityRenderRegistry {

    public static void init() {
        IGauntlet.LOGGER.info("Registering TileEntity Renders");

      //  bindTileEntityRenderer(TileEntityTest.class, TileEntityRendererTest::new);
    }


    public static <T extends TileEntity> void  bindTileEntityRenderer(Class clazz, Function<? super TileEntityRendererDispatcher, ? extends TileEntityRenderer<? super T>> rendererFactory) {
        ClientRegistry.bindTileEntityRenderer(InfinityTileEntities.TILE_ENTITY_TYPES.get(clazz), rendererFactory);
    }
}
