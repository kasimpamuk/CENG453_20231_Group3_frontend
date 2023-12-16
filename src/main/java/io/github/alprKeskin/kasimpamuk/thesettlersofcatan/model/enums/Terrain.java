package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums;

import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public enum Terrain {

    DESERT("desert"),
    HILL("hill"),
    MOUNTAIN("mountain"),
    FOREST("forest"),
    FIELD("field"),
    PASTURE("pasture");

    private final String terrainType;

    Terrain(String terrainType) {
        this.terrainType = terrainType;
    }


    public Color getColor() {
        return switch (this.terrainType) {
            case "desert" -> Color.web("#e4c49f"); // A sandy beige, resembling desert sand
            case "hill" -> Color.web("#b7410e"); // A darker earth tone, suggestive of rocky hills
            case "mountain" -> Color.web("#708090"); // A muted grey with a hint of green, resembling mountain rock
            case "forest" -> Color.web("#228b22"); // A deep green, representative of dense forests
            case "field" -> Color.web("#ffc300"); // A golden wheat color, typical of fields
            case "pasture" -> Color.web("#7bb274"); // A fresh, vibrant green, suggestive of lush pastures
            default -> Color.web("#ffffff"); // Pure white for undefined terrain
        };
    }

}