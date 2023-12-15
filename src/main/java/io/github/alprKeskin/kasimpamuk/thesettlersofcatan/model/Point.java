package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import lombok.Getter;
@Getter
public class Point {
    private double x;
    private double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return  "{" + x + "," + y + '}';
    }

    public boolean equals(Point p) {
        return (this.x == p.getX() && this.y == p.getY());
    }
}
