package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import lombok.Getter;

public class Edge {
    @Getter
    private int id;
    private Point start;
    private Point end;
    private boolean isRoad;
    @Getter
    private Shape road;


    public Edge(int id, Point start, Point end, boolean isRoad) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.isRoad = isRoad;
    }
    public Edge(int id, Point start, Point end) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.isRoad = false;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public boolean isRoad() {
        return isRoad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public void setRoad(boolean road) {
        isRoad = road;
    }

    public Shape setRoad(Color color) {
        this.isRoad = true;
        this.road = createRoad(color);
        return this.road;
    }
    private Shape createRoad(Color color) {
        double roadWidth = 8; // Width of the road
        double shrinkFactor = 10; // Distance to shrink the road from each corner

        // Calculate the direction vector from start to end
        double dx = this.end.getX() - this.start.getX();
        double dy = this.end.getY() - this.start.getY();
        double length = Math.sqrt(dx * dx + dy * dy);
        double unitDx = dx / length;
        double unitDy = dy / length;

        // Calculate new start and end points for the road
        double startX = this.start.getX() + unitDx * shrinkFactor;
        double startY = this.start.getY() + unitDy * shrinkFactor;
        double endX = this.end.getX() - unitDx * shrinkFactor;
        double endY = this.end.getY() - unitDy * shrinkFactor;

        // Create a line to represent the road
        Line road = new Line(startX, startY, endX, endY);
        road.setStrokeWidth(roadWidth);
        road.setStroke(color);

        return road;
    }
    @Override
    public String toString() {
        return "Edge{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", isRoad=" + isRoad +
                '}';
    }
}
