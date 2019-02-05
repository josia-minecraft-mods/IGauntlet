package com.igauntlet.network.packets;

import com.igauntlet.common.damage.IDamageSource;
import com.igauntlet.common.items.function.gems.GemPower;
import com.igauntlet.config.ModConfig;
import com.igauntlet.init.ModBlocks;
import com.igauntlet.util.handlers.SoundsHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static com.igauntlet.common.items.function.gems.GemPower.WriteAsh;

public class MessageSnap implements IMessage {

    static EntityPlayer player;
    static ItemStack stack;
    public static int extend;


    public MessageSnap() {
    }

    public MessageSnap(EntityPlayer player, ItemStack stack, int extend) {
        this.player = player;
        this.stack = stack;
        this.extend = extend;
    }

    public void fromBytes(ByteBuf buf) {

    }

    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageSnap, IMessage> {

        @Override
        public IMessage onMessage(MessageSnap message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                boolean CanSnap = ModConfig.Gauntlet.PowerStone.Snap;
                if (player.isSneaking() && CanSnap) {
                    player.world.playSound(null, player.getPosition(), SoundsHandler.SNAP, SoundCategory.HOSTILE, 1F, 1F);
                }

                if (!player.world.isRemote && player.isSneaking() && CanSnap) {
                    for (Entity targetentity : player.world.getEntitiesWithinAABB(EntityLiving.class, player.getEntityBoundingBox().grow(extend, extend, extend))) {
                        int entity = targetentity.getEntityId();

                        Block blk = ModBlocks.ASH_PILE;
                        BlockPos pos0 = new BlockPos(targetentity.posX, targetentity.posY, targetentity.posZ);
                        IBlockState state0 = blk.getDefaultState();
                        targetentity.world.setBlockState(pos0, state0);
                        WriteAsh(pos0, player.world, entity);
                        targetentity.attackEntityFrom(IDamageSource.SNAP, 1000);

                        if (!player.capabilities.isCreativeMode) {
                            stack.setItemDamage(stack.getItemDamage() + 1);
                        }
                    }
                }
            });
            return null;
        }

    }
}
