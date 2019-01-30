package com.util.handlers;

import com.common.tileentity.TileAshPile;
import com.common.tileentity.TileTesseract;
import com.util.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileRegister {

    public static final void PreInit() {
        GameRegistry.registerTileEntity(TileAshPile.class, Reference.MODID + ":tile_ash_pile");
        GameRegistry.registerTileEntity(TileTesseract.class, Reference.MODID + ":tile_tesseract");
    }
}
