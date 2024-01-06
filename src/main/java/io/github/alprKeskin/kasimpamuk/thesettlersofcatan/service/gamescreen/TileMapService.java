package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Edge;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Tile;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.creator.EdgeCreatorService;
import javafx.scene.layout.Pane;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class TileMapService {

	private List<Tile> tiles = new ArrayList<>();

	private final TileMapInitializationService tileMapInitializationService;
	private final CornerService cornerService;
	private final EdgeService edgeService;
	private final Pane tileMap;


	@Autowired
	public TileMapService(TileMapInitializationService tileMapInitializationService, CornerService cornerService, EdgeCreatorService edgeCreatorService, EdgeService edgeService) {
		this.tileMapInitializationService = tileMapInitializationService;
		this.cornerService = cornerService;
		this.edgeService = edgeService;
		this.tileMap = this.tileMapInitializationService.getTileMap();
	}

	public void initializeTileMap(Point boardCenter) {
		this.tiles = this.tileMapInitializationService.createAllTiles(boardCenter);
		this.cornerService.createAllSettlementCorners(this.tiles, this.tileMap);
		this.edgeService.createAllEdges(this.tileMap, this.tiles, this.cornerService.getSettlementCorners());
	}

	public void resetTileMap() {
		this.tileMap.getChildren().clear();
		this.tiles.clear();
		this.cornerService.getSettlementCorners().clear();
	}

}
