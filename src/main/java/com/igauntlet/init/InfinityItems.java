package com.igauntlet.init;

import com.igauntlet.common.items.ItemBase;
import com.igauntlet.common.items.ItemInfinityGauntlet;
import com.igauntlet.common.items.ItemMixTape;
import com.igauntlet.common.items.clothing.ItemEyeOfAgamotto;
import com.igauntlet.common.items.stones.*;
import com.igauntlet.common.items.tools.ItemDwarfhammer;
import com.igauntlet.util.handlers.SoundsHandler;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

;

public class InfinityItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //Items
    public static final Item INFINITY_GAUNTLET = new ItemInfinityGauntlet("infinity_gauntlet");
    public static final Item MIXTAPE = new ItemMixTape("awesome_mix", SoundsHandler.AWESOMEMIX);

    //Ingots
    public static final Item URU_INGOT = new ItemBase("uru_ingot", true);

    //Stones
    public static final Item MINDSTONE = new ItemMindStone("mind_stone");
    public static final Item REALITYSTONE = new ItemRealityStone("reality_stone");
    public static final Item TIMESTONE = new ItemTimeStone("time_stone");
    public static final Item SPACESTONE = new ItemSpaceStone("space_stone");
    public static final Item POWERSTONE = new ItemPowerStone("power_stone");
    public static final Item SOULSTONE = new ItemSoulStone("soul_stone");

    //Tools
    public static final Item DWARFHAMMER = new ItemDwarfhammer("dwarf_hammer");

    //Clothing
    public static final Item  NECKLACE = new ItemEyeOfAgamotto("eye_agamotto");

}





