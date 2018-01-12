package com.example.examplemod.blockfetcher;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class BlockFetcher {
    public int[] ranges = {
            1,
            3,
            9,
            18,
            38,
            60,
            80
    };

    private static Block getWorldBlock(World world, Vec3 position) {
        return world.getBlock(
                (int) position.xCoord,
                (int) position.yCoord,
                (int) position.zCoord
        );
    }

    private static boolean isForHighlight(Block block) {
        return block != Blocks.air & block != Blocks.water & block.isBlockNormalCube();
    }

    BlockList fetch_blocks(World world, Vec3 position, int range) {
        BlockList blocks = new BlockList();

        position = Vec3.createVectorHelper(
                Math.floor(position.xCoord),
                Math.floor(position.yCoord),
                Math.floor(position.zCoord)
        );

        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range; z++) {
                    Vec3 bpos = position.addVector(x, y, z);

                    double distance = bpos.distanceTo(position);
                    if (distance <= 1 + range) {
                        Block block = BlockFetcher.getWorldBlock(world, bpos);
                        Block above_block = BlockFetcher.getWorldBlock(
                                world,
                                bpos.addVector(
                                        0,
                                        +1,
                                        0
                                )
                        );

                        if (BlockFetcher.isForHighlight(block) & !BlockFetcher.isForHighlight(above_block))
                            blocks.add(bpos);
                    }
                }
            }
        }

        return blocks;
    }

    public abstract List<BlockList> fetch(EntityPlayer current_player);

    public class BlockList extends ArrayList<Vec3> {
    }
}
