package com.igauntlet.common.items.stones;

import com.igauntlet.Infinity;
import com.igauntlet.common.damage.IDamageSource;
import com.igauntlet.init.InfinityConfig;
import com.igauntlet.init.InfinityItems;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.helpers.EntityHelper;
import com.igauntlet.util.helpers.IHasModel;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTimeStone extends Item implements IHasModel {
    private int time = 0;

    public ItemTimeStone(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);
        setMaxDamage(4500);

        InfinityItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);

        if (isSelected && InfinityConfig.AllowedGems.TimeStone) {
            worldIn.getWorldInfo().setWorldTime(worldIn.getWorldTime() + InfinityConfig.Gauntlet.TimeStone.TimeSpeed);
            time++;
            if (time >= 200)
                EntityHelper.AttackBySource(entityIn, IDamageSource.HOLDING, 0.001F);
            if (time == 201)
                time = 0;
            System.out.println(time);
        }
    }
}
