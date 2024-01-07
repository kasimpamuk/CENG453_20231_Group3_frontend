package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.TerrainType;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen.ResourceBoxService;
import javafx.scene.layout.Pane;
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
    private final TerrainType terrainType;
    private final int number;
    private List<Point> corners;
    static int cornerId = 0;
    static int edgeId = 0;
    private Polygon hexagon;
    private final ResourceBoxService resourceBoxService;

    public Tile(int id, Point center, TerrainType terrainType, int number, int edgeSize, ResourceBoxService resourceBoxService) {
        this.id = id;
        this.center = center;
        this.edgeSize = edgeSize;
        this.terrainType = terrainType;
        this.number = number;
        this.resourceBoxService = resourceBoxService;

        corners = new ArrayList<>();

        this.hexagon = new Polygon();
        Double[] hexPoints = initializeHexagonPoints();
        hexagon.getPoints().addAll(hexPoints);
        hexagon.setStrokeWidth(2);
        hexagon.setStroke(Color.BLACK);
        hexagon.setFill(terrainType.getColor());
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


    public Edge createTopRightEdge(int id, Pane pane) {
        return this.createEdge(id, this.getTopCornerPoint(), this.getTopRightCornerPoint(), pane);
    }
    public Edge createRightEdge(int id, Pane pane) {
        return this.createEdge(id, this.getTopRightCornerPoint(), this.getBottomRightCornerPoint(), pane);
    }
    public Edge createBottomRightEdge(int id, Pane pane) {
        return this.createEdge(id, this.getBottomRightCornerPoint(), this.getBottomCornerPoint(), pane);
    }
    public Edge createBottomLeftEdge(int id, Pane pane) {
        return this.createEdge(id, this.getBottomCornerPoint(), this.getBottomLeftCornerPoint(), pane);
    }
    public Edge createLeftEdge(int id, Pane pane) {
        return this.createEdge(id, this.getBottomLeftCornerPoint(), this.getTopLeftCornerPoint(), pane);
    }
    public Edge createTopLeftEdge(int id, Pane pane) {
        return this.createEdge(id, this.getTopLeftCornerPoint(), this.getTopCornerPoint(), pane);
    }

    private Edge createEdge(int id, Point point1, Point point2, Pane pane) {
        Edge edge = new Edge(id, point1, point2, pane, resourceBoxService);
        pane.getChildren().add(edge.getButton());
        return edge;
    }

    private Double[] initializeHexagonPoints() {
        double distanceFromCenterToEdge = Math.sqrt(3) / 2.0;

        // Clockwise order starting from top
        this.corners.add(new Point(center.getX(), center.getY() - (double) edgeSize)); // Top Corner
        this.corners.add(new Point(center.getX() + distanceFromCenterToEdge * edgeSize, center.getY() - (double) edgeSize / 2)); // Top Right Corner
        this.corners.add(new Point(center.getX() + distanceFromCenterToEdge * edgeSize, center.getY() + (double) edgeSize / 2)); // Bottom Right Corner
        this.corners.add(new Point(center.getX(), center.getY() + (double) edgeSize)); // Bottom Corner
        this.corners.add(new Point(center.getX() - distanceFromCenterToEdge * edgeSize, center.getY() + (double) edgeSize / 2)); // Bottom Left Corner
        this.corners.add(new Point(center.getX() - distanceFromCenterToEdge * edgeSize, center.getY() - (double) edgeSize / 2)); // Top Left Corner


        return new Double[]{
                center.getX(), center.getY() - (double) edgeSize, // Top
                center.getX() + distanceFromCenterToEdge * edgeSize, center.getY() - (double) edgeSize / 2, // Top Right Corner
                center.getX() + distanceFromCenterToEdge * edgeSize, center.getY() + (double) edgeSize / 2, // Bottom Right Corner
                center.getX(), center.getY() + (double) edgeSize, // Bottom
                center.getX() - distanceFromCenterToEdge * edgeSize, center.getY() + (double) edgeSize / 2, // Bottom Left Corner
                center.getX() - distanceFromCenterToEdge * edgeSize, center.getY() - (double) edgeSize / 2 // Top Left Corner
        };
    }

}
