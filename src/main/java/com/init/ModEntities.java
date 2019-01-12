package com.init;

import com.Main;
import com.common.entity.EntityLaser;
import com.util.Reference;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    public static int id = 0;

    public static void registerEntities(){
        registerProjectiles(EntityLaser.class,"ray");
    }

    public static void registerProjectiles(Class<? extends EntityThrowable> entityClass, String name) {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, name), entityClass, name, ++id, Main.instance, 64, 5, true);
    }
}
