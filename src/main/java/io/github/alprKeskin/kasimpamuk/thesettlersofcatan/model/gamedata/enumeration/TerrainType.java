package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration;

import javafx.scene.paint.Color;

public enum TerrainType {
	DESERT,
	FIELD,
	FOREST,
	HILL,
	MOUNTAIN,
	PASTURE;

	public Color getColor() {
		return switch (this) {
			case DESERT -> Color.web("#e4c49f");
			case HILL -> Color.web("#b7410e");
			case MOUNTAIN -> Color.web("#708090");
			case FOREST -> Color.web("#228b22");
			case FIELD -> Color.web("#ffc300");
			case PASTURE -> Color.web("#b6c9a2");
		};
	}

}
