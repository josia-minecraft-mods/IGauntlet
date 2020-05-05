package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.common.entity.EntityLaser;
import com.jmteam.igauntlet.common.entity.EntityPortal;
import com.jmteam.igauntlet.common.entity.EntitySquirrelGirl;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class InfinityEntities {

    @SubscribeEvent
    public static void addEntities(RegistryEvent.Register<EntityEntry> e) {
        IForgeRegistry<EntityEntry> reg = e.getRegistry();
        reg.registerAll(EntityEntries.LASER, EntityEntries.PORTAL, EntityEntries.SQUIRREL_GIRL);
    }

    @GameRegistry.ObjectHolder(Infinity.MODID)
    public static class EntityEntries {
        public static final EntityEntry LASER = EntityEntryBuilder.create().entity(EntityLaser.class).id(new ResourceLocation(Infinity.MODID, "ray"), 0).name("ray").tracker(80, 3, true).build();
        public static final EntityEntry PORTAL = EntityEntryBuilder.create().entity(EntityPortal.class).id(new ResourceLocation(Infinity.MODID, "portal"), 1).name("portal").tracker(80, 3, false).build();
        public static final EntityEntry SQUIRREL_GIRL = EntityEntryBuilder.create().entity(EntitySquirrelGirl.class).id(new ResourceLocation(Infinity.MODID, "squirrelgirl"), 2).name("squirrel_girl").tracker(80, 3, true).build();
    }
}
