package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Edge;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.SettlementCorner;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Tile;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@Getter
public class EdgeService {

    private final int[][] edgeCornerMatrix = new int[72][54]; // 72 edges, 54 corners
    private final List<Edge> edges = new ArrayList<>();

    public void disableAllEdgeButtons() {
        for (Edge edge : this.edges) {
            edge.disableButton();
        }
    }

    public void enableAllEdgeButtons() {
        for (Edge edge : this.edges) {
            edge.enableButton();
        }
    }

    public void createAllEdges(Pane tileMapPane, List<Tile> tiles, List<SettlementCorner> settlementCorners) {
        List<Integer> tileIdsCreatingAllRoads = new ArrayList<>(Arrays.asList(0, 2, 7, 9, 11, 16, 18));
        List<Integer> tileIdsCreatingTopAndBottomRoads = new ArrayList<>(Arrays.asList(1, 8, 10, 17));


        int edgeId = 0;

        for (int i = 0; i < tileIdsCreatingAllRoads.size(); i++) {
            Tile tile = tiles.get( tileIdsCreatingAllRoads.get(i) );
            // create all tile edges
            for (int j = 0; j < 6; j++) {
                Edge edge = createEdge(edgeId++, tile.getCorners().get(j), tile.getCorners().get(this.getModulo(6, j + 1)), tileMapPane);
                //edgeCornerMatrix.add(Arrays.asList(edge.getId(), tile.getCorners().get(j).getId(), tile.getCorners().get(this.getModulo(6, j + 1)).getId()));
                edges.add(edge);
            }
        }

        for (int i = 0; i < tileIdsCreatingTopAndBottomRoads.size(); i++) {
            Tile tile = tiles.get( tileIdsCreatingTopAndBottomRoads.get(i) );
            edges.add(createEdge(edgeId++, tile.getTopCornerPoint(), tile.getTopRightCornerPoint(), tileMapPane));
            edges.add(createEdge(edgeId++, tile.getBottomRightCornerPoint(), tile.getBottomCornerPoint(), tileMapPane));
            edges.add(createEdge(edgeId++, tile.getBottomCornerPoint(), tile.getBottomLeftCornerPoint(), tileMapPane));
            edges.add(createEdge(edgeId++, tile.getTopLeftCornerPoint(), tile.getTopCornerPoint(), tileMapPane));
        }

        edges.add( tiles.get(3).createLeftEdge(edgeId++, tileMapPane));
        edges.add( tiles.get(3).createTopLeftEdge(edgeId++, tileMapPane));

        edges.add( tiles.get(6).createTopRightEdge(edgeId++, tileMapPane));
        edges.add( tiles.get(6).createRightEdge(edgeId++, tileMapPane));

        edges.add( tiles.get(12).createBottomLeftEdge(edgeId++, tileMapPane));
        edges.add( tiles.get(12).createLeftEdge(edgeId++, tileMapPane));

        edges.add( tiles.get(15).createRightEdge(edgeId++, tileMapPane));
        edges.add( tiles.get(15).createBottomRightEdge(edgeId++, tileMapPane));


        edges.add(tiles.get(3).createRightEdge(edgeId++, tileMapPane));
        edges.add(tiles.get(4).createRightEdge(edgeId++, tileMapPane));
        edges.add(tiles.get(5).createRightEdge(edgeId++, tileMapPane));
        edges.add(tiles.get(12).createRightEdge(edgeId++, tileMapPane));
        edges.add(tiles.get(13).createRightEdge(edgeId++, tileMapPane));
        edges.add(tiles.get(14).createRightEdge(edgeId++, tileMapPane));

        createEdgeCornerMatrix(settlementCorners);
    }

    private void createEdgeCornerMatrix(List<SettlementCorner> settlementCorners) {

        for(int i = 0; i < 72; i++) {
            for (int j = 0; j < 54; j++) {
                if (this.edges.get(i).getLocation1().equals(settlementCorners.get(j).getLocation()) || this.edges.get(i).getLocation2().equals(settlementCorners.get(j).getLocation())) {
                    edgeCornerMatrix[i][j] = 1;
                }
                else {
                    edgeCornerMatrix[i][j] = 0;
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
