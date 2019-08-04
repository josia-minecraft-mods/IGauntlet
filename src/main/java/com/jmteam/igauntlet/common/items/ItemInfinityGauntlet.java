package com.jmteam.igauntlet.common.items;

import com.jmteam.igauntlet.client.gui.GuiGauntlet;
import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.function.gems.*;
import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.tabs.InfinityTabs;
import com.jmteam.igauntlet.util.InfinityConfig;
import com.jmteam.igauntlet.util.helpers.GemHelper;
import com.jmteam.igauntlet.util.helpers.PlayerHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.jmteam.igauntlet.common.init.InfinityNbtKeys.CURRENTSTONE;


public class ItemInfinityGauntlet extends Item {

    public ItemInfinityGauntlet() {
        setMaxStackSize(1);
        setMaxDamage(4500);
        setCreativeTab(InfinityTabs.infinityTabs);
    }

    int NONE = 0;
    int REALITY = 1;
    int SPACE = 2;
    int SOUL = 3;
    int TIME = 4;
    int POWER = 5;
    int MIND = 6;

    boolean TimeOn = InfinityConfig.AllowedGems.TimeStone;
    boolean SpaceOn = InfinityConfig.AllowedGems.SpaceStone;
    boolean RealityOn = InfinityConfig.AllowedGems.RealityStone;
    boolean SoulOn = InfinityConfig.AllowedGems.SoulStone;
    boolean MindOn = InfinityConfig.AllowedGems.MindStone;
    boolean PowerOn = InfinityConfig.AllowedGems.PowerStone;

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
        ItemStack stack = playerIn.getActiveItemStack();

        int current = GemHelper.ActiveGem(playerIn);

        if (worldIn.isRemote && playerIn.getHeldItemOffhand().getItem() == InfinityItems.infinity_gauntlet) {
            OpenInfinityGui();
        }

        if (current == NONE) {
            PlayerHelper.sendMessageClient(playerIn, "gauntlet.selected.null", true);
        }

        if (playerIn.getHeldItemOffhand().getItem() != InfinityItems.infinity_gauntlet) {


            if (PowerOn && current == POWER) {
                GemPower.Laser(playerIn, worldIn, stack);
            }

            if (RealityOn && current == REALITY) {
                if (!playerIn.isSneaking()) {
                    //  GemReality.makeBubbles(playerIn);
                } else {
                    GemReality.QuickSand(playerIn);
                }
            }

            if (TimeOn && current == TIME && !worldIn.isRemote) {

                if (!playerIn.isSneaking()) {
                    if (!stack.getTagCompound().getBoolean("freeze")) {
                        stack.getTagCompound().setBoolean("freeze", true);
                    } else {
                        stack.getTagCompound().setBoolean("freeze", false);
                    }
                    GemTime.FreezeTime(playerIn, worldIn, stack.getTagCompound().getBoolean("freeze"), InfinityConfig.Gauntlet.TimeStone.FreezeRange);
                } /*else {
                    for (BlockPos b : BlockPos.getAllInBox((int) playerIn.posX - 20, (int) playerIn.posY - 10, (int) playerIn.posZ - 20, (int) playerIn.posX + 20, (int) playerIn.posY + 10, (int) playerIn.posZ + 20)) {
                        GemTime.ReviveAsh(b, worldIn);
                    }
                }
                */
            }

            if (worldIn.isRemote && SpaceOn && current == SPACE) {
                GemSpace.OpenSpaceGui(playerIn);
            }
        }


        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        int current = GemHelper.ActiveGem(stack);
        EntityPlayer player = (EntityPlayer) attacker;
        return true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        IInfinityCap cap = CapabilityInfinity.get(playerIn);
        int current = GemHelper.ActiveGem(stack);

        if (!cap.isPosessing() && current == SOUL && SoulOn && !playerIn.isSneaking()) {
            if (target instanceof EntityLiving) GemSoul.startPosessing(playerIn, (EntityLiving) target, cap);
        }

        if (current == MIND && MindOn) {

            if (playerIn.isSneaking()) {
                if (!(target instanceof EntityPlayer))
                    GemMind.Attack(playerIn, (EntityLiving) target);
            } else {
                // GemMind.MakeFriendly(target);
            }
        }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    @SideOnly(Side.CLIENT)
    public void OpenInfinityGui() {
        Minecraft.getMinecraft().displayGuiScreen(new GuiGauntlet());
    }


    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        EntityPlayer entityplayer = (EntityPlayer) entityLiving;


        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if ((entityLiving instanceof EntityPlayer)) {

            EntityPlayer playerIn = (EntityPlayer) entityLiving;
            playerIn.setActiveHand(EnumHand.MAIN_HAND);
            int extend = InfinityConfig.Gauntlet.ExtensionRange;
            NBTTagCompound nbt = stack.getTagCompound();
            int current = GemHelper.ActiveGem(playerIn);


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
        EntityPlayer player = (EntityPlayer) entityIn;


        if (!worldIn.isRemote) {
            if ((isSelected)) {
                if (stack.getItem() == InfinityItems.infinity_gauntlet) {
                    if (player.getActivePotionEffect(MobEffects.INSTANT_HEALTH) == null) {
                        player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 50, 3, true, false));
                    }

                    if (stack.getItem() instanceof ItemInfinityGauntlet) {
                        if (!worldIn.isRemote) {
                            if (nbt == null) {
                                nbt = new NBTTagCompound();
                                nbt.setInteger(CURRENTSTONE, 0);
                                stack.setTagCompound(nbt);
                            }

                        }

                    }
                }
            }
        }
    }
}
