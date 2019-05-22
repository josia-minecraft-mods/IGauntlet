package com.jmteam.igauntlet.common.capability;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.network.NetworkHandler;
import com.jmteam.igauntlet.network.packets.PacketCapSync;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class CapabilityInfinity implements IInfinityCap {

    private EntityPlayer player;
    private int possesionentity;
    private boolean isPosessing = false;


    public CapabilityInfinity() {}


    public CapabilityInfinity(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void update() {

    }

    @Override
    public void sync() {
        NetworkHandler.NETWORK.sendToAll(new PacketCapSync(player, serializeNBT()));
    }

    @Override
    public void setPosessing(boolean posessing) {
        this.isPosessing = posessing;
    }

    @Override
    public boolean isPosessing() {
        return isPosessing;
    }

    @Override
    public void setPosessedEntity(Entity posessedEntity) {
        this.possesionentity = posessedEntity.getEntityId();
    }

    @Override
    public Entity getPosessedEntity() {
        return player.world.getEntityByID(possesionentity);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("posses_entity", possesionentity);
        nbt.setBoolean("is_posessing", isPosessing);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        possesionentity = nbt.getInteger("posses_entity");
        isPosessing = nbt.getBoolean("is_posessing");
    }

    @Mod.EventBusSubscriber(modid = Infinity.MODID)
    public static class Events {

        @SubscribeEvent
        public static void attach(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof EntityPlayer)
                event.addCapability(new ResourceLocation(Infinity.MODID, "meta_cap"), new CapInfinityStorage.InfinityCapProvider((EntityPlayer) event.getObject()));
        }

        @SubscribeEvent
        public static void update(LivingEvent.LivingUpdateEvent event) {
            CapabilityInfinity cap = event.getEntityLiving().getCapability(CapInfinityStorage.CAPABILITY, null);
            if (cap != null)
                cap.update();
        }

        @SubscribeEvent
        public static void onPlayerClone(PlayerEvent.Clone event) {
            Capability.IStorage storage = CapInfinityStorage.CAPABILITY.getStorage();

            IInfinityCap oldCap = get(event.getOriginal());
            IInfinityCap newCap = get(event.getEntityPlayer());

            NBTTagCompound nbt = (NBTTagCompound) storage.writeNBT(CapInfinityStorage.CAPABILITY, oldCap, null);
            storage.readNBT(CapInfinityStorage.CAPABILITY, newCap, null, nbt);
            get(event.getEntityPlayer()).sync();
        }

        @SubscribeEvent
        public static void playerTracking(PlayerEvent.StartTracking event) {
            get(event.getEntityPlayer()).sync();
        }
    }


    @SubscribeEvent
    public static void onPlayerRespawn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event) {
        get(event.player).sync();
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event) {
        get(event.player).sync();
    }

    @SubscribeEvent
    public static void onDeathEvent(LivingDeathEvent e) {
        if (e.getEntityLiving() instanceof EntityPlayer) {
            get((EntityPlayer) e.getEntityLiving()).sync();
        }
    }

    @Nonnull
    public static IInfinityCap get(EntityPlayer player) {
        if (player.hasCapability(CapInfinityStorage.CAPABILITY, null)) {
            return player.getCapability(CapInfinityStorage.CAPABILITY, null);
        }
        throw new IllegalStateException("Missing Cap - IGauntlet");
    }
}
