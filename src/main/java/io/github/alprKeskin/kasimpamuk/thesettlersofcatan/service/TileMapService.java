package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Edge;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.SettlementCorner;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Tile;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.enums.Terrain;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.creator.CornerCreatorService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.creator.EdgeCreatorService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.creator.TileCreatorService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen.CornerService;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TileMapService {

    private final Point BOARD_CENTER;

    private final Pane tileMapPane;
    private List<Tile> tiles;
    public static List<SettlementCorner> settlementCorners;
    private List<Edge> edges;

    private final List<Terrain> terrains;
    private final List<Integer> tileNumbers;

    private final TileCreatorService tileCreatorService;
    private final CornerCreatorService cornerCreatorService;
    private final EdgeCreatorService edgeCreatorService;
    private CornerService cornerService;

    public TileMapService(Pane tileMap, List<Tile> tiles, List<Terrain> terrains, List<Integer> tileNumbers, Point boardCenter) {
        this.tiles = tiles;
        this.tileMapPane = tileMap;
        this.terrains = terrains;
        this.tileNumbers = tileNumbers;
        BOARD_CENTER = boardCenter;

        this.tileCreatorService = new TileCreatorService(this.tileMapPane, this.tiles, this.terrains, this.tileNumbers, this.BOARD_CENTER);
        this.cornerCreatorService = new CornerCreatorService(this.tileMapPane, this.tiles);
        this.edgeCreatorService = new EdgeCreatorService(this.tiles);
    }

    public void createTileMap() {
        this.tiles = this.tileCreatorService.createAllTiles();
        this.settlementCorners = this.cornerCreatorService.createAllSettlementCorners();
//        this.cornerService = new CornerService(this.settlementCorners);
        // this.cornerService.disableAllSettlementCornerButtons();
        this.edges = this.edgeCreatorService.createAllEdges(this.tileMapPane);
    }

}
