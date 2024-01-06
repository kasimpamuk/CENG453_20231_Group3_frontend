package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.enums;

import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public enum Resource {

    BRICK("Brick"),
    GRAIN("Grain"),
    LUMBER("Lumber"),
    ORE("Ore"),
    WOOL("Wool");

    private final String resourceType;

    Resource(String resourceType) {
        this.resourceType = resourceType;
    }


    public Color getColor() {
        return switch (this.resourceType) {
            case "Brick" -> Color.web("#b7410e");
            case "Ore" -> Color.web("#708090");
            case "Lumber" -> Color.web("#228b22");
            case "Grain" -> Color.web("#ffc300");
            case "Wool" -> Color.web("#b6c9a2");
            default -> Color.web("#ffffff");
        };
    }
}
