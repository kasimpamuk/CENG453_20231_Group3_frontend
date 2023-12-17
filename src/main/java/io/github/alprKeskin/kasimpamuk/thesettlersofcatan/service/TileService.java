package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Tile;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.List;

import static io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain.DESERT;

public class TileService {

    private final int TILE_EDGE_SIZE = 50;
    private final Pane tileMap;
    private final List<Tile> tiles;
    private final List<Terrain> terrains;
    private final List<Integer> tileNumbers;
    private final Point BOARD_CENTER;

    public TileService(Pane tileMap, List<Tile> tiles, List<Terrain> terrains, List<Integer> tileNumbers, Point boardCenter) {
        this.tiles = tiles;
        this.tileMap = tileMap;
        this.terrains = terrains;
        this.tileNumbers = tileNumbers;
        BOARD_CENTER = boardCenter;
    }

    public void createAllTiles() {
        double distanceFromCenterToEdge = TILE_EDGE_SIZE * Math.sqrt(3) / 2;

        createTilesRow(3, 0, new Point(BOARD_CENTER.getX() - 2 * distanceFromCenterToEdge, BOARD_CENTER.getY() - 3 * TILE_EDGE_SIZE));
        createTilesRow(4, 3, new Point(BOARD_CENTER.getX() - 3 * distanceFromCenterToEdge, BOARD_CENTER.getY() - 1.5 * TILE_EDGE_SIZE));
        createTilesRow(5, 7, new Point(BOARD_CENTER.getX() - 4 * distanceFromCenterToEdge, BOARD_CENTER.getY()));
        createTilesRow(4, 12, new Point(BOARD_CENTER.getX() - 3 * distanceFromCenterToEdge, BOARD_CENTER.getY() + 1.5 * TILE_EDGE_SIZE));
        createTilesRow(3, 16, new Point(BOARD_CENTER.getX() - 2 * distanceFromCenterToEdge, BOARD_CENTER.getY() + 3 * TILE_EDGE_SIZE));
    }

    private void createTilesRow(int numberOfTiles, int startId, Point startPoint) {
        for (int i = 0; i < numberOfTiles; i++) {
            int id = startId + i;
            double distanceFromCenterToEdge = TILE_EDGE_SIZE * Math.sqrt(3) / 2;
            Point center = new Point(startPoint.getX() + i * distanceFromCenterToEdge * 2, startPoint.getY());
            if (id == 9) {
                Tile tile = createTile(id, DESERT, 0, center);
            }
            else {
                if (id > 9) {
                    Tile tile = createTile(id, terrains.get(id - 1), tileNumbers.get(id - 1), center);
                }
                else {
                    Tile tile = createTile(id, terrains.get(id), tileNumbers.get(id), center);
                }
            }
        }
    }

    private Tile createTile(int id, Terrain terrain, int number, Point center) {
        Tile tile =  new Tile(id, center, terrain, number, TILE_EDGE_SIZE);
        this.tiles.add(tile);
        this.tileMap.getChildren().add(tile.getHexagon());
        if (id != 9) {
            Text numberText = new Text(String.valueOf(number));
            numberText.setX(center.getX() - numberText.getBoundsInLocal().getWidth() / 2);
            numberText.setY(center.getY() + numberText.getBoundsInLocal().getHeight() / 4);
            this.tileMap.getChildren().add(numberText);
        }
        return tile;
    }

}
