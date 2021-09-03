package com.jmteam.igauntlet.common.damage;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ISource extends DamageSource {

    private final String message;

    public ISource(String name) {
        super(name);
        this.message = "damagesource.igauntlet." + name;
    }

    @Override
    public ITextComponent getLocalizedDeathMessage(LivingEntity entity) {
        return new TranslationTextComponent(message, entity.getName());
    }


}
