package com.jmteam.igauntlet.network.packets.server;

import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.init.InfinityDamageSources;
import com.jmteam.igauntlet.common.init.InfinitySounds;
import com.jmteam.igauntlet.util.gauntlet.GauntletHelper;
import com.jmteam.igauntlet.util.gauntlet.GemHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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
                    World world = player.getEntityWorld();
                    IInfinityCap capability = CapabilityInfinity.get(player);
                    SnapType type = player.isCrouching() ? SnapType.REVIVE : SnapType.SNAP;

                    // TODO Config
                    if (capability.canSnap()) {
                        switch (type) {

                            case SNAP:
                                // TODO add config value
                                List<LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, player.getBoundingBox().grow(20));
                                GauntletHelper.filterSnapList(player, entities);
                                int snapped = 0;


                                if (entities.size() > 1) {
                                    player.getServerWorld().playSound(null, player.getPosition(), InfinitySounds.SNAP.get(), SoundCategory.PLAYERS, 1,1);

                                    for (int x = 0; x < entities.size(); x++) {
                                        LivingEntity entity = entities.get(x);

                                        if (!entity.isInvulnerable() && (x % 2 == 1 && x != 0) && entity.isAlive()) {
                                            snapped++;

                                            // Prevent setting Entities removed when they shouldn't be
                                            if (GauntletHelper.filterSnap(entity, true)) {
                                                entity.removed = true;
                                            }

                                            entity.attackEntityFrom(InfinityDamageSources.SNAP, entity.getHealth());

                                            player.getServerWorld().spawnParticle(ParticleTypes.LARGE_SMOKE, entity.getPosX(), entity.getPosY() + 0.5, entity.getPosZ(), 50, 0.5, 0.5, 0.5, 0.1);
                                            GemHelper.createAshPile(world, entity.getPosition(), entity);
                                        }
                                    }

                                    // Infinity War (I don't feel so good) easter egg sound
                                    if(world.rand.nextInt(15) == 5) {
                                        player.getServerWorld().playSound(null, player.getPosition(), InfinitySounds.IDONTFEELGOOD.get(), SoundCategory.PLAYERS, 1,1);
                                    }

                                    capability.setSnapTimeout(System.currentTimeMillis());
                                    capability.sync();

                                    player.sendStatusMessage(new StringTextComponent(I18n.format("msg.snapped.amount" + (snapped > 1 ? ".multiple" : "")).replaceAll("&a", String.valueOf(snapped))), true);
                                } else {
                                    player.sendStatusMessage(new TranslationTextComponent("msg.snap.notenough"), true);
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
