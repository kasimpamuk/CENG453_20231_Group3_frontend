package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Player {

	private int gameId;
	private int playerId;
	private int score = 0;
	private Color color;
	private List<Integer> settlementIds = new ArrayList<>();
	private List<Integer> roadIds = new ArrayList<>();
	private ResourceInfo resourceInfo = new ResourceInfo();

	public Player(int gameId, int playerId) {
		this.gameId = gameId;
		this.playerId = playerId;
		this.color = this.assignColor(playerId - 4 * gameId);
	}

	private Color assignColor(int id) {
		if (id == 0) return Color.YELLOW;
		else if (id == 1) return Color.GREEN;
		else if (id == 2) return Color.RED;
		else if (id == 3) return Color.BLUE;
		else throw new RuntimeException("Color assignment using id larger than 3");
	}

}
