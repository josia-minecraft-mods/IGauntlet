package com.init;
;
import com.common.items.*;
import com.common.items.stones.*;
import com.common.items.tools.Dwarfhammer;
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

    public static final Item GREENSTONE = new ItemBase("stone_green", false);
    public static final Item REDSTONE = new ItemBase("stone_red", false);
    public static final Item BLUESTONE = new ItemBase("stone_blue", false);
    public static final Item ORANGESTONE = new ItemBase("stone_orange", false);
    public static final Item YELLOWSTONE = new ItemBase("stone_yellow", false);

    //Tools
    public static final Item DWARFHAMMER = new Dwarfhammer("dwarf_hammer");



}





