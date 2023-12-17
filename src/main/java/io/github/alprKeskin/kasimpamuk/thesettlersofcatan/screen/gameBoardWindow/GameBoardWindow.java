package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.screen.gameBoardWindow;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Edge;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.SettlementCorner;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Tile;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.TileService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

import static io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain.*;

@Getter
@NoArgsConstructor
public class GameBoardWindow {

    private final int WIDTH = 1000;
    private final int HEIGHT = 1000;
    private final int TILE_EDGE_SIZE = 50;
    private Point BOARD_CENTER;

    private final BorderPane boardPane = new BorderPane();
    private final Pane tileMap = new Pane();
    private Scene catanScene;

    private List<Tile> tiles = new ArrayList<>();

    private final List<Terrain> terrains = createTerrainList();
    private final List<Integer> tileNumbers = createNumberList();

    private TileService tileService;

    public void display() {
        initializeTileMap();
        initializeBoardPane();
        initializeCatanScene();
        this.tileService = new TileService(this.tileMap, this.tiles, this.terrains, this.tileNumbers, this.BOARD_CENTER);
        initializeTileBoard();
    }


    private void initializeTileMap() {
        this.tileMap.setStyle("-fx-background-color: #87CEEB;");
        tileMap.setPrefSize(WIDTH, HEIGHT);
    }

    private void initializeBoardPane() {
        this.boardPane.setStyle("-fx-background-color: #87CEEB;");
        this.boardPane.setCenter(tileMap);

        // Bottom Console
        ResourceBox resourceBox = new ResourceBox();
        DiceBox diceBox = new DiceBox();
        HBox bottomConsole = new HBox(20, resourceBox.getResource_box(), diceBox.getDiceBox());
        bottomConsole.setAlignment(Pos.CENTER);
        this.boardPane.setBottom(bottomConsole);
        BOARD_CENTER = new Point((double) WIDTH / 2, (double) HEIGHT / 2);
    }

    private void initializeCatanScene() {
        this.catanScene = new Scene(this.boardPane, WIDTH, HEIGHT);
    }

    private void initializeTileBoard() {
        this.tileService.createAllTiles();
        createAllCornerButtons();
        Edge ex_edge = new Edge(0, tiles.get(10).getTopLeftCornerPoint(), tiles.get(10).getTopCornerPoint(), false, this.tileMap);
    }

    private ArrayList<Terrain> createTerrainList() {
        ArrayList<Terrain> terrains = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            terrains.add(HILL);
            terrains.add(MOUNTAIN);
        }
        for (int i = 0; i < 4; i++) {
            terrains.add(FOREST);
            terrains.add(FIELD);
            terrains.add(PASTURE);
        }
        Collections.shuffle(terrains);
        return terrains;
    }

    private ArrayList<Integer> createNumberList() {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(2, 12));
        for (int number : new int[]{3, 4, 5, 6, 8, 9, 10, 11}) {
            numbers.add(number);
            numbers.add(number);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    private SettlementCorner createSettlementButton(Point point) {
        List<Integer> adjacentTileIds = getAdjacentTileIdsOfCorner(point);
        SettlementCorner settlementCorner = new SettlementCorner(0, point, adjacentTileIds, this.tileMap);
        this.tileMap.getChildren().add(settlementCorner.getButton());
        return settlementCorner;
    }

    private List<Integer> getAdjacentTileIdsOfCorner(Point point) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < this.tiles.size(); i++) {
            Tile tile = this.tiles.get(i);
            for (int j = 0; j < tile.getCorners().size(); j++) {
                if (point.equals(tile.getCorners().get(j))) {
                    result.add(tile.getId());
                }
                else {
                    continue;
                }
            }
        }
        return result;
    }

    private void createAllCornerButtons() {
        List<Integer> tileList1 = new ArrayList<>(Arrays.asList(0, 2, 7, 9, 11, 16, 18));
        List<Integer> tileList2 = new ArrayList<>(Arrays.asList(1, 8, 10, 17));

        for (int i = 0; i < tileList1.size(); i++) {
            int tileId = tileList1.get(i);
            Tile tile = this.tiles.get(tileId);
            // create all corner buttons
            for (int j = 0; j < 6; j++) {
                SettlementCorner settlementCorner = createSettlementButton(tile.getCorners().get(j));
            }
        }

        for (int i = 0; i < tileList2.size(); i++) {
            int tileId = tileList2.get(i);
            Tile tile = this.tiles.get(tileId);
            createSettlementButton(tile.getTopCornerPoint());
            createSettlementButton(tile.getBottomCornerPoint());
        }

        createSettlementButton(this.tiles.get(3).getTopLeftCornerPoint());
        createSettlementButton(this.tiles.get(6).getTopRightCornerPoint());
        createSettlementButton(this.tiles.get(12).getBottomLeftCornerPoint());
        createSettlementButton(this.tiles.get(15).getBottomRightCornerPoint());
    }

}
