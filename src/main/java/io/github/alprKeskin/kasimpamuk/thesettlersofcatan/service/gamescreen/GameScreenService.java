package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.PlayerActionInfo;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.request.RequestDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.InitialResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.ResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.Color;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.RequestType;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.ResponseType;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Edge;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Point;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.SettlementCorner;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.restservice.CatanRestService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.util.ClientInfo;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameScreenService {

	private final int WIDTH = 750;
	private final int HEIGHT = 750;
	private final Point BOARD_CENTER = new Point((double) WIDTH / 2, (double) HEIGHT / 2);
	@Getter
	private BorderPane boardPane;
	@Getter
	private Scene catanScene;
	private Button endTurnButton;

	private final TileMapService tileMapService;
	private final BottomConsoleService bottomConsoleService;
	private final CatanRestService catanRestService;
	private final DiceBoxService diceBoxService;

	private final CornerService cornerService;
	private final EdgeService edgeService;

	@Autowired
	public GameScreenService(TileMapService tileMapService, BottomConsoleService bottomConsoleService, CatanRestService catanRestService, DiceBoxService diceBoxService, CornerService cornerService, EdgeService edgeService) {
		this.tileMapService = tileMapService;
		this.bottomConsoleService = bottomConsoleService;
		this.catanRestService = catanRestService;
		this.diceBoxService = diceBoxService;
		this.cornerService = cornerService;
		this.edgeService = edgeService;
	}

	public void displayGameScreen() {
		this.setClientInitialGameData();
		this.boardPane = new BorderPane();
		this.tileMapService.resetTileMap();
		this.catanScene = new Scene(this.boardPane, WIDTH, HEIGHT);
		this.boardPane.setStyle("-fx-background-color: #87CEEB;");
		this.boardPane.setCenter(this.tileMapService.getTileMap());

		this.boardPane.setBottom(this.bottomConsoleService.createBottomConsole());
		this.tileMapService.initializeTileMap(BOARD_CENTER);
		this.disableAllButtons();

		ResponseDTO responseDTO = this.getGameData();
	}

	private void setClientInitialGameData() {
		InitialResponseDTO initialResponseDTO = this.catanRestService.getInitialGameData();
		ClientInfo.gameId = initialResponseDTO.getGameId();
		ClientInfo.playerId = initialResponseDTO.getPlayerId();
		ClientInfo.playerColor = initialResponseDTO.getPlayerColor();
		ClientInfo.tileInfos = initialResponseDTO.getTileInfos();
	}

	private void disableAllButtons() {
		this.tileMapService.disableAllSettlementCornerButtons();
		this.tileMapService.disableAllRoadEdgeButtons();
	}

	private void enableAllButtons() {
		this.tileMapService.enableAllSettlementCornerButtons();
		this.tileMapService.enableAllRoadEdgeButtons();
	}

	private ResponseDTO getGameData() {
		PlayerActionInfo playerActionInfo = new PlayerActionInfo(ClientInfo.playerId, ClientInfo.playerColor, 0, 0, null, null);
		RequestDTO requestDTO = new RequestDTO(RequestType.GET_INFO, ClientInfo.gameId, playerActionInfo);

		pollGameInfo(requestDTO);

		return null;
	}

	private void pollGameInfo(RequestDTO requestDTO) {
		Task<ResponseDTO> pollingTask = new Task<>() {
			@Override
			protected ResponseDTO call() throws Exception {
				return catanRestService.getGameInfo(requestDTO);
			}
		};

		pollingTask.setOnSucceeded(event -> {
			// This will be executed on the JavaFX Application Thread
			ResponseDTO response = pollingTask.getValue();
			if (response.getResponseType() == ResponseType.YOUR_TURN) {
				enableAllButtons();
				this.bottomConsoleService.disappearWaitingIcon(bottomConsoleService.getVbox());
				endTurnButton = new Button("End Turn");
				endTurnButton.setOnAction(e -> {
					PlayerActionInfo playerActionInfo = new PlayerActionInfo(ClientInfo.playerId, ClientInfo.playerColor, diceBoxService.getDiceValue1(), diceBoxService.getDiceValue2(), ClientInfo.newSettlementIdsInRound, ClientInfo.newRoadIdsInRound);
					ClientInfo.newSettlementIdsInRound = new ArrayList<>();
					ClientInfo.newRoadIdsInRound = new ArrayList<>();
					RequestDTO requestDTO1 = new RequestDTO(RequestType.TURN_ROUND, ClientInfo.gameId, playerActionInfo);
					ResponseDTO responseDTO = this.catanRestService.sendEndTurnMessage(requestDTO1);
					System.out.println("Turn ended!");
					System.out.println(responseDTO);
					if (responseDTO.getResponseType() == ResponseType.WAIT) {
						disableAllButtons();
						this.bottomConsoleService.getVbox().getChildren().remove(endTurnButton);
						this.bottomConsoleService.showWaitingIcon(bottomConsoleService.getVbox());
					}

					PlayerActionInfo playerActionInfo2 = new PlayerActionInfo(ClientInfo.playerId, ClientInfo.playerColor, 0, 0, null, null);
					RequestDTO requestDTO2 = new RequestDTO(RequestType.GET_INFO, ClientInfo.gameId, playerActionInfo2);
					pollGameInfo(requestDTO2);
				});
				this.bottomConsoleService.getVbox().getChildren().add(endTurnButton);

				// concurrent
				if (response.getPlayerActionInfo() != null && response.getPlayerActionInfo().getPlayerId() != ClientInfo.idOfTheLastConsumedPlayerActionInfo) {
					PlayerActionInfo receivedPlayerActionInfo = response.getPlayerActionInfo();
					Color color = receivedPlayerActionInfo.getPlayerColor();
					List<Integer> newSettlementIds = receivedPlayerActionInfo.getNewSettlementIds();
					List<Integer> newRoadIds = receivedPlayerActionInfo.getNewRoadIds();
					List<SettlementCorner> settlementCorners = this.cornerService.getSettlementCorners();
					List<Edge> edges = this.edgeService.getEdges();
					for (int i = 0; i < newSettlementIds.size(); i++) {
						int settlementId = newSettlementIds.get(i);
						settlementCorners.get(settlementId).buildHouse(color);
					}
					for (int i = 0; i < newRoadIds.size(); i++) {
						int roadId = newRoadIds.get(i);
						edges.get(roadId).buildRoad(color);
					}
					System.out.println("HOOOOPPP");
					ClientInfo.idOfTheLastConsumedPlayerActionInfo = receivedPlayerActionInfo.getPlayerId();
				}
			}
			else if (response.getResponseType() == ResponseType.WAIT) {
				// Update your UI based on the response
				PlayerActionInfo receivedPlayerActionInfo = response.getPlayerActionInfo();
				Color color = receivedPlayerActionInfo.getPlayerColor();
				List<Integer> newSettlementIds = receivedPlayerActionInfo.getNewSettlementIds();
				List<Integer> newRoadIds = receivedPlayerActionInfo.getNewRoadIds();
				List<SettlementCorner> settlementCorners = this.cornerService.getSettlementCorners();
				List<Edge> edges = this.edgeService.getEdges();
				for (int i = 0; i < newSettlementIds.size(); i++) {
					int settlementId = newSettlementIds.get(i);
					settlementCorners.get(settlementId).buildHouse(color);
				}
				for (int i = 0; i < newRoadIds.size(); i++) {
					int roadId = newRoadIds.get(i);
					edges.get(roadId).buildRoad(color);
				}
				System.out.println("HOOOOPPP");
				ClientInfo.idOfTheLastConsumedPlayerActionInfo = receivedPlayerActionInfo.getPlayerId();

				PlayerActionInfo playerActionInfo2 = new PlayerActionInfo(ClientInfo.playerId, ClientInfo.playerColor, 0, 0, null, null);
				RequestDTO requestDTO2 = new RequestDTO(RequestType.GET_INFO, ClientInfo.gameId, playerActionInfo2);
				pollGameInfo(requestDTO2);
			}
			else {
				//
			}
		});

		pollingTask.setOnFailed(event -> {
			// Handle any exceptions
			Throwable e = pollingTask.getException();
			e.printStackTrace();
			// Update UI to reflect the error
		});

		// Start the task on a new thread
		new Thread(pollingTask).start();
	}



}
