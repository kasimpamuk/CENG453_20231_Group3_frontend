package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Edge;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.Tile;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.enums.Terrain.DESERT;

@Getter
@Setter
public class TileService {

    private final Point BOARD_CENTER;

    private final Pane tileMap;
    private final List<Tile> tiles;
    private final List<Terrain> terrains;
    private final List<Integer> tileNumbers;

    private final TileCreatorService tileCreatorService;
    private final CornerCreatorService cornerCreatorService;

    public TileService(Pane tileMap, List<Tile> tiles, List<Terrain> terrains, List<Integer> tileNumbers, Point boardCenter) {
        this.tiles = tiles;
        this.tileMap = tileMap;
        this.terrains = terrains;
        this.tileNumbers = tileNumbers;
        BOARD_CENTER = boardCenter;

        this.tileCreatorService = new TileCreatorService(this.tileMap, this.tiles, this.terrains, this.tileNumbers, this.BOARD_CENTER);
        this.cornerCreatorService = new CornerCreatorService(this.tileMap, this.tiles);
    }

    public void createTileMap() {
        this.tileCreatorService.createAllTiles();
        this.cornerCreatorService.createAllCornerButtons();
        Edge ex_edge = new Edge(0, tiles.get(10).getTopLeftCornerPoint(), tiles.get(10).getTopCornerPoint(), false, this.tileMap);
    }

}
