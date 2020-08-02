package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.common.tileentity.TileEntityAshPile;
import com.jmteam.igauntlet.common.tileentity.TileEntityQuickSand;
import com.jmteam.igauntlet.common.tileentity.TileEntityTesseract;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InfinityTileEntities {

    public static final void PreInit() {
        registerTileEntity(TileEntityAshPile.class, "ash_pile");
        registerTileEntity(TileEntityTesseract.class, "tesseract");
        registerTileEntity(TileEntityQuickSand.class, "quicksand");
    }

    @Deprecated
    private static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
        GameRegistry.registerTileEntity(clazz, Infinity.MODID + ":" + name);
    }
}
