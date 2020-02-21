package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.util.InfinityConfig;
import com.jmteam.igauntlet.world.dimension.dwarf.WorldProviderDwarf;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class InfinityDimensions {

    public static int DWARFID = InfinityConfig.Dimensions.DwarfDimensionID;
    public static DimensionType DWARF;

    public static void registerDimensions() {
        if (!InfinityConfig.Dimensions.CustomDimensionID) {
            DWARFID = DimensionManager.getNextFreeDimId();
        }

        DWARF = DimensionType.register("Dwarf", "_dwarf", DWARFID, WorldProviderDwarf.class, false);
        DimensionManager.registerDimension(DWARFID, DWARF);
    }
}
