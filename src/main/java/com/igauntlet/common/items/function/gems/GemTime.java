package com.igauntlet.common.items.function.gems;

import com.igauntlet.common.blocks.AshPile;
import com.igauntlet.common.tileentity.TileAshPile;
import com.igauntlet.util.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GemTime {

    public static void ReviveAsh(BlockPos pos, World worldIn, EntityPlayer player) {
        IBlockState block = worldIn.getBlockState(pos);

        if (block.getBlock() instanceof AshPile) {
            Block blk = Blocks.AIR;
            IBlockState state0 = blk.getDefaultState();
            SummonCreature(worldIn, player, pos);
            worldIn.setBlockState(pos, state0);
        }
    }

    public static void SummonCreature(World worldIn, EntityPlayer player, BlockPos pos) {
        TileEntity ash_te = worldIn.getTileEntity(pos);
        if (ash_te != null && ash_te instanceof TileAshPile) {
            EntityCreeper creeper = new EntityCreeper(worldIn);
            creeper.setPosition(pos.getX(), pos.getY(), pos.getZ());
            worldIn.spawnEntity(creeper);
        }
    }






    public static void FreezeTime(EntityPlayer player, World world,int freeze, int extensionrange) {
        for (EntityLiving entity : player.world.getEntitiesWithinAABB(EntityLiving.class, player.getEntityBoundingBox().grow(extensionrange, extensionrange, extensionrange))) {
            if (freeze == 1) {
                ModUtil.Log("noAI?");
                entity.setNoAI(true);
            } else {
                ModUtil.Log("noA41I?");
                entity.setNoAI(false);
            }
        }
    }
}
