package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Point;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameScreenService {

	private final int WIDTH = 750;
	private final int HEIGHT = 750;
	private final Point BOARD_CENTER = new Point((double) WIDTH / 2, (double) HEIGHT / 2);
	@Getter
	private BorderPane boardPane;
	@Getter
	private Scene catanScene;

	private final TileMapService tileMapService;
	private final BottomConsoleService bottomConsoleService; // NOT DONE!

	@Autowired
	public GameScreenService(TileMapService tileMapService, BottomConsoleService bottomConsoleService) {
		this.tileMapService = tileMapService;
		this.bottomConsoleService = bottomConsoleService;
	}

	public void displayGameScreen() {
		this.boardPane = new BorderPane();
		this.tileMapService.resetTileMap();
		this.catanScene = new Scene(this.boardPane, WIDTH, HEIGHT);
		this.boardPane.setStyle("-fx-background-color: #87CEEB;");
		this.boardPane.setCenter(this.tileMapService.getTileMap());

		this.boardPane.setBottom(this.bottomConsoleService.createBottomConsole());
		this.tileMapService.initializeTileMap(BOARD_CENTER);

	}

}
