package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.GameState;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Game {

	private int gameId;
	private GameState gameState = GameState.WAITING_FOR_PLAYERS;
	private int playerIdHavingTurn = 0;
	private List<Player> players = new ArrayList<>();
	private List<TileInfo> tileInfos = new ArrayList<>();
	private PlayerActionInfo lastAction = null;

	public void addPlayer(Player player) {
		if (this.players.size() >= 4) throw new RuntimeException("Attempt to add a player to a full game.");
		this.players.add(player);
		if (this.players.size() == 4) this.gameState = GameState.INITIALIZATION;
	}

	public boolean isReadyToStart() {
		return this.gameState == GameState.INITIALIZATION;
	}

	public Player giveRoundToNextPlayer() {
		int playerIndex = this.getPlayerIndexByPlayerId(this.playerIdHavingTurn);
		this.playerIdHavingTurn = this.getPlayerIdByIndex(playerIndex + 1);
		return this.getPlayerByPlayerId(this.playerIdHavingTurn);
	}

	public Player getPlayerByPlayerId(int playerId) {
		int playerIndex = this.getPlayerIndexByPlayerId(playerId);
		return this.players.get(playerIndex);
	}

	private int getPlayerIndexByPlayerId(int playerId) {
		return playerId % 4;
	}

	private int getPlayerIdByIndex(int index) {
		return this.gameId * 4 + (index % 4);
	}

}
