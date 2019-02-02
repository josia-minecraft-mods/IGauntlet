package com.igauntlet.util.handlers;

import com.igauntlet.common.tileentity.TileAshPile;
import com.igauntlet.common.tileentity.TileTesseract;
import com.igauntlet.util.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileRegister {

    public static final void PreInit() {
        GameRegistry.registerTileEntity(TileAshPile.class, Reference.MODID + ":tile_ash_pile");
        GameRegistry.registerTileEntity(TileTesseract.class, Reference.MODID + ":tile_tesseract");
    }
}
