package com.common.items;

import com.Main;
import com.client.gui.GuiGauntlet;
import com.common.entity.EntityLaser;
import com.init.ModBlocks;
import com.init.ModItems;
import com.tabs.InfinityTabs;
import com.util.IHasModel;
import com.config.ModConfig;
import com.util.MSource;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;


public class InfinityGauntlet extends Item implements IHasModel {

    public InfinityGauntlet(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(InfinityTabs.infinityTabs);
        setMaxStackSize(1);
        setMaxDamage(4500);

        ModItems.ITEMS.add(this);
    }

    int MIND = 1; int TIME = 2; int SOUL = 3; int SPACE = 4; int REALITY = 5; int POWER = 6;


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
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);

        if (worldIn.isRemote && playerIn.getHeldItemOffhand().getItem() == ModItems.INFINITY_GAUNTLET) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiGauntlet());
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        EntityPlayer entityplayer = (EntityPlayer) entityLiving;
        NBTTagCompound nbt = stack.getTagCompound();
        int current = nbt.getInteger("currentstone");

        if (current == POWER) {
            if (!worldIn.isRemote && !entityplayer.isSneaking() && entityplayer.getHeldItemOffhand().getItem() != ModItems.INFINITY_GAUNTLET) {
                Vec3d v3 = entityplayer.getLook(1);

                EntityLaser laser = new EntityLaser(worldIn, entityplayer, 8, new MSource("ray"), new Vec3d(1, 0, 5));
                laser.shoot(v3.x, v3.y, v3.z, 1.5F, (float) (0 - worldIn.getDifficulty().getDifficultyId() * 0));
                worldIn.spawnEntity(laser);

                if (!entityplayer.capabilities.isCreativeMode) {
                    stack.setItemDamage(stack.getItemDamage() + 1);
                }
            }
            if (worldIn.isRemote && !entityplayer.isSneaking() && entityplayer.getHeldItemOffhand().getItem() != ModItems.INFINITY_GAUNTLET) {
                entityplayer.playSound(SoundsHandler.GAUNTLET_HUM, 1, 1);
            }
        }

        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if ((entityLiving instanceof EntityPlayer)) {

            EntityPlayer playerIn = (EntityPlayer) entityLiving;
            int extend = ModConfig.Gauntlet.ExtensionRange;
            NBTTagCompound nbt = stack.getTagCompound();
            int current = nbt.getInteger("currentstone");

            if (current == POWER) {
                if (playerIn.isSneaking() && ModConfig.Gauntlet.Snap) {
                    playerIn.world.playSound(null, playerIn.getPosition(), SoundsHandler.SNAP, SoundCategory.HOSTILE, 1F, 1F);
                }

                if (!playerIn.world.isRemote && playerIn.isSneaking() && ModConfig.Gauntlet.Snap) {
                    for (Entity targetentity : playerIn.world.getEntitiesWithinAABB(EntityLiving.class, playerIn.getEntityBoundingBox().grow(extend, extend, extend))) {
                        Block blk = ModBlocks.ASH_PILE;
                        BlockPos pos0 = new BlockPos(targetentity.posX, targetentity.posY, targetentity.posZ);
                        IBlockState state0 = blk.getDefaultState();
                        entityLiving.world.setBlockState(pos0, state0);
                        targetentity.attackEntityFrom(DamageSource.OUT_OF_WORLD, 100);
                        if (!playerIn.capabilities.isCreativeMode) {
                            stack.setItemDamage(stack.getItemDamage() + 1);
                        }
                    }
                }
            }

            EnumHand hand = playerIn.getActiveHand();
            playerIn.setActiveHand(hand);
        }
        return super.onEntitySwing(entityLiving, stack);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        return super.onLeftClickEntity(stack, player, entity);
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        NBTTagCompound nbt = stack.getTagCompound();

        if (!worldIn.isRemote && (isSelected)) {
            EntityPlayerMP player = (EntityPlayerMP) entityIn;

            if (stack.getItem() == ModItems.INFINITY_GAUNTLET) {
                if (player.getActivePotionEffect(MobEffects.INSTANT_HEALTH) == null) {
                    player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 50, 3));
                }
            }
        }

        if (!worldIn.isRemote) {
            if (nbt == null) {
                nbt = new NBTTagCompound();
            }

            if (GuiGauntlet.STONE == MIND) {
                nbt.setInteger("currentstone", MIND);
            }

            if (GuiGauntlet.STONE == TIME) {
                nbt.setInteger("currentstone", TIME);
            }

            if (GuiGauntlet.STONE == SOUL) {
                nbt.setInteger("currentstone", SOUL);
            }

            if (GuiGauntlet.STONE == SPACE) {
                nbt.setInteger("currentstone", SPACE);
            }

            if (GuiGauntlet.STONE == REALITY) {
                nbt.setInteger("currentstone", REALITY);
            }

            if (GuiGauntlet.STONE == POWER) {
                nbt.setInteger("currentstone", POWER);
            }
            stack.setTagCompound(nbt);
        }
    }


    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
