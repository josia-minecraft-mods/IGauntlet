package com.jmteam.igauntlet.world;

import com.jmteam.igauntlet.util.InfinityConfig;
import com.jmteam.igauntlet.world.dimension.dwarf.WorldProviderDwarf;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class InfinityDimensions {

    public static int DWARFID;
    public static DimensionType DWARF;

    public static void registerDimensions() {
        if (InfinityConfig.Dimensions.CustomDimensionID) {
            DWARFID = InfinityConfig.Dimensions.DwarfDimensionID;
        } else {
            DWARFID = DimensionManager.getNextFreeDimId();
        }

        DWARF = DimensionType.register("Dwarf", "_dwarf", DWARFID, WorldProviderDwarf.class, false);
        DimensionManager.registerDimension(DWARFID, DWARF);
    }
}
