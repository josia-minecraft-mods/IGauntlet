package com.jmteam.igauntlet.network.packets.server;

import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.init.InfinityDamageSources;
import com.jmteam.igauntlet.common.init.InfinityMessages;
import com.jmteam.igauntlet.common.init.InfinitySounds;
import com.jmteam.igauntlet.util.gauntlet.GauntletHelper;
import com.jmteam.igauntlet.util.gauntlet.GemHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class PacketSnap {

    public PacketSnap() {

    }

    public static void encode(PacketSnap packet, PacketBuffer buf) {

    }

    public static PacketSnap decode(PacketBuffer buf) {
        return new PacketSnap();
    }

    public static class Handler {
        public static void handle(PacketSnap packet, Supplier<NetworkEvent.Context> ctx) {

            ctx.get().enqueueWork(() -> {

                ServerPlayerEntity player = ctx.get().getSender();

                if (player != null) {
                    World world = player.getLevel();
                    IInfinityCap capability = CapabilityInfinity.get(player);
                    SnapType type = player.isCrouching() ? SnapType.REVIVE : SnapType.SNAP;

                    // TODO Config
                    if (capability.canSnap()) {
                        switch (type) {

                            case SNAP:
                                // TODO add config value
                                List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(20));
                                GauntletHelper.filterSnapList(player, entities);
                                int snapped = 0;

                                if (entities.size() > 1) {
                                    player.getLevel().playSound(null, player.blockPosition(), InfinitySounds.SNAP.get(), SoundCategory.PLAYERS, 1, 1);

                                    for (int x = 0; x < entities.size(); x++) {
                                        LivingEntity entity = entities.get(x);

                                        if (!entity.isInvulnerable() && x % 2 == 1 && entity.isAlive()) {
                                            snapped++;

                                            // Prevent setting Entities removed when they shouldn't be
                                            if (GauntletHelper.filterSnap(entity, true)) {
                                                entity.remove();
                                            }

                                            entity.hurt(InfinityDamageSources.SNAP, entity.getHealth());
                                            player.getLevel().addParticle(ParticleTypes.LARGE_SMOKE, entity.getX(), entity.getY() + 0.5D, entity.getZ(), 50D, 0.5D, 0.5D);

                                            GemHelper.createAshPile(world, entity.blockPosition(), entity);
                                        }
                                    }

                                    // Infinity War (I don't feel so good) easter egg sound
                                    if (world.getRandom().nextInt(15) == 5) {
                                        player.getLevel().playSound(null, player.blockPosition(), InfinitySounds.IDONTFEELGOOD.get(), SoundCategory.PLAYERS, 1, 1);
                                    }

                                    capability.setSnapTimeout(System.currentTimeMillis());
                                    capability.sync();

                                    // TODO Check if this works on server???
                                    player.displayClientMessage(InfinityMessages.getComponent(snapped > 1 ? InfinityMessages.SNAP_AMOUNT_MULTIPLE : InfinityMessages.SNAP_AMOUNT, String.valueOf(snapped)), true);
                                } else {
                                    player.displayClientMessage(InfinityMessages.getComponent(InfinityMessages.SNAP_NOT_ENOUGH), true);
                                }

                                break;

                            case REVIVE:
                                // TODO add config value
                                GemHelper.reviveAshPiles(player, 20);
                                break;
                        }
                    }
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }

    public enum SnapType {
        SNAP, REVIVE
    }
}
