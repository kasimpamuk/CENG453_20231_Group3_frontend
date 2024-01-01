package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard.component;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Tile;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.TileMapService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.util.GameInitializationUtil;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TileMap {

	private final int WIDTH;
	private final int HEIGHT;
	private final Point BOARD_CENTER;

	private final Pane tileMap = new Pane();
	private List<Tile> tiles = new ArrayList<>();
	private final List<Terrain> terrains = GameInitializationUtil.createTerrainList();
	private final List<Integer> tileNumbers = GameInitializationUtil.createNumberList();
	private final TileMapService tileMapService;

	public TileMap(int width, int height, Point boardCenter) {
		WIDTH = width;
		HEIGHT = height;
		BOARD_CENTER = boardCenter;
		this.tileMapService = new TileMapService(this.tileMap, this.tiles, this.terrains, this.tileNumbers, this.BOARD_CENTER);
	}


	public void initializeTileMap() {
		this.initializePane();
		this.tileMapService.createTileMap();
	}

	private void initializePane() {
		this.tileMap.setStyle("-fx-background-color: #87CEEB;");
		tileMap.setPrefSize(WIDTH, HEIGHT);
	}

}
