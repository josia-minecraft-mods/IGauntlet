package com.jmteam.igauntlet.network.packets;

import com.jmteam.igauntlet.common.damage.IDamageSource;
import com.jmteam.igauntlet.common.init.InfinityItems;
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

import static com.jmteam.igauntlet.common.init.InfinityNbtKeys.SNAPPED;

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
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayerMP playerIn = ctx.getServerHandler().player;
                Random random = new Random();

                boolean CanSnap = InfinityConfig.Gauntlet.Snap;
                int extend = InfinityConfig.Gauntlet.ExtensionRange;
                int r = random.nextInt(10);
                int snapentity = 0;
                boolean ticklimit = true;


                if (CanSnap && PlayerHelper.getPDataInt(playerIn, SNAPPED) == 0) {
                    if (!(playerIn.getHeldItemMainhand().getItem() == InfinityItems.infinity_gauntlet)) return;
                    for (EntityLiving targetentity : playerIn.world.getEntitiesWithinAABB(EntityLiving.class, playerIn.getEntityBoundingBox().grow(extend, extend, extend))) {
                        SNAPENTITY.add(targetentity);
                    }
                    snapentity = SNAPENTITY.size() / 2;

                    for (EntityLiving e : SNAPENTITY) {

                        if (snapentity > 0) {
                            EntityLiving targetentity = SNAPENTITY.get(snapentity);

                            EntityLiving entity = targetentity;
                            if (!targetentity.getIsInvulnerable()) {
                                GauntletHelper.makeAshPile(entity.world, entity.getPosition(), entity);
                                targetentity.attackEntityFrom(IDamageSource.SNAP, targetentity.getMaxHealth());
                                snapentity--;
                            }
                        }
                        if (SNAPENTITY.size() > 1) {
                            if (ticklimit) {
                                playerIn.world.playSound(null, playerIn.getPosition(), SoundsHandler.SNAP, SoundCategory.HOSTILE, 1F, 1F);
                                PlayerHelper.setPDataInt(playerIn, "snapped", InfinityConfig.Gauntlet.SnapCooldown * 20);
                                if (r == 3) {
                                    playerIn.world.playSound(null, playerIn.getPosition(), SoundsHandler.IDONTFEELGOOD, SoundCategory.AMBIENT, 1F, 1F);
                                }
                                ticklimit = false;
                            }
                        } else {
                            PlayerHelper.sendMessage(playerIn, "gauntlet.snap.notenough", true);
                        }
                    }
                }
                SNAPENTITY.clear();
                snapentity = 0;
            });
            return null;
        }
    }
}
