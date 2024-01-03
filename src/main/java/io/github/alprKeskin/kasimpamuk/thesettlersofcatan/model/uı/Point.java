package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.uı;

import lombok.*;

import static java.lang.Math.abs;

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
        double epsilon = 0.1;
        return ( abs(this.x - point.getX()) < epsilon && abs(this.y - point.getY()) < epsilon);
    }
}
