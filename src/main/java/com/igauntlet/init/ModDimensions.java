package com.igauntlet.init;


import com.igauntlet.world.dimension.dwarf.WorldProviderDwarf;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions
{
    public static  final DimensionType DWARF = DimensionType.register("Dwarf", "_dwarf", 2, WorldProviderDwarf.class, false);

    public static void registerDimensions() {
        DimensionManager.registerDimension(2, DWARF);
    }
}
