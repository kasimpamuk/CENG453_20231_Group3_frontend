package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.uı.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.uı.Tile;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.uı.enums.Terrain;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.util.GameInitializationUtil;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.uı.enums.Terrain.DESERT;

@Service
@Getter
public class TileMapInitializationService {

	private final int TILE_EDGE_SIZE = 50;
	private final List<Tile> tiles = new ArrayList<>();
	private final Pane tileMap = new Pane();

	public List<Tile> createAllTiles(Point boardCenter) {
		List<Tile> allTiles = new ArrayList<>();
		double distanceFromCenterToEdge = TILE_EDGE_SIZE * Math.sqrt(3) / 2;

		allTiles.addAll(
				createTilesRow(3, 0, new Point(boardCenter.getX() - 2 * distanceFromCenterToEdge, boardCenter.getY() - 3 * TILE_EDGE_SIZE))
		);
		allTiles.addAll(
				createTilesRow(4, 3, new Point(boardCenter.getX() - 3 * distanceFromCenterToEdge, boardCenter.getY() - 1.5 * TILE_EDGE_SIZE))
		);
		allTiles.addAll(
				createTilesRow(5, 7, new Point(boardCenter.getX() - 4 * distanceFromCenterToEdge, boardCenter.getY()))
		);
		allTiles.addAll(
				createTilesRow(4, 12, new Point(boardCenter.getX() - 3 * distanceFromCenterToEdge, boardCenter.getY() + 1.5 * TILE_EDGE_SIZE))
		);
		allTiles.addAll(
				createTilesRow(3, 16, new Point(boardCenter.getX() - 2 * distanceFromCenterToEdge, boardCenter.getY() + 3 * TILE_EDGE_SIZE))
		);

		return allTiles;
	}

	private List<Tile> createTilesRow(int numberOfTiles, int startId, Point startPoint) {
		List<Tile> tilesInRow = new ArrayList<>();

		List<Terrain> terrains = GameInitializationUtil.createTerrainList();
		List<Integer> tileNumbers = GameInitializationUtil.createNumberList();

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
