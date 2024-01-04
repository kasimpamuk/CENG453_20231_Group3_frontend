package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResourceInfo {
	private int brickAmount;
	private int grainAmount;
	private int lumberAmount;
	private int oreAmount;
	private int woolAmount;
	// TODO: Add more
}
