package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.enums;

import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public enum Resource {

    BRICK,
    GRAIN,
    LUMBER,
    ORE,
    WOOL;

    public Color getColor() {
        return switch (this) {
            case BRICK -> Color.web("#b7410e");
            case ORE -> Color.web("#708090");
            case LUMBER -> Color.web("#228b22");
            case GRAIN -> Color.web("#ffc300");
            case WOOL -> Color.web("#b6c9a2");
        };
    }

}
