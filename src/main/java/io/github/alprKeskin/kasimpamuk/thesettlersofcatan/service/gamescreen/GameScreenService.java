package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.uı.Point;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameScreenService {

	private final int WIDTH = 750;
	private final int HEIGHT = 750;
	private final Point BOARD_CENTER = new Point((double) WIDTH / 2, (double) HEIGHT / 2);

	private BorderPane boardPane = new BorderPane();
	@Getter
	private Scene catanScene = new Scene(this.boardPane, WIDTH, HEIGHT);

	private final TileMapService tileMapService;
	private final BottomConsoleService bottomConsoleService;

	@Autowired
	public GameScreenService(TileMapService tileMapService, BottomConsoleService bottomConsoleService) {
		this.tileMapService = tileMapService;
		this.bottomConsoleService = bottomConsoleService;
	}

	public void displayGameScreen() {
		this.boardPane.setStyle("-fx-background-color: #87CEEB;");
		this.boardPane.setCenter(this.tileMapService.getTileMap());

		this.boardPane.setBottom(this.bottomConsoleService.createBottomConsole());
		this.tileMapService.initializeTileMap(BOARD_CENTER);
	}

	public void resetBoard() {
		this.boardPane = new BorderPane();
		this.boardPane.setStyle("-fx-background-color: #87CEEB;"); // Set the default style if needed
		this.catanScene.setRoot(this.boardPane); // Update the scene root
	}

}
