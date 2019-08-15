package com.jmteam.igauntlet.common.function.gems;

import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.util.handlers.SoundsHandler;
import com.jmteam.igauntlet.util.helpers.GauntletHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class GemPower {

    public static void Laser(EntityPlayer entityplayer, World worldIn, ItemStack stack) {
        if (!entityplayer.isSneaking() && entityplayer.getHeldItemOffhand().getItem() != InfinityItems.infinity_gauntlet) {
            entityplayer.playSound(SoundsHandler.GAUNTLET_HUM, 1, 1);
            GauntletHelper.ShootLaser(entityplayer, worldIn, Float.MAX_VALUE, 1, 0, 5);
        }
    }
}


