package com.util;

import com.util.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class MSource extends DamageSource {

    private String message;
    private boolean blockable;

    public MSource(String name, boolean blockable) {
        super(name);
        this.message = "damagesrc."+ Reference.MODID+"." + name;
        this.blockable = blockable;
    }

    public MSource(String name) {
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
