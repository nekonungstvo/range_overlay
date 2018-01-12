package com.example.examplemod.blockfetcher;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class PlayerBlockFetcher extends BlockFetcher {
    private String username;

    public PlayerBlockFetcher(String username) {
        this.username = username;
    }

    public List<BlockList> fetch(EntityPlayer current_player) {
        World world = current_player.getEntityWorld();
        List<BlockList> array = new ArrayList<BlockList>();

        EntityPlayer player = world.getPlayerEntityByName(username);

        if (player != null)
            array.add(this.fetch_blocks(world, player.getPosition(0), 5));

        return array;
    }
}
