package com.jmteam.igauntlet.common.function.gems;

import com.jmteam.igauntlet.common.items.InfinityItems;
import com.jmteam.igauntlet.common.tileentity.TileAshPile;
import com.jmteam.igauntlet.util.handlers.SoundsHandler;
import com.jmteam.igauntlet.util.helpers.GauntletHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class GemPower {

    public static void Laser(EntityPlayer entityplayer, World worldIn, ItemStack stack) {
        if (worldIn.isRemote && !entityplayer.isSneaking() && entityplayer.getHeldItemOffhand().getItem() != InfinityItems.infinity_gauntlet) {
            entityplayer.playSound(SoundsHandler.GAUNTLET_HUM, 1, 1);
        }
        GauntletHelper.ShootLaser(entityplayer, worldIn, 100, 1,0 ,5);
    }

    public static void WriteAsh(BlockPos pos, World world, Entity entity) {
        TileEntity ash_te = world.getTileEntity(pos);
        if (ash_te != null && ash_te instanceof TileAshPile) {
            TileAshPile ash_te_f = (TileAshPile) ash_te;
            ash_te_f.setEntity(entity);
        }
    }
}


