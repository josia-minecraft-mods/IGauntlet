package com.jmteam.igauntlet.common.damage;

import net.minecraft.util.DamageSource;

public class IDamageSource {

    public static DamageSource LASER = new ISource("laser", false);
    public static DamageSource SNAP = new ISource("snap", false);
    public static DamageSource HOLDING = new ISource("hold_stone", false);
    public static DamageSource POWER = new ISource("powerstone", false);
}
