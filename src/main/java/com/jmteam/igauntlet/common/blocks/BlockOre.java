package com.jmteam.igauntlet.common.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

public class BlockOre extends InfinityBlock {

    public Supplier<Item> itemSupplier;
    public int amount;
    public int difference;

    public BlockOre(Material material, Supplier<Item> itemSupplier, int amount, int difference) {
        super(Block.Properties.create(material).harvestLevel(0).hardnessAndResistance(5.0f).harvestTool(ToolType.PICKAXE));
        this.itemSupplier = itemSupplier;
        this.amount = amount;
        this.difference = difference;
    }

    // TODO Use loottable?
    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        int quantity = amount + worldIn.rand.nextInt(difference);

        if (!player.isCreative()) {
            spawnAsEntity(worldIn, pos, new ItemStack(itemSupplier.get(), quantity));
        }
    }
}
