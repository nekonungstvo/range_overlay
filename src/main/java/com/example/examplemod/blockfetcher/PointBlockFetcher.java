package com.example.examplemod.blockfetcher;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class PointBlockFetcher extends BlockFetcher {
    private World world;
    private Vec3 point;

    public PointBlockFetcher(World world, Vec3 point) {
        this.point = point;
        this.world = world;
    }

    public List<BlockList> fetch(EntityPlayer current_player) {
        World world = current_player.getEntityWorld();

        List<BlockList> overlays = new ArrayList<BlockList>();

        if (this.world.equals(world))
            overlays.add(this.fetch_blocks(world, this.point, 5));

        return overlays;
    }
}
