package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.enums;

import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public enum Terrain {

    DESERT("desert"),
    FIELD("field"),
    FOREST("forest"),
    HILL("hill"),
    MOUNTAIN("mountain"),
    PASTURE("pasture");

    private final String terrainType;

    Terrain(String terrainType) {
        this.terrainType = terrainType;
    }


    public Color getColor() {
        return switch (this.terrainType) {
            case "desert" -> Color.web("#e4c49f");
            case "hill" -> Color.web("#b7410e");
            case "mountain" -> Color.web("#708090");
            case "forest" -> Color.web("#228b22");
            case "field" -> Color.web("#ffc300");
            case "pasture" -> Color.web("#b6c9a2");
            default -> Color.web("#ffffff");
        };
    }

}