package com.common.items;

import com.Infinity;
import com.client.gui.GuiGauntlet;
import com.common.items.gems.GemPower;
import com.common.items.gems.GemSpace;
import com.init.ModItems;
import com.tabs.InfinityTabs;
import com.util.IHasModel;
import com.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
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
import net.minecraft.util.text.TextComponentString;
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

        ModItems.ITEMS.add(this);
    }


    int REALITY = 1;
    int SPACE = 2;
    int SOUL = 3;
    int TIME = 4;
    int POWER = 5;
    int MIND = 6;


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


            ItemStack stack = player.getHeldItem(hand);
            int current = stack.getTagCompound().getInteger("currentstone");


        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }


    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        ItemStack stack = playerIn.getActiveItemStack();

        int current = stack.getTagCompound().getInteger("currentstone");

        if (worldIn.isRemote && playerIn.getHeldItemOffhand().getItem() == ModItems.INFINITY_GAUNTLET) {
            OpenInfinityGui();
        }

        if(worldIn.isRemote && current == SPACE) {
            GemSpace.OpenSpaceGui(playerIn);
        }



        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void OpenInfinityGui() {
        Minecraft.getMinecraft().displayGuiScreen(new GuiGauntlet());
    }


    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        EntityPlayer entityplayer = (EntityPlayer) entityLiving;
        int current = stack.getTagCompound().getInteger("currentstone");

        if (current == POWER) {
            GemPower.Laser(entityplayer, worldIn, stack);
        }

        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if ((entityLiving instanceof EntityPlayer)) {

            EntityPlayer playerIn = (EntityPlayer) entityLiving;
            playerIn.setActiveHand(EnumHand.MAIN_HAND);
            int extend = ModConfig.Gauntlet.ExtensionRange;
            NBTTagCompound nbt = stack.getTagCompound();
            int current = nbt.getInteger("currentstone");

            if (current == POWER) {
                GemPower.Snap(playerIn, stack, extend);
            }

            if (current == TIME) {
                if (playerIn.world.isRemote && playerIn.isSneaking()) {
                    playerIn.sendMessage(new TextComponentString("This is the first initialization, No function has been added to this yet!"));
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


        if (!worldIn.isRemote && (isSelected)) {
            EntityPlayerMP player = (EntityPlayerMP) entityIn;
            if (stack.getItem() == ModItems.INFINITY_GAUNTLET) {
                if (player.getActivePotionEffect(MobEffects.INSTANT_HEALTH) == null) {
                    player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 50, 3));
                }
            }
        }

        NBTTagCompound nbt = stack.getTagCompound();

        if (!worldIn.isRemote && stack.getItem() instanceof InfinityGauntlet) {
            if (nbt == null) {
                nbt = new NBTTagCompound();
                stack.setTagCompound(nbt);
            }
        }
    }


    @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
