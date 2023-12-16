package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

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
    private final String terrainType;
    private final int number;
    private List<Point> corners;
    //private Edge[] edges;
    static int cornerId = 0;
    static int edgeId = 0;

    private Polygon hexagon;

    public Tile(int id, Point center, String terrainType, int number, int edgeSize) {
        this.id = id;
        this.center = center;
        this.edgeSize = edgeSize;
        this.terrainType = terrainType;
        this.number = number;

        corners = new ArrayList<>();

        this.hexagon = new Polygon();
        Double[] hexPoints = initializeHexagonPoints();
        hexagon.getPoints().addAll(hexPoints);
        hexagon.setStrokeWidth(2);
        hexagon.setStroke(Color.BLACK);
        hexagon.setFill(getTerrainColor(terrainType));

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

    private Color getTerrainColor(String terrain) {
        return switch (terrain) {
            case "desert" -> Color.web("#e4c49f"); // A sandy beige, resembling desert sand
            case "hills" -> Color.web("#b7410e"); // A darker earth tone, suggestive of rocky hills
            case "mountains" -> Color.web("#708090"); // A muted grey with a hint of green, resembling mountain rock
            case "forest" -> Color.web("#228b22"); // A deep green, representative of dense forests
            case "fields" -> Color.web("#ffc300"); // A golden wheat color, typical of fields
            case "pasture" -> Color.web("#7bb274"); // A fresh, vibrant green, suggestive of lush pastures
            default -> Color.web("#ffffff"); // Pure white for undefined terrain
        };
    }

}
