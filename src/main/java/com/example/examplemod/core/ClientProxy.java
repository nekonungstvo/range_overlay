package com.example.examplemod.core;

import com.example.examplemod.blockfetcher.BlockFetcher;
import com.example.examplemod.utils.OverlayRenderer;
import com.example.examplemod.utils.PolygonTranslator;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;

public class ClientProxy extends CommonProxy {
    private List<BlockFetcher> blockFetchers = new ArrayList<BlockFetcher>();

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new RangeCommand(blockFetchers));
    }

    private Vec3 calculatePlayerPosition(float partialTicks, EntityPlayer player) {
        float px = (float) player.posX;
        float py = (float) player.posY;
        float pz = (float) player.posZ;
        float mx = (float) player.prevPosX;
        float my = (float) player.prevPosY;
        float mz = (float) player.prevPosZ;

        return Vec3.createVectorHelper(
                mx + (px - mx) * partialTicks,
                my + (py - my) * partialTicks,
                mz + (pz - mz) * partialTicks
        ).addVector(0, -2 + player.getDefaultEyeHeight(), 0);
    }

    @SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent event) {
        EntityPlayer current_player = FMLClientHandler.instance().getClientPlayerEntity();
        Vec3 position = calculatePlayerPosition(event.partialTicks, current_player);

        for (BlockFetcher blockFetcher : this.blockFetchers) {
            List<BlockFetcher.BlockList> overlays = blockFetcher.fetch(current_player);
            for (BlockFetcher.BlockList overlay : overlays) {
                OverlayRenderer.render(
                        PolygonTranslator.translate(overlay, position)
                );
            }
        }


    }
}
