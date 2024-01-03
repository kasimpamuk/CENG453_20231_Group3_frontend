package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestDTO {

	private int playerId;
	private int dice1;
	private int dice2;
	private List<Integer> newSettlementIds;
	private List<Integer> newRoadIds;

}
