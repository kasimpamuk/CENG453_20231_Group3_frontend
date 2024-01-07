package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.util;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.TerrainType;

import java.util.ArrayList;
import java.util.List;

public class GameInitializationUtil {

	public static List<TerrainType> createTerrainList() {
		List<TerrainType> terrainTypes = new ArrayList<>();
		for (int i = 0; i < ClientInfo.tileInfos.size(); i++) {
			terrainTypes.add(ClientInfo.tileInfos.get(i).getTerrainType());
		}
		return terrainTypes;
	}

	public static List<Integer> createNumberList() {
		List<Integer> numberList = new ArrayList<>();
		for (int i = 0; i < ClientInfo.tileInfos.size(); i++) {
			numberList.add(ClientInfo.tileInfos.get(i).getNumber());
		}
		return numberList;
	}

}
