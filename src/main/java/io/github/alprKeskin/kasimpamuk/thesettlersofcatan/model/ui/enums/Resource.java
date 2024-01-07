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

    public String getColor() {
        return switch (this) {
            case BRICK -> "#b7410e";
            case ORE -> "#708090";
            case LUMBER -> "#228b22";
            case GRAIN -> "#ffc300";
            case WOOL -> "#b6c9a2";
        };
    }

}
