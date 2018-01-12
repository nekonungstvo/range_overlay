package com.example.examplemod.utils;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class OverlayRenderer {
    public static void render(List<PolygonTranslator.Polygon> polygons) {
        Tessellator tessellator = Tessellator.instance;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glPushMatrix();

        for (PolygonTranslator.Polygon polygon : polygons) {
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA(100, 0, 100, 100);
            for (Vec3 dot : polygon) {
                tessellator.addVertex(dot.xCoord, dot.yCoord, dot.zCoord);
            }
            tessellator.draw();
        }

        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
    }
}
