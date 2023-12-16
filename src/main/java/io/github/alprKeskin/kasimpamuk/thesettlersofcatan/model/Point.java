package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private double x;
    private double y;

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof Point point)) return false;
        return (this.x == point.getX() && this.y == point.getY());
    }
}
