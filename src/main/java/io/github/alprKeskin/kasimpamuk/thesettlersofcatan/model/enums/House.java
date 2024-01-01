package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums;

import lombok.Getter;

@Getter
public enum House {

	RED_HOUSE("red-house.jpeg"),
	GREEN_HOUSE("green-house.jpeg"),
	BLUE_HOUSE("blue-house.jpeg");

	private final String house;

	House(String house) {
		this.house = house;
	}

}
