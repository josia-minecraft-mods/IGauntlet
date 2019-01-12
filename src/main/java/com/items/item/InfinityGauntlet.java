package com.items.item;

import com.Main;
import com.google.common.base.Predicate;
import com.init.ModItems;
import com.tabs.InfinityTabs;
import com.util.IHasModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

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

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote && !playerIn.isSneaking()) {
            Vec3d v3 = playerIn.getLook(1);
            EntitySmallFireball smallfireball = new EntitySmallFireball(worldIn, playerIn.posX, playerIn.posY + playerIn.eyeHeight, playerIn.posZ, v3.x, v3.y, v3.z);
            smallfireball.shootingEntity = playerIn;
            worldIn.spawnEntity(smallfireball);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
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
        if (!player.world.isRemote) {
            Vec3d v3 = player.getLook(1);
            if (!entity.getUniqueID().equals(TPMOB)) {
                entity.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, v3.x, v3.y + 0.5D, v3.z, 0, 0.0D, 0.0D);
                entity.setDead();
            } else {
                entity.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, v3.x, v3.y + 0.5D, v3.z, 0, 0.0D, 0.0D);
                entity.setPosition(player.posX, 0, player.posZ);
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        EntityPlayerMP player = (EntityPlayerMP)entityIn;

        if (stack.getItem() == ModItems.INFINITY_GAUNTLET) {
            if (player.getActivePotionEffect(MobEffects.SPEED) == null) {
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 50, 3));
            }

            super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        }
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
