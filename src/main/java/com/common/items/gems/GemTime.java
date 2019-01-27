package com.common.items.gems;

import com.common.blocks.AshPile;
import com.common.tileentity.TileAshPile;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class GemTime {

    public static void ReviveAsh(RayTraceResult result, World worldIn, EntityPlayer player) {
        if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
            IBlockState block = worldIn.getBlockState(result.getBlockPos());

            if (block.getBlock() instanceof AshPile) {
                BlockPos pos0 = result.getBlockPos();
                Block blk = Blocks.AIR;
                IBlockState state0 = blk.getDefaultState();
                SummonCreature(worldIn, player, result, pos0);
                worldIn.setBlockState(pos0, state0);
            }
        }
    }

    public static void SummonCreature(World worldIn, EntityPlayer player, RayTraceResult result, BlockPos pos) {
        TileEntity ash_te = worldIn.getTileEntity(pos);
        if (ash_te != null && ash_te instanceof TileAshPile) {
            EntityCreeper creeper = new EntityCreeper(worldIn);
            creeper.setPosition(result.getBlockPos().getX(), result.getBlockPos().getY(), result.getBlockPos().getZ());
            worldIn.spawnEntity(creeper);
        }
    }


}
