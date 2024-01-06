package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.uı;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TileMap {

	private int WIDTH;
	private int HEIGHT;
	private Point BOARD_CENTER;

	private List<Tile> tiles = new ArrayList<>();
	public List<SettlementCorner> settlementCorners;
	private List<Edge> edges;

}
