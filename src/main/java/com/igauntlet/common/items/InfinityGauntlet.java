package com.igauntlet.common.items;

import com.igauntlet.Infinity;
import com.igauntlet.client.gui.GuiGauntlet;
import com.igauntlet.common.function.gems.*;
import com.igauntlet.init.InfinityConfig;
import com.igauntlet.init.InfinityItems;
import com.igauntlet.tabs.InfinityTabs;
import com.igauntlet.util.helpers.GemHelper;
import com.igauntlet.util.helpers.IHasModel;
import com.igauntlet.util.helpers.PlayerHelper;
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


public class InfinityGauntlet extends Item implements IHasModel {

    public InfinityGauntlet(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setMaxDamage(4500);
        setCreativeTab(InfinityTabs.infinityTabs);

        InfinityItems.ITEMS.add(this);
    }

    int NONE = 0;
    int REALITY = 1;
    int SPACE = 2;
    int SOUL = 3;
    int TIME = 4;
    int POWER = 5;
    int MIND = 6;

    int snapcooldown = 0;
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

        if (worldIn.isRemote && playerIn.getHeldItemOffhand().getItem() == InfinityItems.INFINITY_GAUNTLET) {
            OpenInfinityGui();
        }

        if(current == NONE) {
            PlayerHelper.sendMessageClient(playerIn, "gauntlet.selected.null", true);
        }

        if (playerIn.getHeldItemOffhand().getItem() != InfinityItems.INFINITY_GAUNTLET) {


            if (PowerOn && current == POWER) {
                GemPower.Laser(playerIn, worldIn, stack);
            }

            if (RealityOn && current == REALITY) {
                if (!playerIn.isSneaking()) {
                    GemReality.ShootFireBall(playerIn);
                }
            }

            if (TimeOn && current == TIME && !worldIn.isRemote) {

                if (stack.getTagCompound().getInteger("freeze") == 0) {
                    stack.getTagCompound().setInteger("freeze", 1);
                } else {
                    stack.getTagCompound().setInteger("freeze", 0);
                }
                GemTime.FreezeTime(playerIn, worldIn, stack.getTagCompound().getInteger("freeze"), 50);
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

        if (current == MIND && MindOn) {
            if (player.isSneaking()) {
                GemMind.Attack((EntityPlayer) attacker, (EntityLiving) target);
            } else {
                GemMind.MakeFriendly(target);
            }
        }
        return super.hitEntity(stack, target, attacker);
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
                if (stack.getItem() == InfinityItems.INFINITY_GAUNTLET) {
                    if (player.getActivePotionEffect(MobEffects.INSTANT_HEALTH) == null) {
                        player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 50, 3));
                    }

                    if (stack.getItem() instanceof InfinityGauntlet) {
                        if (!worldIn.isRemote) {
                            if (nbt == null) {
                                nbt = new NBTTagCompound();
                                nbt.setInteger("currentstone", 0);
                                stack.setTagCompound(nbt);
                            }

                        }

                    }
                }

            }
        }
    }


    @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
