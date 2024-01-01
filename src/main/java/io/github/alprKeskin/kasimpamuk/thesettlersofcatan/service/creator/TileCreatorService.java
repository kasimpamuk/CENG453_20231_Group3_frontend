package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.creator;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Tile;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain.DESERT;

@Getter
@Setter
public class TileCreatorService {

	private final int TILE_EDGE_SIZE = 50;
	private final Pane tileMap;
	private final List<Tile> tiles;
	private final List<Terrain> terrains;
	private final List<Integer> tileNumbers;
	private final Point BOARD_CENTER;

	public TileCreatorService(Pane tileMap, List<Tile> tiles, List<Terrain> terrains, List<Integer> tileNumbers, Point boardCenter) {
		this.tileMap = tileMap;
		this.tiles = tiles;
		this.terrains = terrains;
		this.tileNumbers = tileNumbers;
		BOARD_CENTER = boardCenter;
	}

	public List<Tile> createAllTiles() {
		List<Tile> allTiles = new ArrayList<>();
		double distanceFromCenterToEdge = TILE_EDGE_SIZE * Math.sqrt(3) / 2;

		allTiles.addAll(
			createTilesRow(3, 0, new Point(BOARD_CENTER.getX() - 2 * distanceFromCenterToEdge, BOARD_CENTER.getY() - 3 * TILE_EDGE_SIZE))
		);
		allTiles.addAll(
			createTilesRow(4, 3, new Point(BOARD_CENTER.getX() - 3 * distanceFromCenterToEdge, BOARD_CENTER.getY() - 1.5 * TILE_EDGE_SIZE))
		);
		allTiles.addAll(
			createTilesRow(5, 7, new Point(BOARD_CENTER.getX() - 4 * distanceFromCenterToEdge, BOARD_CENTER.getY()))
		);
		allTiles.addAll(
			createTilesRow(4, 12, new Point(BOARD_CENTER.getX() - 3 * distanceFromCenterToEdge, BOARD_CENTER.getY() + 1.5 * TILE_EDGE_SIZE))
		);
		allTiles.addAll(
			createTilesRow(3, 16, new Point(BOARD_CENTER.getX() - 2 * distanceFromCenterToEdge, BOARD_CENTER.getY() + 3 * TILE_EDGE_SIZE))
		);

		return allTiles;
	}

	private List<Tile> createTilesRow(int numberOfTiles, int startId, Point startPoint) {
		List<Tile> tilesInRow = new ArrayList<>();
		for (int i = 0; i < numberOfTiles; i++) {
			int id = startId + i;
			double distanceFromCenterToEdge = TILE_EDGE_SIZE * Math.sqrt(3) / 2;
			Point center = new Point(startPoint.getX() + i * distanceFromCenterToEdge * 2, startPoint.getY());
			if (id == 9) {
				Tile tile = createTile(id, DESERT, 0, center);
				tilesInRow.add(tile);
			}
			else {
				if (id > 9) {
					Tile tile = createTile(id, terrains.get(id - 1), tileNumbers.get(id - 1), center);
					tilesInRow.add(tile);
				}
				else {
					Tile tile = createTile(id, terrains.get(id), tileNumbers.get(id), center);
					tilesInRow.add(tile);
				}
			}
		}
		return tilesInRow;
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
