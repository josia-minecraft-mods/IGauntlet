package com.igauntlet.common.items.function.gems;

import com.igauntlet.common.damage.IDamageSource;
import com.igauntlet.common.entity.EntityLaser;
import com.igauntlet.common.tileentity.TileAshPile;
import com.igauntlet.config.ModConfig;
import com.igauntlet.init.ModBlocks;
import com.igauntlet.init.ModItems;
import com.igauntlet.util.handlers.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class GemPower {

    // Not in use , Current is a Packet (MessageSnap)
    public static void Snap(EntityPlayer playerIn, ItemStack stack, int extend) {
        NBTTagCompound nbt = stack.getTagCompound();

        boolean CanSnap = ModConfig.Gauntlet.Snap;
        if (playerIn.isSneaking() && CanSnap) {
            playerIn.world.playSound(null, playerIn.getPosition(), SoundsHandler.SNAP, SoundCategory.HOSTILE, 1F, 1F);
        }

        if (playerIn.isSneaking() && CanSnap) {
            for (Entity targetentity : playerIn.world.getEntitiesWithinAABB(EntityLiving.class, playerIn.getEntityBoundingBox().grow(extend, extend, extend))) {
                int entity = targetentity.getEntityId();

                Block blk = ModBlocks.ASH_PILE;
                BlockPos pos0 = new BlockPos(targetentity.posX, targetentity.posY, targetentity.posZ);
                IBlockState state0 = blk.getDefaultState();
                targetentity.world.setBlockState(pos0, state0);
                WriteAsh(pos0, playerIn.world, entity);
                targetentity.attackEntityFrom(IDamageSource.SNAP, 1000);

                if (!playerIn.capabilities.isCreativeMode) {
                    stack.setItemDamage(stack.getItemDamage() + 1);
                }
            }
            nbt.setBoolean("snapnow", false);
            stack.writeToNBT(nbt);
        }
    }

    public static void Laser(EntityPlayer entityplayer, World worldIn, ItemStack stack) {
        if (!entityplayer.isSneaking() && entityplayer.getHeldItemOffhand().getItem() != ModItems.INFINITY_GAUNTLET) {
            Vec3d v3 = entityplayer.getLook(1);
            EntityLaser laser = new EntityLaser(worldIn, entityplayer, 100, IDamageSource.LASER, new Vec3d(1, 0, 5));
            laser.shoot(v3.x, v3.y, v3.z, 1.5F, (float) 0);
            worldIn.spawnEntity(laser);

        }
        if (worldIn.isRemote && !entityplayer.isSneaking() && entityplayer.getHeldItemOffhand().getItem() != ModItems.INFINITY_GAUNTLET) {
            entityplayer.playSound(SoundsHandler.GAUNTLET_HUM, 1, 1);
        }
    }

    public static void WriteAsh(BlockPos pos, World world, int entity) {
        TileEntity ash_te = world.getTileEntity(pos);
        if (ash_te != null && ash_te instanceof TileAshPile) {
            TileAshPile ash_te_f = (TileAshPile) ash_te;
            ash_te_f.setEntity(entity);
        }
    }
}


