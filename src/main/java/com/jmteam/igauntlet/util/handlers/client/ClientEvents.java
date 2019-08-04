package com.jmteam.igauntlet.util.handlers.client;

import com.jmteam.igauntlet.client.init.InfinityKeyBinds;
import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.network.NetworkHandler;
import com.jmteam.igauntlet.network.packets.PacketPosessFunction;
import com.jmteam.igauntlet.network.packets.PacketSnap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.InputUpdateEvent;
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
    public static void onClientTick(InputUpdateEvent e) {
        Minecraft mc = Minecraft.getMinecraft();
        GameSettings set = mc.gameSettings;
        IInfinityCap cap = CapabilityInfinity.get(e.getEntityPlayer());
        String func = "";

        if (InfinityKeyBinds.SNAP.isPressed())
            NetworkHandler.NETWORK.sendToServer(new PacketSnap());

        if(cap.isPosessing()) {
            if(InfinityKeyBinds.SPECIAL.isPressed()) func = "special";
            if(set.keyBindForward.isKeyDown()) func = "forward";
            if(set.keyBindBack.isKeyDown()) func = "back";
            if(set.keyBindSprint.isKeyDown()) func = "sprint";
            if(set.keyBindSneak.isKeyDown()) func = "clear";
            if(set.keyBindJump.isKeyDown()) func = "jump";

            if(!func.equals("")) NetworkHandler.NETWORK.sendToServer(new PacketPosessFunction(func));
        }
    }

    @SubscribeEvent
    public static void renderPlayer(RenderPlayerEvent.Pre e) {
        EntityPlayer player = Minecraft.getMinecraft().player;

        if(!e.isCanceled()) {
            if (player != null) {
                if (CapabilityInfinity.get(player).isPosessing()) {
                    e.setCanceled(true);
                    return;
                }
            }
        }
    }
}
