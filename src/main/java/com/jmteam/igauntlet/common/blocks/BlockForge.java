package com.jmteam.igauntlet.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockForge extends InfinityBlock {

    public BlockForge(Material material) {
        super(material);
    }

    @Override
    public void stepOn(World world, BlockPos blockPos, Entity entity) {
        if (!entity.fireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
            entity.hurt(DamageSource.HOT_FLOOR, 1.0F);
        }

        super.stepOn(world, blockPos, entity);
    }

}