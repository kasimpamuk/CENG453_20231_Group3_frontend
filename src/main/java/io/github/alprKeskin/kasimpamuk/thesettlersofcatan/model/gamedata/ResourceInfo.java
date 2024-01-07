package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResourceInfo {
	private int brickAmount = 3;
	private int grainAmount = 1;
	private int lumberAmount = 3;
	private int oreAmount = 0;
	private int woolAmount = 1;
}
