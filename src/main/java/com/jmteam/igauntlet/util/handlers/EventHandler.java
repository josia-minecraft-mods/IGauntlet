package com.jmteam.igauntlet.util.handlers;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.util.InfinityConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
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

            if (player.getEntityData().hasKey("snapped")) {
                if (player.getEntityData().getInteger("snapped") > 0) {
                    int removing = player.getEntityData().getInteger("snapped") - 1;
                    player.getEntityData().setInteger("snapped", removing);
                }
            } else {
                if (player.getActiveItemStack().getItem() == InfinityItems.infinity_gauntlet)
                    player.getEntityData().setInteger("snapped", 0);
            }
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
    public static void PosessEntity(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            Vec3d vec = player.getLookVec();

            IInfinityCap cap = CapabilityInfinity.get(player);

            if (cap.isPosessing()) {
                Entity p = cap.getPosessedEntity();

                if (p instanceof EntityLiving) {

                    EntityLiving l = (EntityLiving) p;

                    // TODO Save coords so you get tp'd back before you went all goofy

                    if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()
                            || Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown()
                            || Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {

                        if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() && p.onGround) {
                            p.motionX = vec.x / 4;
                            p.motionZ = vec.z / 4;
                        }

                        if (Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() && p.onGround) {
                            p.motionX = -vec.x / 4;
                            p.motionZ = -vec.z / 4;
                        }


                        // Special Functions per entity
                        if (p instanceof EntityCreeper) {
                            if (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() && p.onGround) { // TODO Change to custom keybind
                                p.world.createExplosion(p, p.posX, p.posY, p.posZ, 5, true);
                                p.setDead();
                                cap.clearPosessing();
                            }
                        }

                    } else {
                        p.motionX = 0;
                        p.motionZ = 0;
                    }

                    if (Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown() && p.onGround) {
                        cap.clearPosessing();
                    }

                    if (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {
                        if(p.onGround)
                        p.motionY = 0.4;

                        p.motionX = vec.x / 8;
                        p.motionZ = vec.z / 8;
                    }

                    if(Minecraft.getMinecraft().gameSettings.keyBindSprint.isKeyDown()) {
                        p.motionX = vec.x / 3;
                        p.motionZ = vec.z / 3;
                    }

                    if(((EntityLiving) p).getHealth() <= 0) {
                        cap.clearPosessing();
                    }

                    p.rotationYaw = player.rotationYawHead;
                    p.rotationPitch = player.rotationPitch;
                }
            }
        }
    }

    @SubscribeEvent
    public static void PlayerJoinWorld(PlayerEvent.PlayerLoggedOutEvent playerEvent) {
        IInfinityCap cap = CapabilityInfinity.get(playerEvent.player);
        if (cap.isPosessing()) {
            cap.setPosessing(false);
            playerEvent.player.setInvisible(false);
            if (playerEvent.player.isRiding())
                playerEvent.player.dismountRidingEntity();
        }
    }
}
