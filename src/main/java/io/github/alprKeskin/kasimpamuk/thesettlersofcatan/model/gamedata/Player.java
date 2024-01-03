package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.Color;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Player {

	private int id;
	private int score;
	private Color color;
	private List<Integer> settlementIds;
	private List<Integer> roadIds;
	private ResourceInfo resourceInfo;

}
