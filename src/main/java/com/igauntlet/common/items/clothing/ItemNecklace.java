package com.igauntlet.common.items.clothing;

import com.igauntlet.Infinity;
import com.igauntlet.client.models.ModelNecklace;
import com.igauntlet.init.InfinityItems;
import com.igauntlet.util.helpers.IHasModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemNecklace extends ItemArmor implements IHasModel {

    public ItemNecklace(String name) {
        super(ArmorMaterial.CHAIN, 0, EntityEquipmentSlot.CHEST);
        setRegistryName(name);
        setTranslationKey(name);

        InfinityItems.ITEMS.add(this);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return Infinity.MODID + ":textures/clothing/eye_agamotto.png";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        return new ModelNecklace(_default);
    }

    @Override
    public void registerModels() {
        Infinity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
