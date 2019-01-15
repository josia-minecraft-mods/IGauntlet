package com.init;

import com.common.items.mindstone;
import com.common.items.realitystone;
import com.common.items.timestone;
import com.common.items.spacestone;
import com.common.items.powerstone;
import com.common.items.soulstone;
import com.common.items.InfinityGauntlet;
import net.minecraft.item.*;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //Items
    public static final Item INFINITY_GAUNTLET = new InfinityGauntlet("infinity_gauntlet");

    public static final Item MINDSTONE = new mindstone("mind_stone");

    public static final Item REALITYSTONE = new realitystone("reality_stone");

    public static final Item TIMESTONE = new timestone("time_stone");

    public static final Item SPACESTONE = new spacestone("space_stone");

    public static final Item POWERSTONE = new powerstone("power_stone");

    public static final Item SOULSTONE = new soulstone("soul_stone");

}





