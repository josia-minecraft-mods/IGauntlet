package com.igauntlet.util.handlers;

import com.igauntlet.Infinity;
import com.igauntlet.common.tileentity.TileAshPile;
import com.igauntlet.common.tileentity.TileQuickSand;
import com.igauntlet.common.tileentity.TileTesseract;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileRegister {

    public static final void PreInit() {
        GameRegistry.registerTileEntity(TileAshPile.class, Infinity.MODID + ":tile_ash_pile");
        GameRegistry.registerTileEntity(TileTesseract.class, Infinity.MODID + ":tile_tesseract");
        GameRegistry.registerTileEntity(TileQuickSand.class, Infinity.MODID + ":tile_quicksand");
    }
}
