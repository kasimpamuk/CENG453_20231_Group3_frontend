package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.SettlementCorner;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Tile;
import javafx.scene.layout.Pane;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Service
public class CornerService {

	private final List<SettlementCorner> settlementCorners = new ArrayList<>();
	// 19 tiles, 54 corners
	private final int[][] cornerTileMatrix = new int[19][54];

	private final ResourceBoxService resourceBoxService;

	@Autowired
	public CornerService(ResourceBoxService resourceBoxService) {
		this.resourceBoxService = resourceBoxService;
	}

	public void disableAllSettlementCornerButtons() {
		for (SettlementCorner settlementCorner : this.settlementCorners) {
			settlementCorner.disableButton();
		}
	}

	public void enableAllSettlementCornerButtons() {
		for (SettlementCorner settlementCorner : this.settlementCorners) {
			settlementCorner.enableButton();
		}
	}

	public void createCornerTileMatrix(List<Tile> tiles, Pane tileMap) {
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 54; j++) {
				cornerTileMatrix[i][j] = 0;
				for (int k = 0; k < 6; k++) {
					if (tiles.get(i).getCorners().get(k).equals(this.settlementCorners.get(j).getLocation())) {
						cornerTileMatrix[i][j] = 1;
					}
				}
			}
		}
		System.out.println("cornerTileMatrix");
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 54; j++) {
				System.out.print(cornerTileMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public List<SettlementCorner> createAllSettlementCorners(List<Tile> tiles, Pane tileMap) {
		List<Integer> tileList1 = new ArrayList<>(Arrays.asList(0, 2, 7, 9, 11, 16, 18));
		List<Integer> tileList2 = new ArrayList<>(Arrays.asList(1, 8, 10, 17));


		for (int i = 0; i < tileList1.size(); i++) {
			int tileId = tileList1.get(i);
			Tile tile = tiles.get(tileId);
			// create all corner buttons
			for (int j = 0; j < 6; j++) {
				//		cornerTileMatrix[tileId][SettlementCorner.cornerId] = 1;
				SettlementCorner settlementCorner = createSettlementButton(tile.getCorners().get(j), tiles, tileMap);
				settlementCorners.add(settlementCorner);
			}
		}

		for (int i = 0; i < tileList2.size(); i++) {
			int tileId = tileList2.get(i);
			Tile tile = tiles.get(tileId);
			settlementCorners.add( createSettlementButton(tile.getTopCornerPoint(), tiles, tileMap));

			settlementCorners.add( createSettlementButton(tile.getBottomCornerPoint(), tiles, tileMap));
		}
		settlementCorners.add( createSettlementButton(tiles.get(3).getTopLeftCornerPoint(), tiles, tileMap) );
		settlementCorners.add( createSettlementButton(tiles.get(6).getTopRightCornerPoint(), tiles, tileMap));
		settlementCorners.add( createSettlementButton(tiles.get(12).getBottomLeftCornerPoint(), tiles, tileMap));
		settlementCorners.add( createSettlementButton(tiles.get(15).getBottomRightCornerPoint(), tiles, tileMap));
		System.out.println("settlementCorners count: " + SettlementCorner.cornerId);
		SettlementCorner.cornerId = 0;

		createCornerTileMatrix(tiles, tileMap);
		return settlementCorners;
	}

	private SettlementCorner createSettlementButton(Point point, List<Tile> tiles, Pane tileMap) {
		List<Integer> adjacentTileIds = getAdjacentTileIdsOfCorner(point, tiles, tileMap);
		SettlementCorner settlementCorner = new SettlementCorner(SettlementCorner.cornerId++, point, adjacentTileIds, tileMap, resourceBoxService);
		tileMap.getChildren().add(settlementCorner.getButton());
		return settlementCorner;
	}

	private List<Integer> getAdjacentTileIdsOfCorner(Point point, List<Tile> tiles, Pane tileMap) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < tiles.size(); i++) {
			Tile tile = tiles.get(i);
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

}
