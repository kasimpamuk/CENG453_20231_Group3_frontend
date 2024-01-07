package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.util;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.PlayerActionInfo;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.ResourceInfo;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.TileInfo;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.Color;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.enums.Resource;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ClientInfo {

	public static String token = " ";
	public static int gameId;
	public static int playerId;
	public static Color playerColor;
	public static List<TileInfo> tileInfos;

	public static PlayerActionInfo playerActionInfo = new PlayerActionInfo();

	public static List<Integer> newSettlementIdsInRound = new ArrayList<>();
	public static List<Integer> newRoadIdsInRound = new ArrayList<>();
	public static Integer idOfTheLastConsumedPlayerActionInfo = -1;
	public static ResourceInfo myResourceInfo = new ResourceInfo();


}