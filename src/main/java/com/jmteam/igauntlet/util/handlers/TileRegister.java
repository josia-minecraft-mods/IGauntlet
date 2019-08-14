package com.jmteam.igauntlet.util.handlers;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.common.tileentity.TileAshPile;
import com.jmteam.igauntlet.common.tileentity.TileQuickSand;
import com.jmteam.igauntlet.common.tileentity.TileTesseract;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileRegister {

    public static final void PreInit() {
        registerTileEntity(TileAshPile.class, "ash_pile");
        registerTileEntity(TileTesseract.class, "tesseract");
        registerTileEntity(TileQuickSand.class, "quicksand");
    }

    private static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
        GameRegistry.registerTileEntity(clazz, Infinity.MODID + ":" + name);
    }
}
