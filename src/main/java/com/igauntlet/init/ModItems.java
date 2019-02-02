package com.igauntlet.init;
;
import com.igauntlet.common.items.*;
import com.igauntlet.common.items.stones.*;
import com.igauntlet.common.items.tools.Dwarfhammer;
import net.minecraft.item.*;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //Items
    public static final Item INFINITY_GAUNTLET = new InfinityGauntlet("infinity_gauntlet");

    //Ingots
    public static final Item URU_INGOT = new ItemBase("uru_ingot", true);

    //Stones
    public static final Item MINDSTONE = new MindStone("mind_stone");
    public static final Item REALITYSTONE = new RealityStone("reality_stone");
    public static final Item TIMESTONE = new TimeStone("time_stone");
    public static final Item SPACESTONE = new SpaceStone("space_stone");
    public static final Item POWERSTONE = new PowerStone("power_stone");
    public static final Item SOULSTONE = new SoulStone("soul_stone");

    //Tools
    public static final Item DWARFHAMMER = new Dwarfhammer("dwarf_hammer");

}





