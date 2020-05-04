package com.jmteam.igauntlet.common.commands;

import com.jmteam.igauntlet.util.helpers.schematics.Schematic;
import com.jmteam.igauntlet.util.helpers.schematics.SchematicUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.Collections;
import java.util.List;

public class CommandCreateSchematic extends CommandBase {

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 1 && !sender.getEntityWorld().isRemote) {


            int X1 = 0, Y1 = 0, Z1 = 0;
            int X2 = 0, Y2 = 0, Z2 = 0;
            String name = "schematic_" + System.currentTimeMillis();


            if (args.length > 5) {
                X1 = Integer.parseInt(args[0]);
                Y1 = Integer.parseInt(args[1]);
                Z1 = Integer.parseInt(args[2]);

                X2 = Integer.parseInt(args[3]);
                Y2 = Integer.parseInt(args[4]);
                Z2 = Integer.parseInt(args[5]);
            } else {
                sender.sendMessage(new TextComponentString(TextFormatting.RED + "Define Coords"));
                return;
            }

            if (args.length > 6)
                name = args[6];


            if (sender instanceof EntityPlayer) {
                Schematic schematic = SchematicUtil.mapSchematic(((EntityPlayer) sender).world, new BlockPos(X1, Y1, Z1), new BlockPos(X2, Y2, Z2), name, false);

              if(schematic != null)  SchematicUtil.addSchematic(schematic);
            }
        } else {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Define Coords"));
        }
    }

    @Override
    public String getName() {
        return "schematic-add";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/" + this.getName() + " <X1> <Y1> <Z1> <X2> <Y2> <Z2> <schematic_name>";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        return Collections.emptyList();
    }
}
