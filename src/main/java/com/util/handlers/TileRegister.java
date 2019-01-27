package com.util.handlers;

import com.common.tileentity.TileAshPile;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileRegister {

    public static final void PreInit() {
        GameRegistry.registerTileEntity(TileAshPile.class, "tile_ash_pile");
    }
}
