package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.TileInfo;
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
public class InitialResponseDTO {

	private int gameId;
	private int playerId;
	private Color playerColor;
	private List<TileInfo> tileInfos;

}
