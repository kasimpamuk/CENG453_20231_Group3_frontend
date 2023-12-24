package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Tile;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.TileService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class GameBoard {

    // constants
    private final int WIDTH = 1000;
    private final int HEIGHT = 1000;
    private Point BOARD_CENTER = new Point((double) WIDTH / 2, (double) HEIGHT / 2);

    private final BorderPane boardPane = new BorderPane();
    private Scene catanScene;

    private TileMap tileMap = new TileMap(WIDTH, HEIGHT, BOARD_CENTER);
    private BottomConsole bottomConsole = new BottomConsole();

    public void display() {
        initializeBoardPane();
        initializeCatanScene();

        this.bottomConsole.initializeBottomConsole(this.boardPane);
        this.tileMap.initializeTileMap();
    }

    private void initializeBoardPane() {
        this.boardPane.setStyle("-fx-background-color: #87CEEB;");
        this.boardPane.setCenter(this.tileMap.getTileMap());
    }

    private void initializeCatanScene() {
        this.catanScene = new Scene(this.boardPane, WIDTH, HEIGHT);
    }


}
