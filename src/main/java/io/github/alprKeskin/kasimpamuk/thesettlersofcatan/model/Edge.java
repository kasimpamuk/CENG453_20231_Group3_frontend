package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import lombok.Getter;

public class Edge {
    @Getter
    private int id;
    private Point start;
    private Point end;
    private boolean isRoad;

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
