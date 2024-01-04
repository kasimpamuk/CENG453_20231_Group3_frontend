package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.uı.enums;

import lombok.Getter;

@Getter
public enum House {

	RED_HOUSE("settlement_red.png"),
	GREEN_HOUSE("settlement_green.png"),
	BLUE_HOUSE("settlement_blue.png"),
	YELLOW_HOUSE("settlement_yellow.png");

	private final String house;

	House(String house) {
		this.house = house;
	}

}
