package com.igauntlet.client;

import com.igauntlet.client.util.ModKeyBinds;
import com.igauntlet.config.ModConfig;
import com.igauntlet.network.NetworkHandler;
import com.igauntlet.network.packets.MessageSnap;
import com.igauntlet.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import scala.swing.event.KeyPressed;

@Mod.EventBusSubscriber(value = Side.CLIENT)
public class ClientHandler {

   public static EntityPlayer player = Minecraft.getMinecraft().player;
    public static ItemStack stack = player.getActiveItemStack();
    public static int range = ModConfig.Gauntlet.PowerStone.ExtensionRange;


   /* @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        if (ModKeyBinds.SNAP.isPressed()) {
            NetworkHandler.NETWORK.sendToServer(new MessageSnap(player, stack, range));
        }
    }*/
}
