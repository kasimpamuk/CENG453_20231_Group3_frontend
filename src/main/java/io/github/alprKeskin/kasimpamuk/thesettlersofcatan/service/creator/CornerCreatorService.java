package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.creator;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.SettlementCorner;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Tile;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.House.RED_HOUSE;

@Getter
@Setter
public class CornerCreatorService {

	private final Pane tileMap;
	private final List<Tile> tiles;
	private List<SettlementCorner> settlementCorners = new ArrayList<>();

	private final int[][] cornerTileMatrix = new int[19][54];


	public CornerCreatorService(Pane tileMap, List<Tile> tiles) {
		this.tileMap = tileMap;
		this.tiles = tiles;
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 54; j++) {
				cornerTileMatrix[i][j] = 0;
			}
		}
	}

	public void createCornerTileMatrix() {
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 54; j++) {
				for (int k = 0; k < 6; k++) {
					if (this.tiles.get(i).getCorners().get(k).equals(this.settlementCorners.get(j).getLocation())) {
						cornerTileMatrix[i][j] = 1;
					}
				}
			}
		}
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 54; j++) {
				System.out.print(cornerTileMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public List<SettlementCorner> createAllSettlementCorners() {
		List<Integer> tileList1 = new ArrayList<>(Arrays.asList(0, 2, 7, 9, 11, 16, 18));
		List<Integer> tileList2 = new ArrayList<>(Arrays.asList(1, 8, 10, 17));


		for (int i = 0; i < tileList1.size(); i++) {
			int tileId = tileList1.get(i);
			Tile tile = this.tiles.get(tileId);
			// create all corner buttons
			for (int j = 0; j < 6; j++) {
		//		cornerTileMatrix[tileId][SettlementCorner.cornerId] = 1;
				SettlementCorner settlementCorner = createSettlementButton(tile.getCorners().get(j));
				settlementCorners.add(settlementCorner);
			}
		}

		for (int i = 0; i < tileList2.size(); i++) {
			int tileId = tileList2.get(i);
			Tile tile = this.tiles.get(tileId);
			settlementCorners.add( createSettlementButton(tile.getTopCornerPoint()) );

			settlementCorners.add( createSettlementButton(tile.getBottomCornerPoint()) );
		}
		settlementCorners.add( createSettlementButton(this.tiles.get(3).getTopLeftCornerPoint()) );
		settlementCorners.add( createSettlementButton(this.tiles.get(6).getTopRightCornerPoint()) );
		settlementCorners.add( createSettlementButton(this.tiles.get(12).getBottomLeftCornerPoint()) );
		settlementCorners.add( createSettlementButton(this.tiles.get(15).getBottomRightCornerPoint()) );
		System.out.println("settlementCorners count: " + SettlementCorner.cornerId);

		createCornerTileMatrix();
		return settlementCorners;
	}

	private SettlementCorner createSettlementButton(Point point) {
		List<Integer> adjacentTileIds = getAdjacentTileIdsOfCorner(point);
		SettlementCorner settlementCorner = new SettlementCorner(SettlementCorner.cornerId++, point, adjacentTileIds, this.tileMap);
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

}
