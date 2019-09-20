package com.jmteam.igauntlet.common.events;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.damage.IDamageSource;
import com.jmteam.igauntlet.common.function.gems.GemSoul;
import com.jmteam.igauntlet.util.InfinityConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber(modid = Infinity.MODID)
public class EventHandler {

   /* @SubscribeEvent
    public static void CancelEnemy(LivingSetAttackTargetEvent events) {
        EntityLivingBase entity = events.getEntityLiving();

        if (entity != null && entity.getEntityData().getBoolean("isfriend")) {
            if(events.getTarget() instanceof EntityPlayer) {
                entity.setRevengeTarget(null);
            }
                if(entity instanceof EntityLiving && events.getTarget().getRevengeTarget() instanceof EntityPlayer)
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

   //TODO Add PosessionEvent so i can control things from there


    @SubscribeEvent
    public static void onDeath(LivingDeathEvent e) {
        if (GemSoul.posessed.containsValue(e.getEntityLiving())) {
            IInfinityCap cap = CapabilityInfinity.get(GemSoul.posessed.get(e.getEntityLiving()));
            cap.clearPosessing();
        }
    }

    @SubscribeEvent
    public static void DropEvent(LivingDropsEvent e) {
        if (e.getSource() == IDamageSource.SNAP) {
            e.getDrops().clear();
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
    public static void PlayerLeaveWorld(PlayerEvent.PlayerLoggedOutEvent playerEvent) {
        IInfinityCap cap = CapabilityInfinity.get(playerEvent.player);
        if (cap.isPosessing()) {
            cap.setPosessing(false);
            if (playerEvent.player.isRiding())
                playerEvent.player.dismountRidingEntity();
            GemSoul.posessed.remove(cap.getPosessedEntity());
        }
    }
}
