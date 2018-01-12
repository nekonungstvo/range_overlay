package com.example.examplemod.core;


import com.example.examplemod.blockfetcher.BlockFetcher;
import com.example.examplemod.blockfetcher.PlayerBlockFetcher;
import com.example.examplemod.blockfetcher.PointBlockFetcher;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.Vec3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RangeCommand extends CommandBase {
    private List<BlockFetcher> blockFetchers;

    RangeCommand(List<BlockFetcher> blockFetchers) {
        this.blockFetchers = blockFetchers;
    }

    public String getCommandName() {
        return "range";
    }

    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        ArrayList<String> arguments = new ArrayList<String>(Arrays.asList(args));

        System.out.println(arguments);

        String operation = arguments.get(0);
        arguments.remove(0);

        System.out.println(arguments);

        boolean is_detached = arguments.contains("--detached");
        arguments.remove("--detached");

        if (operation.equals("add")) {

            String username = null;
            if (!arguments.isEmpty()) username = arguments.get(0);
            if (username == null || username.equals("self"))
                username = sender.getCommandSenderName();

            if (is_detached) {
                Vec3 position = sender.getEntityWorld().getPlayerEntityByName(username).getPosition(0);
                blockFetchers.add(new PointBlockFetcher(sender.getEntityWorld(), position));
            } else {
                blockFetchers.add(new PlayerBlockFetcher(username));
            }
        } else if (operation.equals("clear")) blockFetchers.clear();
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

}