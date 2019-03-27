package com.igauntlet.util.handlers;

import com.igauntlet.Infinity;
import com.igauntlet.init.InfinityConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

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
    }
}
