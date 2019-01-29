package com.common.items.function.gems;

import com.common.blocks.AshPile;
import com.common.tileentity.TileAshPile;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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


}
