package com.jmteam.igauntlet.util.handlers;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.common.tileentity.TileAshPile;
import com.jmteam.igauntlet.common.tileentity.TileQuickSand;
import com.jmteam.igauntlet.common.tileentity.TileTesseract;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileRegister {

    public static final void PreInit() {
        GameRegistry.registerTileEntity(TileAshPile.class, Infinity.MODID + ":tile_ash_pile");
        GameRegistry.registerTileEntity(TileTesseract.class, Infinity.MODID + ":tile_tesseract");
        GameRegistry.registerTileEntity(TileQuickSand.class, Infinity.MODID + ":tile_quicksand");
    }
}
