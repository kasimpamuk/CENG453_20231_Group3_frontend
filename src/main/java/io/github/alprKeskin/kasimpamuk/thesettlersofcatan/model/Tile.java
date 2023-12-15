package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import lombok.Getter;

@Getter
public class Tile {
    private int id;
    private double centerx;
    private double centery;
    private int size;
    static int cornerId = 0;
    static int edgeId = 0;
    private String terrain;
    private int number;
    //private Point[] corners;
    //private Edge[] edges;
    // Getters and setters for fields
    @Getter
    private Polygon hexagon;

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

    public Tile(int id, double centerx, double centery, String terrain, int number, int size) {
        this.id = id;
        this.centerx = centerx;
        this.centery = centery;
        this.terrain = terrain;
        this.number = number;
        this.size = size;
        //this.corners = new Point[6];
        //this.edges = new Edge[6];

        double v = Math.sqrt(3) / 2.0;
        this.hexagon = new Polygon();
        Double[] hexPoints = new Double[]{
                centerx - v * size, centery + (double) size / 2,
                centerx - v * size, centery - (double) size / 2,
                centerx, centery - (double) size,
                centerx + v * size, centery - (double) size / 2,
                centerx + v * size, centery + (double) size / 2,
                centerx, centery + (double) size
        };
        hexagon.getPoints().addAll(hexPoints);
        hexagon.setStrokeWidth(2);
        hexagon.setStroke(Color.BLACK);
        hexagon.setFill(getTerrainColor(terrain));
/*
        // Initialize corners and edges
        for (int i = 0; i < 6; i++) {
            corners[i] = new Point(hexPoints[i * 2], hexPoints[i * 2 + 1]);
            edges[i] = new Edge(i, corners[i], corners[(i + 1) % 6]);
        }
    }
*/

    }



    // Additional methods like toString(), setColor(), etc.

}
