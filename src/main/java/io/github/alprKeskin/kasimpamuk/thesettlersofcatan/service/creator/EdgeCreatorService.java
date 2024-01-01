package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.creator;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Edge;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.SettlementCorner;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Tile;
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

	public EdgeCreatorService(Pane tileMapPane, List<Tile> tiles) {
		this.tileMapPane = tileMapPane;
		this.tiles = tiles;
	}

	public List<Edge> createAllEdges() {
		List<Integer> tileIdsCreatingAllRoads = new ArrayList<>(Arrays.asList(0, 2, 7, 9, 11, 16, 18));
		List<Integer> tileIdsCreatingTopAndBottomRoads = new ArrayList<>(Arrays.asList(1, 8, 10, 17));

		List<Edge> edges = new ArrayList<>();

		for (int i = 0; i < tileIdsCreatingAllRoads.size(); i++) {
			Tile tile = this.tiles.get( tileIdsCreatingAllRoads.get(i) );
			// create all tile edges
			for (int j = 0; j < 6; j++) {
				Edge edge = this.createEdge(0, tile.getCorners().get(j), tile.getCorners().get(this.getModulo(6, j + 1)));
				edges.add(edge);
			}
		}

		for (int i = 0; i < tileIdsCreatingTopAndBottomRoads.size(); i++) {
			Tile tile = this.tiles.get( tileIdsCreatingTopAndBottomRoads.get(i) );
			edges.add(createEdge(1, tile.getTopCornerPoint(), tile.getTopRightCornerPoint()));
			edges.add(createEdge(1, tile.getBottomRightCornerPoint(), tile.getBottomCornerPoint()));
			edges.add(createEdge(1, tile.getBottomCornerPoint(), tile.getBottomLeftCornerPoint()));
			edges.add(createEdge(1, tile.getTopLeftCornerPoint(), tile.getTopCornerPoint()));
		}

		edges.add( this.tiles.get(3).createLeftEdge(0, this.tileMapPane));
		edges.add( this.tiles.get(3).createTopLeftEdge(0, this.tileMapPane));

		edges.add( this.tiles.get(6).createTopRightEdge(0, this.tileMapPane));
		edges.add( this.tiles.get(6).createRightEdge(0, this.tileMapPane));

		edges.add( this.tiles.get(12).createBottomLeftEdge(0, this.tileMapPane));
		edges.add( this.tiles.get(12).createLeftEdge(0, this.tileMapPane));

		edges.add( this.tiles.get(15).createRightEdge(0, this.tileMapPane));
		edges.add( this.tiles.get(15).createBottomRightEdge(0, this.tileMapPane));


		edges.add(this.tiles.get(3).createRightEdge(0, this.tileMapPane));
		edges.add(this.tiles.get(4).createRightEdge(0, this.tileMapPane));
		edges.add(this.tiles.get(5).createRightEdge(0, this.tileMapPane));
		edges.add(this.tiles.get(12).createRightEdge(0, this.tileMapPane));
		edges.add(this.tiles.get(13).createRightEdge(0, this.tileMapPane));
		edges.add(this.tiles.get(14).createRightEdge(0, this.tileMapPane));

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
