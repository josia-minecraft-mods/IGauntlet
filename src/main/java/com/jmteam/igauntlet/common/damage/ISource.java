package com.jmteam.igauntlet.common.damage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class ISource extends DamageSource {

    private final String message;
    private final boolean blockable;

    public ISource(String name, boolean blockable) {
        super(name);
        this.message = "damagesource.igauntlet." + name;
        this.blockable = blockable;
    }

    public ISource(String name) {
        this(name, false);
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entity) {
        return new TextComponentTranslation(message, entity.getName());
    }

    @Override
    public boolean isUnblockable() {
        return !blockable;
    }
}
