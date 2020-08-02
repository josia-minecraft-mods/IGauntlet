package com.jmteam.igauntlet.common.items;

import com.jmteam.igauntlet.client.gui.GuiGauntlet;
import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.function.gems.*;
import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.util.InfinityConfig;
import com.jmteam.igauntlet.util.helpers.GemHelper;
import com.jmteam.igauntlet.util.helpers.GemHelper.StoneType;
import com.jmteam.igauntlet.util.helpers.PlayerHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
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
        return TextFormatting.BOLD + I18n.format("item.infinity_gauntlet.name");
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
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
        StoneType current = GemHelper.ActiveGem(playerIn, handIn);

        // Open Stone Selection GUI
        if (worldIn.isRemote && playerIn.getHeldItemOffhand().getItem() == InfinityItems.infinity_gauntlet) {
            OpenInfinityGui();
        }

        if (playerIn.getHeldItemOffhand().getItem() != InfinityItems.infinity_gauntlet) {
            switch (current) {
                case NONE:
                    PlayerHelper.sendMessageClient(playerIn, "gauntlet.selected.null", true);
                    break;

                case POWER:
                    if (PowerOn) GemPower.Laser(playerIn, worldIn, stack);
                    break;

                case REALITY:
                    if (RealityOn) GemReality.QuickSand(playerIn);
                    break;

                case TIME:
                    if (TimeOn && !worldIn.isRemote) {
                        if (stack.getTagCompound().hasKey("freeze")) {
                            stack.getTagCompound().setBoolean("freeze", !stack.getTagCompound().getBoolean("freeze"));
                            stack.getTagCompound().setBoolean("freeze", !stack.getTagCompound().getBoolean("freeze"));
                            // TODO Add arraylist with all frozen , so they can be unfrozen later
                            GemTime.FreezeTime(playerIn, worldIn, stack.getTagCompound().getBoolean("freeze"), InfinityConfig.Gauntlet.TimeStone.FreezeRange);
                        } else {
                            stack.getTagCompound().setBoolean("freeze", true);
                        }
                    }
                    break;

                case SPACE:
                    GemSpace.OpenSpaceGui(playerIn);
                    break;
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        IInfinityCap cap = CapabilityInfinity.get(playerIn);
        StoneType current = GemHelper.ActiveGem(stack);

        if (playerIn.world.isRemote) return false;

        switch (current) {
            case SOUL:
                if (!cap.isPosessing() && SoulOn && !playerIn.isSneaking()) {
                    if (target instanceof EntityLiving) GemSoul.startPosessing(playerIn, (EntityLiving) target, cap);
                }
                break;

            case MIND:
                if (playerIn.isSneaking()) {
                    if (!(target instanceof EntityPlayer) && MindOn) GemMind.attack(playerIn, (EntityLiving) target);
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
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
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
                                nbt.setString(CURRENTSTONE, String.valueOf(StoneType.NONE));
                                stack.setTagCompound(nbt);
                            }
                        }
                    }
                }
            }
        }
    }
}
