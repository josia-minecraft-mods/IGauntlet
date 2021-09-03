package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.common.entity.EntityGauntlet;
import com.jmteam.igauntlet.util.registry.EntityBuilder;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;

public class InfinityEntities {

    public static final RegistryObject<EntityType<EntityGauntlet>> GAUNTLET = create("gauntlet").entity(EntityGauntlet::new, EntityClassification.MISC).size(1f, 1.7f).build();

    public static EntityBuilder create(String name) {
        return EntityBuilder.create(name);
    }
}
