package com.igauntlet.network.packets;

import com.igauntlet.common.damage.IDamageSource;
import com.igauntlet.config.ModConfig;
import com.igauntlet.init.ModBlocks;
import com.igauntlet.init.ModItems;
import com.igauntlet.util.handlers.SoundsHandler;
import com.igauntlet.util.helpers.PlayerHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.List;

import static com.igauntlet.common.items.function.gems.GemPower.WriteAsh;


public class MessageSnap implements IMessage {

    public static boolean snap;
    public static final List<EntityLiving> SNAPENTITY = new ArrayList<EntityLiving>();

    public MessageSnap() {
    }

    public MessageSnap(boolean snap) {
        this.snap = snap;
    }

    public void fromBytes(ByteBuf buf) {
        this.snap = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.snap);
    }

    public static class Handler implements IMessageHandler<MessageSnap, IMessage> {

        @Override
        public IMessage onMessage(MessageSnap message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayerMP playerIn = ctx.getServerHandler().player;
                ItemStack stack = playerIn.getActiveItemStack();
                NBTTagCompound nbt = stack.getTagCompound();
                boolean CanSnap = ModConfig.Gauntlet.Snap;
                boolean Snapinit = false;
                int extend = ModConfig.Gauntlet.ExtensionRange;
                int passentity = 0;

                if (CanSnap) {

                    // Entity Counter
                    if (!(playerIn.getHeldItemMainhand().getItem() == ModItems.INFINITY_GAUNTLET)) return;
                    for (EntityLiving targetentity : playerIn.world.getEntitiesWithinAABB(EntityLiving.class, playerIn.getEntityBoundingBox().grow(extend, extend, extend))) {
                       SNAPENTITY.add(targetentity);
                        Snapinit = true;
                        passentity++;
                    }

                    // Snap
                    if (Snapinit) {
                        for (EntityLiving e : playerIn.world.getEntitiesWithinAABB(EntityLiving.class, playerIn.getEntityBoundingBox().grow(extend, extend, extend))) {

                            int halfentity = passentity / 2;

                            if(halfentity > 0 ) {
                                EntityLiving targetentity = SNAPENTITY.get(halfentity);

                                int entity = targetentity.getEntityId();
                                if (!targetentity.getIsInvulnerable()) {
                                    Block blk = ModBlocks.ASH_PILE;
                                    BlockPos pos0 = new BlockPos(targetentity.posX, targetentity.posY, targetentity.posZ);
                                    IBlockState state0 = blk.getDefaultState();
                                    targetentity.world.setBlockState(pos0, state0);
                                    WriteAsh(pos0, playerIn.world, entity);
                                    targetentity.attackEntityFrom(IDamageSource.SNAP, targetentity.getMaxHealth());
                                    passentity--;
                                }
                            }
                            if(SNAPENTITY.size() >= 2) {
                                playerIn.world.playSound(null, playerIn.getPosition(), SoundsHandler.SNAP, SoundCategory.HOSTILE, 1F, 1F);
                            }else{
                                PlayerHelper.sendMessage(playerIn,"gauntlet.snap.notenough", true);
                            }
                        }
                    }
                    SNAPENTITY.clear();
                    passentity = 0;
                }
            });
            return null;
        }
    }
}
