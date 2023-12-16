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
    }

    public Point getTopCornerPoint() {
        return corners.get(0);
    }

    public Point getTopRightCornerPoint() {
        return this.corners.get(1);
    }

    public Point getBottomRightCornerPoint() {
        return this.corners.get(2);
    }

    public Point getBottomCornerPoint() {
        return corners.get(3);
    }

    public Point getBottomLeftCornerPoint() {
        return this.corners.get(4);
    }

    public Point getTopLeftCornerPoint() {
        return this.corners.get(5);
    }

    private Double[] initializeHexagonPoints() {
        double v = Math.sqrt(3) / 2.0;

        // v * edgeSize = merkezden kenara dik uzaklık

        // Clockwise order starting from top
        this.corners.add(new Point(center.getX(), center.getY() + (double) edgeSize)); // üst
        this.corners.add(new Point(center.getX() + v * edgeSize, center.getY() + (double) edgeSize / 2)); // sağ üst
        this.corners.add(new Point(center.getX() + v * edgeSize, center.getY() - (double) edgeSize / 2)); // sağ alt
        this.corners.add(new Point(center.getX(), center.getY() - (double) edgeSize)); // alt
        this.corners.add(new Point(center.getX() - v * edgeSize, center.getY() - (double) edgeSize / 2));// sol alt
        this.corners.add(new Point(center.getX() - v * edgeSize, center.getY() + (double) edgeSize / 2));// sol üst


        return new Double[]{
                center.getX(), center.getY() + (double) edgeSize,// üst
                center.getX() + v * edgeSize, center.getY() + (double) edgeSize / 2,// sağ üst
                center.getX() + v * edgeSize, center.getY() - (double) edgeSize / 2,// sağ alt
                center.getX(), center.getY() - (double) edgeSize,// alt
                center.getX() - v * edgeSize, center.getY() - (double) edgeSize / 2,// sol alt
                center.getX() - v * edgeSize, center.getY() + (double) edgeSize / 2// sol üst
        };
    }

}
