package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.TerrainType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TileInfo {

	private int id;
	private int number;
	private TerrainType terrainType;

}
