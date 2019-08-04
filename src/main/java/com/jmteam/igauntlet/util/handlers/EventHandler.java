package com.jmteam.igauntlet.util.handlers;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.util.InfinityConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static com.jmteam.igauntlet.common.init.InfinityNbtKeys.SNAPPED;

@Mod.EventBusSubscriber(modid = Infinity.MODID)
public class EventHandler {

   /* @SubscribeEvent
    public static void CancelEnemy(LivingSetAttackTargetEvent event) {
        EntityLivingBase entity = event.getEntityLiving();

        if (entity != null && entity.getEntityData().getBoolean("isfriend")) {
            if(event.getTarget() instanceof EntityPlayer) {
                entity.setRevengeTarget(null);
            }
                if(entity instanceof EntityLiving && event.getTarget().getRevengeTarget() instanceof EntityPlayer)
               ((EntityLiving) entity).setAttackTarget(null);
            }
        }

    @SubscribeEvent
    public static void DenyExplosion(ExplosionEvent e) {
        Entity entity = e.getExplosion().getExplosivePlacedBy();
        if (entity == null) return;
        if (EntityHelper.EntityIsFriend(entity)) {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void EndermanTeleport(EnderTeleportEvent e) {
        if (EntityHelper.EntityIsFriend(e.getEntity())) {
            e.setCanceled(true);
        }
    }*/

    @SubscribeEvent
    public static void countDownSnap(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();

            // Snap Countdown
            if (player.getEntityData().hasKey(SNAPPED)) {
                if (player.getEntityData().getInteger(SNAPPED) > 0) {
                    int removing = player.getEntityData().getInteger(SNAPPED) - 1;
                    player.getEntityData().setInteger(SNAPPED, removing);
                }
            } else {
                if (player.getActiveItemStack().getItem() == InfinityItems.infinity_gauntlet)
                    player.getEntityData().setInteger(SNAPPED, 0);
            }

            // Posession
            //    GemSoul.ProcessTakenSoul(player);
        }
    }


    @SubscribeEvent
    public static void PlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent playerEvent) {
        EntityPlayer player = playerEvent.player;
        if (!player.world.isRemote && InfinityConfig.Gauntlet.UpdateChecker) {
            ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
            if (version.status.equals(ForgeVersion.Status.OUTDATED)) {
                TextComponentString msg = new TextComponentString(TextFormatting.BLUE + "[IGauntlet] : New Update Available!");
                msg.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/igauntlet"));
                msg.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("Open Website")));
                player.sendMessage(msg);
            }
        }
        if (CapabilityInfinity.get(player).isPosessing()) CapabilityInfinity.get(player).setPosessing(false);
    }

    @SubscribeEvent
    public static void PlayerJoinWorld(PlayerEvent.PlayerLoggedOutEvent playerEvent) {
        IInfinityCap cap = CapabilityInfinity.get(playerEvent.player);
        if (cap.isPosessing()) {
            cap.setPosessing(false);
            if (playerEvent.player.isRiding())
                playerEvent.player.dismountRidingEntity();
        }
    }
}
