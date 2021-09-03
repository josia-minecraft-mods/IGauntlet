package com.jmteam.igauntlet.common.capability;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.network.NetworkHandler;
import com.jmteam.igauntlet.network.packets.client.PacketSyncCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CapabilityInfinity implements IInfinityCap {

    private PlayerEntity player;
    private long snap_cooldown = 0;

    public CapabilityInfinity() {
    }

    public CapabilityInfinity(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public void update() {

    }

    @Override
    public void sync() {
        if (!player.level.isClientSide()) {
            NetworkHandler.sendTo((ServerPlayerEntity) player, new PacketSyncCapability(player.getUUID(), serializeNBT()));
        }
    }

    @Override
    public void setSnapTimeout(long snapTimeout) {
        snap_cooldown = snapTimeout;
    }

    @Override
    public long getSnapTimeout() {
        return snap_cooldown;
    }

    @Override
    public boolean canSnap() {
        // TODO COnfig
        return (System.currentTimeMillis() - snap_cooldown) > 2500;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong("snap_cooldown", snap_cooldown);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        snap_cooldown = nbt.getLong("snap_cooldown");
    }

    @Mod.EventBusSubscriber(modid = IGauntlet.MODID)
    public static class Events {

        @SubscribeEvent
        public static void attach(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof PlayerEntity)
                event.addCapability(new ResourceLocation(IGauntlet.MODID, "igauntlet_capability"), new InfinityCapProvider((PlayerEntity) event.getObject()));
        }

        @SubscribeEvent
        public static void update(LivingEvent.LivingUpdateEvent event) {
            LivingEntity entity = event.getEntityLiving();

           if(entity instanceof PlayerEntity) {

               IInfinityCap cap = get((PlayerEntity) entity);

               cap.update();
           }
        }

        @SubscribeEvent
        public static void onPlayerClone(PlayerEvent.Clone event) {
            Capability.IStorage<IInfinityCap> storage = InfinityCapProvider.CAPABILITY.getStorage();

            IInfinityCap oldCap = get(event.getOriginal());
            IInfinityCap newCap = get(event.getPlayer());

            CompoundNBT nbt = (CompoundNBT) storage.writeNBT(InfinityCapProvider.CAPABILITY, oldCap, null);
            storage.readNBT(InfinityCapProvider.CAPABILITY, newCap, null, nbt);
            get(event.getPlayer()).sync();
        }

        @SubscribeEvent
        public static void playerTracking(PlayerEvent.StartTracking event) {
            get(event.getPlayer()).sync();
        }

        @SubscribeEvent
        public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {

        }
    }


    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        get(event.getPlayer()).sync();
    }


    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        get(event.getPlayer()).sync();
    }

    @SubscribeEvent
    public static void onDeathEvent(LivingDeathEvent e) {
        if (e.getEntityLiving() instanceof PlayerEntity){
            get((PlayerEntity) e.getEntityLiving()).sync();
        }
    }

    public static IInfinityCap get(PlayerEntity player) {
        return player.getCapability(InfinityCapProvider.CAPABILITY, null).orElse(InfinityCapProvider.capDummy);
    }
}
