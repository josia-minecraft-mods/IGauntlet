package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.common.damage.ISource;
import net.minecraft.util.DamageSource;

public class InfinityDamageSources {


    public static DamageSource LASER = new ISource("laser", false);
    public static DamageSource SNAP = new ISource("snap", false);
    public static DamageSource HOLDING = new ISource("hold_stone", false);
    public static DamageSource POWER = new ISource("powerstone", false);
}
