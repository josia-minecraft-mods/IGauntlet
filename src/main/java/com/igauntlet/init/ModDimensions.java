package com.igauntlet.init;


import com.igauntlet.config.ModConfig;
import com.igauntlet.world.dimension.dwarf.WorldProviderDwarf;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {
    public static final DimensionType DWARF = DimensionType.register("Dwarf", "_dwarf", ModConfig.Dimensions.DwarfDimensionID, WorldProviderDwarf.class, false);

    public static void registerDimensions() {
        DimensionManager.registerDimension(ModConfig.Dimensions.DwarfDimensionID, DWARF);
    }
}
