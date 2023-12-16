package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Tile {
    private final int id;
    private final Point center;
    private final int edgeSize;
    private final Terrain terrain;
    private final int number;
    private List<Point> corners;
    //private Edge[] edges;
    static int cornerId = 0;
    static int edgeId = 0;
    private Polygon hexagon;

    public Tile(int id, Point center, Terrain terrain, int number, int edgeSize) {
        this.id = id;
        this.center = center;
        this.edgeSize = edgeSize;
        this.terrain = terrain;
        this.number = number;

        corners = new ArrayList<>();

        this.hexagon = new Polygon();
        Double[] hexPoints = initializeHexagonPoints();
        hexagon.getPoints().addAll(hexPoints);
        hexagon.setStrokeWidth(2);
        hexagon.setStroke(Color.BLACK);
        hexagon.setFill(terrain.getColor());

        // Initialize corners and edges
//        for (int i = 0; i < 6; i++) {
//            corners[i] = new Point(hexPoints[i * 2], hexPoints[i * 2 + 1]);
//            edges[i] = new Edge(i, corners[i], corners[(i + 1) % 6]);
//        }

    }

    private Double[] initializeHexagonPoints() {
        double v = Math.sqrt(3) / 2.0;

        this.corners.add(new Point(center.getX() - v * edgeSize, center.getY() + (double) edgeSize / 2));
        this.corners.add(new Point(center.getX() - v * edgeSize, center.getY() - (double) edgeSize / 2));
        this.corners.add(new Point(center.getX(), center.getY() - (double) edgeSize));
        this.corners.add(new Point(center.getX() + v * edgeSize, center.getY() - (double) edgeSize / 2));
        this.corners.add(new Point(center.getX() + v * edgeSize, center.getY() + (double) edgeSize / 2));
        this.corners.add(new Point(center.getX(), center.getY() + (double) edgeSize));


        return new Double[]{
                center.getX() - v * edgeSize, center.getY() + (double) edgeSize / 2,
                center.getX() - v * edgeSize, center.getY() - (double) edgeSize / 2,
                center.getX(), center.getY() - (double) edgeSize,
                center.getX() + v * edgeSize, center.getY() - (double) edgeSize / 2,
                center.getX() + v * edgeSize, center.getY() + (double) edgeSize / 2,
                center.getX(), center.getY() + (double) edgeSize
        };
    }

}
