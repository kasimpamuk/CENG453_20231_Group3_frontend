package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.creator;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Edge;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.SettlementCorner;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Tile;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.TileMapService;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Slf4j
public class EdgeCreatorService {

	private final Pane tileMapPane;
	private final List<Tile> tiles;
	private int[][] edgeCornerMatrix = new int[72][54]; // 72 edges, 54 corners
	private List<Edge> edges = new ArrayList<>();

	public EdgeCreatorService(Pane tileMapPane, List<Tile> tiles) {
		this.tileMapPane = tileMapPane;
		this.tiles = tiles;
		for(int i = 0; i <72; i++) {
			for(int j = 0; j < 54; j++) {
				edgeCornerMatrix[i][j] = 0;
			}
		}
	}

	public void createEdgeCornerMatrix() {
		for(int i = 0; i < 72; i++) {
			for (int j = 0; j < 54; j++) {
				if (this.edges.get(i).getLocation1().equals(TileMapService.settlementCorners.get(j).getLocation()) || this.edges.get(i).getLocation2().equals(TileMapService.settlementCorners.get(j).getLocation())) {
					edgeCornerMatrix[i][j] = 1;
				}
			}
		}

		System.out.println("edgeCornerMatrix");
		for(int j = 0; j < 54; j++) {
			for(int i = 0; i < 72; i++) {
				System.out.print(edgeCornerMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}



	public List<Edge> createAllEdges() {
		List<Integer> tileIdsCreatingAllRoads = new ArrayList<>(Arrays.asList(0, 2, 7, 9, 11, 16, 18));
		List<Integer> tileIdsCreatingTopAndBottomRoads = new ArrayList<>(Arrays.asList(1, 8, 10, 17));


		int edgeId = 0;

		for (int i = 0; i < tileIdsCreatingAllRoads.size(); i++) {
			Tile tile = this.tiles.get( tileIdsCreatingAllRoads.get(i) );
			// create all tile edges
			for (int j = 0; j < 6; j++) {
				Edge edge = this.createEdge(edgeId++, tile.getCorners().get(j), tile.getCorners().get(this.getModulo(6, j + 1)));
				//edgeCornerMatrix.add(Arrays.asList(edge.getId(), tile.getCorners().get(j).getId(), tile.getCorners().get(this.getModulo(6, j + 1)).getId()));
				edges.add(edge);
			}
		}

		for (int i = 0; i < tileIdsCreatingTopAndBottomRoads.size(); i++) {
			Tile tile = this.tiles.get( tileIdsCreatingTopAndBottomRoads.get(i) );
			edges.add(createEdge(edgeId++, tile.getTopCornerPoint(), tile.getTopRightCornerPoint()));
			edges.add(createEdge(edgeId++, tile.getBottomRightCornerPoint(), tile.getBottomCornerPoint()));
			edges.add(createEdge(edgeId++, tile.getBottomCornerPoint(), tile.getBottomLeftCornerPoint()));
			edges.add(createEdge(edgeId++, tile.getTopLeftCornerPoint(), tile.getTopCornerPoint()));
		}

		edges.add( this.tiles.get(3).createLeftEdge(edgeId++, this.tileMapPane));
		edges.add( this.tiles.get(3).createTopLeftEdge(edgeId++, this.tileMapPane));

		edges.add( this.tiles.get(6).createTopRightEdge(edgeId++, this.tileMapPane));
		edges.add( this.tiles.get(6).createRightEdge(edgeId++, this.tileMapPane));

		edges.add( this.tiles.get(12).createBottomLeftEdge(edgeId++, this.tileMapPane));
		edges.add( this.tiles.get(12).createLeftEdge(edgeId++, this.tileMapPane));

		edges.add( this.tiles.get(15).createRightEdge(edgeId++, this.tileMapPane));
		edges.add( this.tiles.get(15).createBottomRightEdge(edgeId++, this.tileMapPane));


		edges.add(this.tiles.get(3).createRightEdge(edgeId++, this.tileMapPane));
		edges.add(this.tiles.get(4).createRightEdge(edgeId++, this.tileMapPane));
		edges.add(this.tiles.get(5).createRightEdge(edgeId++, this.tileMapPane));
		edges.add(this.tiles.get(12).createRightEdge(edgeId++, this.tileMapPane));
		edges.add(this.tiles.get(13).createRightEdge(edgeId++, this.tileMapPane));
		edges.add(this.tiles.get(14).createRightEdge(edgeId++, this.tileMapPane));
		//System.out.println("edge size: " + edges.size());
		//System.out.println(edgeId);


		createEdgeCornerMatrix();
		return edges;
	}

	private Edge createEdge(int id, Point point1, Point point2) {
		Edge edge = new Edge(id, point1, point2, this.tileMapPane);
		this.tileMapPane.getChildren().add(edge.getButton());
		return edge;
	}

	private int getModulo(int a, int b) {
		while (!(b < a)) {
			b = b - a;
		}
		return b;
	}

}
