package com.jmteam.igauntlet.util.handlers.client;

import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void guiOverlayEventPre(RenderGameOverlayEvent.Pre e) {
        EntityPlayer player = Minecraft.getMinecraft().player;

        if (player != null) {

            if (CapabilityInfinity.get(player).isPosessing()) {
                if (e.getType() == RenderGameOverlayEvent.ElementType.FOOD) {
                    e.setCanceled(true);
                }
                if (e.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
                    e.setCanceled(true);
                }
                if (e.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
                    e.setCanceled(true);
                }
                if (e.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
                    e.setCanceled(true);
                }
                if (e.getType() == RenderGameOverlayEvent.ElementType.AIR) {
                    e.setCanceled(true);
                }
                if (e.getType() == RenderGameOverlayEvent.ElementType.ARMOR) {
                    e.setCanceled(true);
                }
                if (e.getType() == RenderGameOverlayEvent.ElementType.JUMPBAR) {
                    e.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void renderInvisible(RenderPlayerEvent.Pre e) {
        EntityPlayer player = Minecraft.getMinecraft().player;

        if (player != null) {
            if (CapabilityInfinity.get(player).isPosessing()) e.setCanceled(true);
        }
    }
}
