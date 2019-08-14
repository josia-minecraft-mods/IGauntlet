package com.jmteam.igauntlet.network.packets;

import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.damage.IDamageSource;
import com.jmteam.igauntlet.util.InfinityConfig;
import com.jmteam.igauntlet.util.handlers.SoundsHandler;
import com.jmteam.igauntlet.util.helpers.GauntletHelper;
import com.jmteam.igauntlet.util.helpers.PlayerHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PacketSnap implements IMessage {

    public static boolean snap;
    public static final List<EntityLiving> SNAPENTITY = new ArrayList<EntityLiving>();

    public PacketSnap() {
    }

    public PacketSnap(boolean snap) {
        this.snap = snap;
    }

    public void fromBytes(ByteBuf buf) {
        this.snap = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.snap);
    }

    public static class Handler implements IMessageHandler<PacketSnap, IMessage> {

        @Override
        public IMessage onMessage(PacketSnap message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    EntityPlayerMP player = ctx.getServerHandler().player;
                    Random r = new Random();
                    boolean CanSnap = InfinityConfig.Gauntlet.Snap;
                    IInfinityCap cap = CapabilityInfinity.get(player);
                    int extend = InfinityConfig.Gauntlet.ExtensionRange;
                    boolean tick = false;
                    List<EntityLiving> entities = player.world.getEntitiesWithinAABB(EntityLiving.class, player.getEntityBoundingBox().grow(extend, extend, extend));

                    if (CanSnap && cap.getSnapCooldown() <= 0) {
                        if (entities.size() > 1) {

                            for (int i = 0; i < (entities.size() / 2); i++) {
                                EntityLiving e = entities.get(i);
                                if (!e.getIsInvulnerable()) {
                                    e.attackEntityFrom(IDamageSource.SNAP, Float.POSITIVE_INFINITY);
                                    GauntletHelper.makeAshPile(e.world, e.getPosition(), e);
                                }
                            }

                            if (!tick) {
                                cap.setSnapCooldown(InfinityConfig.Gauntlet.SnapCooldown);
                                player.world.playSound(null, player.getPosition(), SoundsHandler.SNAP, SoundCategory.HOSTILE, 1F, 1F);
                                if (r.nextInt(10) == 3)
                                    player.world.playSound(null, player.getPosition(), SoundsHandler.IDONTFEELGOOD, SoundCategory.AMBIENT, 1F, 1F);
                                tick = !tick;
                            }
                        } else {
                            PlayerHelper.sendMessage(player, "gauntlet.snap.notenough", true);
                        }
                        entities.clear();
                    }
                }
            });
            return null;
        }
    }
}
