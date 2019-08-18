package com.jmteam.igauntlet.network.packets;

import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.damage.IDamageSource;
import com.jmteam.igauntlet.common.function.gems.GemTime;
import com.jmteam.igauntlet.common.init.InfinityBlocks;
import com.jmteam.igauntlet.common.init.InfinitySounds;
import com.jmteam.igauntlet.util.InfinityConfig;
import com.jmteam.igauntlet.util.helpers.EntityHelper;
import com.jmteam.igauntlet.util.helpers.GauntletHelper;
import com.jmteam.igauntlet.util.helpers.PlayerHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.List;
import java.util.Random;

public class PacketSnap implements IMessage {

    public boolean reverse = false;

    public PacketSnap() {
    }

    public PacketSnap(boolean reverse) {
        this.reverse = reverse;
    }

    public void fromBytes(ByteBuf buf) {
        this.reverse = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.reverse);
    }

    public static class Handler implements IMessageHandler<PacketSnap, IMessage> {

        @Override
        public IMessage onMessage(PacketSnap message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    EntityPlayerMP player = ctx.getServerHandler().player;
                    BlockPos p = player.getPosition();
                    Random rn = new Random();
                    boolean CanSnap = InfinityConfig.Gauntlet.Snap;
                    IInfinityCap cap = CapabilityInfinity.get(player);
                    int extend = InfinityConfig.Gauntlet.ExtensionRange;
                    boolean tick = false;
                    List<EntityLiving> entities = player.world.getEntitiesWithinAABB(EntityLiving.class, player.getEntityBoundingBox().grow(extend, extend, extend));

                    if (CanSnap && cap.getSnapCooldown() <= 0) {

                        if (message.reverse) {
                            boolean canRevive = InfinityConfig.Gauntlet.ReviveAsh;
                            int r = InfinityConfig.Gauntlet.AshReviveRange;

                            if (canRevive) {
                                String status = "snap.revivedashfail";
                                for (BlockPos bp : BlockPos.getAllInBox(p.getX() - r, p.getY() - r, p.getZ() - r, p.getX() + r, p.getY() + r, p.getZ() + r)) {
                                    if (player.world.getBlockState(bp).getBlock() == InfinityBlocks.ash_pile) {
                                        GemTime.SummonCreature(player.world, bp);
                                        status = "snap.revivedash";
                                    }
                                }
                                PlayerHelper.sendMessage(player, status, true);
                            }
                        } else {
                            if (entities.size() > 1) {
                                for (int i = 0; i < (entities.size() / 2); i++) {
                                    EntityLiving e = entities.get(i);
                                    if (!e.getIsInvulnerable()) {
                                        EntityHelper.AttackBySource(e, IDamageSource.SNAP, Float.POSITIVE_INFINITY);
                                        GauntletHelper.makeAshPile(e.world, e.getPosition(), e);
                                    }
                                }

                                if (!tick) {
                                    cap.setSnapCooldown(InfinityConfig.Gauntlet.SnapCooldown);
                                    player.world.playSound(null, player.getPosition(), InfinitySounds.SNAP, SoundCategory.HOSTILE, 1F, 1F);
                                    if (rn.nextInt(10) == 3)
                                        player.world.playSound(null, player.getPosition(), InfinitySounds.IDONTFEELGOOD, SoundCategory.AMBIENT, 1F, 1F);
                                    tick = !tick;
                                }
                            } else {
                                PlayerHelper.sendMessage(player, "gauntlet.snap.notenough", true);
                            }
                            entities.clear();
                        }
                    }
                }
            });
            return null;
        }
    }
}
