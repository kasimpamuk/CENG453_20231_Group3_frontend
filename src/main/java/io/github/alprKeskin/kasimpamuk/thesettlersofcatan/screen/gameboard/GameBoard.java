package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard.component.BottomConsole;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameboard.component.TileMap;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameBoard {

    private final int WIDTH = 750;
    private final int HEIGHT = 750;
    private final Point BOARD_CENTER = new Point((double) WIDTH / 2, (double) HEIGHT / 2);
    private final BorderPane boardPane = new BorderPane();
    private final Scene catanScene = new Scene(this.boardPane, WIDTH, HEIGHT);

    private TileMap tileMap = new TileMap(WIDTH, HEIGHT, BOARD_CENTER);
    private BottomConsole bottomConsole = new BottomConsole();

    public void display() {
        this.boardPane.setStyle("-fx-background-color: #87CEEB;");
        this.boardPane.setCenter(this.tileMap.getTileMap());

        this.bottomConsole.initializeBottomConsole(this.boardPane);
        this.tileMap.initializeTileMap();
    }

}
