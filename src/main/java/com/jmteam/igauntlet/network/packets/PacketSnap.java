package com.jmteam.igauntlet.network.packets;

import com.jmteam.igauntlet.util.gauntlet.GemHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class PacketSnap {

    public SnapType type;

    public PacketSnap(SnapType type) {
        this.type = type;
    }

    public static void encode(PacketSnap packet, PacketBuffer buf) {
        buf.writeString(packet.type.name());
    }

    public static PacketSnap decode(PacketBuffer buf) {
        return new PacketSnap(SnapType.valueOf(buf.readString()));
    }


    public static class Handler {
        public static void handle(PacketSnap packet, Supplier<NetworkEvent.Context> ctx) {

            ctx.get().enqueueWork(() -> {

                ServerPlayerEntity player = ctx.get().getSender();

                if (player != null) {
                    World world = player.getEntityWorld();

                    switch (packet.type) {

                        case SNAP:
                            // TODO add config value
                            List<LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, player.getBoundingBox().grow(20));
                            entities.remove(player);

                            if (entities.size() > 1) {

                                for (int x = 0; x < entities.size(); x++) {
                                    LivingEntity entity = entities.get(x);

                                    if (!entity.isInvulnerable() && (x % 2 == 0 && x != 0)) {
                                        entity.attackEntityFrom(DamageSource.MAGIC, entity.getMaxHealth());
                                        GemHelper.createAshPile(world, entity.getPosition(), entity);
                                    }
                                }
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
            });
            ctx.get().setPacketHandled(true);
        }
    }

    public enum SnapType {
        SNAP, REVIVE
    }
}
