package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration;

public enum Color {
	YELLOW,
	GREEN,
	RED,
	BLUE;

	public String getHouse() {
		if (this == Color.GREEN) {
			return "settlement_green.png";
		} else if (this == Color.RED) {
			return "settlement_red.png";
		} else if (this == Color.BLUE) {
			return "settlement_blue.png";
		} else {
			return "settlement_yellow.png";
		}
	}

	public javafx.scene.paint.Color getColor() {
		if (this == Color.YELLOW) {
			return javafx.scene.paint.Color.YELLOW;
		} else if (this == Color.RED) {
			return javafx.scene.paint.Color.RED;
		} else if (this == Color.BLUE) {
			return javafx.scene.paint.Color.BLUE;
		} else {
			return javafx.scene.paint.Color.GREEN;
		}
	}
}
