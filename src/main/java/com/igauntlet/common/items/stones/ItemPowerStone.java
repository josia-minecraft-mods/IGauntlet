package com.igauntlet.common.items.stones;

import com.igauntlet.Infinity;
import com.igauntlet.init.InfinityItems;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.helpers.IHasModel;
import net.minecraft.item.Item;
import java.util.concurrent.ThreadLocalRandom;

public class ItemPowerStone extends Item implements IHasModel {

    public ItemPowerStone(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);
        setMaxDamage(4500);

        InfinityItems.ITEMS.add(this);
    }

@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);


        EntityPlayer player = (EntityPlayer) entityIn;
         int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1)
             var min == 1
             var max == 7
             if randomNum == 1-6 {
                 target.setDead();
                 }
        else{
            PlayerHelper.sendMessageClient(player, "stones.power.spaired", true);
            }
             
     @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
