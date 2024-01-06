package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.creator;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Edge;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Tile;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.TileMapService;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Slf4j
@Service
public class EdgeCreatorService {

	private final List<Tile> tiles;
	private int[][] edgeCornerMatrix = new int[72][54]; // 72 edges, 54 corners
	private List<Edge> edges = new ArrayList<>();

	public EdgeCreatorService(List<Tile> tiles) {
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



	public List<Edge> createAllEdges(Pane tileMapPane) {
		List<Integer> tileIdsCreatingAllRoads = new ArrayList<>(Arrays.asList(0, 2, 7, 9, 11, 16, 18));
		List<Integer> tileIdsCreatingTopAndBottomRoads = new ArrayList<>(Arrays.asList(1, 8, 10, 17));


		int edgeId = 0;

		for (int i = 0; i < tileIdsCreatingAllRoads.size(); i++) {
			Tile tile = this.tiles.get( tileIdsCreatingAllRoads.get(i) );
			// create all tile edges
			for (int j = 0; j < 6; j++) {
				Edge edge = this.createEdge(edgeId++, tile.getCorners().get(j), tile.getCorners().get(this.getModulo(6, j + 1)), tileMapPane);
				//edgeCornerMatrix.add(Arrays.asList(edge.getId(), tile.getCorners().get(j).getId(), tile.getCorners().get(this.getModulo(6, j + 1)).getId()));
				edges.add(edge);
			}
		}

		for (int i = 0; i < tileIdsCreatingTopAndBottomRoads.size(); i++) {
			Tile tile = this.tiles.get( tileIdsCreatingTopAndBottomRoads.get(i) );
			edges.add(createEdge(edgeId++, tile.getTopCornerPoint(), tile.getTopRightCornerPoint(), tileMapPane));
			edges.add(createEdge(edgeId++, tile.getBottomRightCornerPoint(), tile.getBottomCornerPoint(), tileMapPane));
			edges.add(createEdge(edgeId++, tile.getBottomCornerPoint(), tile.getBottomLeftCornerPoint(), tileMapPane));
			edges.add(createEdge(edgeId++, tile.getTopLeftCornerPoint(), tile.getTopCornerPoint(), tileMapPane));
		}

		edges.add( this.tiles.get(3).createLeftEdge(edgeId++, tileMapPane));
		edges.add( this.tiles.get(3).createTopLeftEdge(edgeId++, tileMapPane));

		edges.add( this.tiles.get(6).createTopRightEdge(edgeId++, tileMapPane));
		edges.add( this.tiles.get(6).createRightEdge(edgeId++, tileMapPane));

		edges.add( this.tiles.get(12).createBottomLeftEdge(edgeId++, tileMapPane));
		edges.add( this.tiles.get(12).createLeftEdge(edgeId++, tileMapPane));

		edges.add( this.tiles.get(15).createRightEdge(edgeId++, tileMapPane));
		edges.add( this.tiles.get(15).createBottomRightEdge(edgeId++, tileMapPane));


		edges.add(this.tiles.get(3).createRightEdge(edgeId++, tileMapPane));
		edges.add(this.tiles.get(4).createRightEdge(edgeId++, tileMapPane));
		edges.add(this.tiles.get(5).createRightEdge(edgeId++, tileMapPane));
		edges.add(this.tiles.get(12).createRightEdge(edgeId++, tileMapPane));
		edges.add(this.tiles.get(13).createRightEdge(edgeId++, tileMapPane));
		edges.add(this.tiles.get(14).createRightEdge(edgeId++, tileMapPane));

		createEdgeCornerMatrix();
		return edges;
	}

	private Edge createEdge(int id, Point point1, Point point2, Pane tileMapPane) {
		Edge edge = new Edge(id, point1, point2, tileMapPane);
		tileMapPane.getChildren().add(edge.getButton());
		return edge;
	}

	private int getModulo(int a, int b) {
		while (!(b < a)) {
			b = b - a;
		}
		return b;
	}

}
