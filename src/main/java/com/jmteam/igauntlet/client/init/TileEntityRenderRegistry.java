package com.jmteam.igauntlet.client.init;

import com.jmteam.igauntlet.IGauntlet;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.function.Function;

public class TileEntityRenderRegistry {

    public static void init() {
        IGauntlet.LOGGER.info("Registering TileEntity Renders");
    }

    public static <T extends TileEntity> void bindTileEntityRenderer(RegistryObject<TileEntityType<T>> type, Function<? super TileEntityRendererDispatcher, ? extends TileEntityRenderer<? super T>> rendererFactory) {
        ClientRegistry.bindTileEntityRenderer(type.get(), rendererFactory);
    }
}
