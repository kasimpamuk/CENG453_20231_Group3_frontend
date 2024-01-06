package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.uı.Point;
import javafx.scene.layout.Pane;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class TileMapService {

	private final TileMapInitializationService tileMapInitializationService;
	private final CornerService cornerService;
	private final Pane tileMap;


	@Autowired
	public TileMapService(TileMapInitializationService tileMapInitializationService, CornerService cornerService) {
		this.tileMapInitializationService = tileMapInitializationService;
		this.cornerService = cornerService;
		this.tileMap = this.tileMapInitializationService.getTileMap();
	}

	public void initializeTileMap(Point boardCenter) {
		this.tileMapInitializationService.createAllTiles(boardCenter);
		this.cornerService.createAllSettlementCorners(this.tileMapInitializationService.getTiles(), this.tileMap);
	}

}
