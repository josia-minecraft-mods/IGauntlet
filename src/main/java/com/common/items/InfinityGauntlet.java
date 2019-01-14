package com.common.items;

import com.Main;
import com.client.gui.GuiGauntlet;
import com.init.ModBlocks;
import com.util.MSource;
import com.common.entity.EntityLaser;
import com.init.ModItems;
import com.tabs.InfinityTabs;
import com.util.IHasModel;
import com.util.ModConfig;
import com.util.handlers.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class InfinityGauntlet extends Item implements IHasModel {

    private static final String TPMOB = "8285731f-56a3-4636-95b6-d9b02c55b9f7";

    public InfinityGauntlet(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);

        ModItems.ITEMS.add(this);
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return TextFormatting.BOLD + "Infinity Gauntlet";
    }


    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }


    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public Item setFull3D() {
        return super.setFull3D();
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (player.world.isRemote) {
            player.playSound(SoundsHandler.GAUNTLET_HUM, 1, 1);
        } else {
            if (!entity.getUniqueID().equals(TPMOB)) {
                entity.setDead();
            } else {
                entity.setPosition(player.posX, 0, player.posZ);
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if ((entityLiving instanceof EntityPlayer)) {
            EntityPlayer playerIn = (EntityPlayer) entityLiving;

            if (playerIn.isSneaking() && ModConfig.Gauntlet.Snap)
                playerIn.world.playSound(null, playerIn.getPosition(), SoundsHandler.SNAP, SoundCategory.HOSTILE, 1F, 1F);

            if (!playerIn.world.isRemote && playerIn.isSneaking() && ModConfig.Gauntlet.Snap) {
                for (Entity targetentity : playerIn.world.getEntitiesWithinAABB(EntityLiving.class, playerIn.getEntityBoundingBox().grow(25.0D, 25.0D, 25.0D))) {
                    Block blk = ModBlocks.ASH_PILE;
                    BlockPos pos0 = new BlockPos(targetentity.posX, targetentity.posY, targetentity.posZ);
                    IBlockState state0 = blk.getDefaultState();
                    entityLiving.world.setBlockState(pos0, state0);
                    targetentity.attackEntityFrom(DamageSource.OUT_OF_WORLD, 100);
                }
                EnumHand hand = playerIn.getActiveHand();
                playerIn.setActiveHand(hand);
            }
        }
        return super.onEntitySwing(entityLiving, stack);
    }


    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);

        if (!worldIn.isRemote && (isSelected)) {
            EntityPlayerMP player = (EntityPlayerMP) entityIn;
            if (stack.getItem() == ModItems.INFINITY_GAUNTLET) {
                if (player.getActivePotionEffect(MobEffects.INSTANT_HEALTH) == null) {
                    player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 50, 3));
                }
            }
        }
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }


    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        EntityPlayer entityplayer = (EntityPlayer) entityLiving;

        if (!worldIn.isRemote && !entityplayer.isSneaking()) {
            Vec3d v3 = entityplayer.getLook(1);

            EntityLaser laser = new EntityLaser(worldIn, entityplayer, 8, new MSource("ray"), new Vec3d(1, 0, 5));
            laser.shoot(v3.x, v3.y, v3.z, 1.5F, (float) (1 - worldIn.getDifficulty().getDifficultyId() * 1));
            worldIn.spawnEntity(laser);
        }
        if (worldIn.isRemote && !entityplayer.isSneaking()) {
            entityplayer.playSound(SoundsHandler.GAUNTLET_HUM, 1, 1);
        }
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
