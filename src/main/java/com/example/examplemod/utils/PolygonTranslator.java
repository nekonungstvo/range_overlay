package com.example.examplemod.utils;

import com.example.examplemod.blockfetcher.BlockFetcher;
import net.minecraft.util.Vec3;

import java.util.ArrayList;
import java.util.List;

public class PolygonTranslator {
    public static List<Polygon> translate(BlockFetcher.BlockList blocks, Vec3 position) {
        List<Polygon> polygons = new ArrayList<Polygon>();

        for (Vec3 coordinate : blocks) {
            Vec3 new_coordinate = position.subtract(coordinate);
            Polygon polygon = new Polygon();
            polygon.add(new_coordinate.addVector(0, -1 + 0.2, 1));
            polygon.add(new_coordinate.addVector(1, -1 + 0.2, 1));
            polygon.add(new_coordinate.addVector(1, -1 + 0.2, 0));
            polygon.add(new_coordinate.addVector(0, -1 + 0.2, 0));
            polygons.add(polygon);
        }

        return polygons;
    }

    ;

    public static class Polygon extends ArrayList<Vec3> {
    }
}
