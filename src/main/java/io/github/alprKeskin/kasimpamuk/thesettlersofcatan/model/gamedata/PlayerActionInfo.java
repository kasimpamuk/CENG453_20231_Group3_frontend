package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlayerActionInfo {
	private int playerId;
	private Color playerColor;
	private int dice1;
	private int dice2;
	List<Integer> newSettlementIds;
	List<Integer> newRoadIds;
}
