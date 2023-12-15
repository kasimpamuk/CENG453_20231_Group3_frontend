package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import java.util.Objects;

public class Point {
    private Double x;
    private Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return  "{" + x + "," + y + '}';
    }

    public boolean equals(Point p) {
        return (Objects.equals(this.x, p.getX()) && this.y == p.getY());
    }
}
