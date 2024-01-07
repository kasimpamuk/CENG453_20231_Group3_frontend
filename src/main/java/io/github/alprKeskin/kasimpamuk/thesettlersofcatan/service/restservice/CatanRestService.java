package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.restservice;

import com.google.gson.Gson;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.AuthenticationInformation;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.LoginResponse;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.PlayerActionInfo;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.request.RequestDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.InitialResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.ResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.Color;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.ResponseType;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.Edge;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.ui.SettlementCorner;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen.CornerService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen.EdgeService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.gamescreen.TileMapInitializationService;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.util.ClientInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
public class CatanRestService {

	private final RestService restService = new RestService();
//	private final String initialGameDataUrl = "http://localhost:8080/api/catan/initial-game-data";
//	private final String gameInfoUrl = "http://localhost:8080/api/catan/get-game-data";
//	private final String registerUrl = "http://localhost:8080/api/authentication/register";
//	private final String loginUrl = "http://localhost:8080/api/authentication/login";

	private final String initialGameDataUrl = "https://app-5xvs.onrender.com/api/catan/initial-game-data";
	private final String gameInfoUrl = "https://app-5xvs.onrender.com/api/catan/get-game-data";
	private final String registerUrl = "https://app-5xvs.onrender.com/api/authentication/register";
	private final String loginUrl = "https://app-5xvs.onrender.com/api/authentication/login";



	private final Gson gson = new Gson();

	private final TileMapInitializationService tileMapInitializationService;


	private String JWT_TOKEN;

	@Autowired
	public CatanRestService(TileMapInitializationService tileMapInitializationService) {
		this.tileMapInitializationService = tileMapInitializationService;
	}

	public InitialResponseDTO getInitialGameData() {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			return this.restService.sendGetRequest(client, this.getUri(initialGameDataUrl));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public ResponseDTO getGameInfo(RequestDTO requestDTO) {
		JWT_TOKEN = ClientInfo.token;
		String jsonRequestBody = gson.toJson(requestDTO);
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			while (true) {
				HttpPost request = new HttpPost(gameInfoUrl);
				request.addHeader("Authorization", "Bearer " + JWT_TOKEN);

				String response = null;
				try {
					request.setHeader("Accept", "application/json");
					request.setHeader("Content-type", "application/json");
					request.setEntity(new StringEntity(jsonRequestBody));
					response = client.execute(request, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				System.out.println("Response: " + response);

				ResponseDTO responseDTO = gson.fromJson(response, ResponseDTO.class);

				if (responseDTO.getResponseType() == ResponseType.WAIT) {
					log.info("Continue Polling for game info...");


					PlayerActionInfo receivedPlayerActionInfo = responseDTO.getPlayerActionInfo();
					if (receivedPlayerActionInfo.getPlayerId() != ClientInfo.idOfTheLastConsumedPlayerActionInfo) {
						return responseDTO;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
				else if (responseDTO.getResponseType() == ResponseType.YOUR_TURN) {
					log.info("Your turn!");
					//enableAllButtons();

					return responseDTO;
				}
				else {
					log.info("Finish Polling!");
					return responseDTO;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public ResponseDTO sendEndTurnMessage(RequestDTO requestDTO) {
		JWT_TOKEN = ClientInfo.token;
		String jsonRequestBody = gson.toJson(requestDTO);
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost request = new HttpPost(gameInfoUrl);
			request.addHeader("Authorization", "Bearer " + JWT_TOKEN);
			String response = null;
			try {
				request.setHeader("Accept", "application/json");
				request.setHeader("Content-type", "application/json");
				request.setEntity(new StringEntity(jsonRequestBody));
				response = client.execute(request, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println("Response: " + response);
			return gson.fromJson(response, ResponseDTO.class);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Boolean register(String email, String password) {
		AuthenticationInformation authenticationInformation = new AuthenticationInformation(email, password);
		String jsonRequestBody = gson.toJson(authenticationInformation);
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost request = new HttpPost(registerUrl);
			String response = null;
			try {
				request.setHeader("Accept", "application/json");
				request.setHeader("Content-type", "application/json");
				request.setEntity(new StringEntity(jsonRequestBody));
				response = client.execute(request, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println("Response: " + response);
			return gson.fromJson(response, Boolean.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// return token
	public String login(String email, String password) {
		AuthenticationInformation authenticationInformation = new AuthenticationInformation(email, password);
		String jsonRequestBody = gson.toJson(authenticationInformation);
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost request = new HttpPost(loginUrl);
			String response = null;
			try {
				request.setHeader("Accept", "application/json");
				request.setHeader("Content-type", "application/json");
				request.setEntity(new StringEntity(jsonRequestBody));
				response = client.execute(request, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println("Response: " + response);
			LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);
			return loginResponse.getToken();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//	public ResponseDTO getGameInfo(RequestDTO requestDTO) {
//		try (CloseableHttpClient client = HttpClients.createDefault()) {
//			while (true) {
//				ResponseDTO responseDTO = this.restService.sendPostRequest(client, this.getUri(gameInfoUrl), requestDTO);
//				if (responseDTO.getResponseType() == ResponseType.WAIT) {
//					log.info("Continue Polling for game info...");
//					Thread.sleep(4000);
//				}
//				else {
//					log.info("Finish Polling!");
//					return responseDTO;
//				}
//			}
//		} catch (IOException | InterruptedException e) {
//			throw new RuntimeException(e);
//		}
//	}

	private URI getUri(String url) {
		try {
			return new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}



//	Color color = receivedPlayerActionInfo.getPlayerColor();
//	List<Integer> newSettlementIds = receivedPlayerActionInfo.getNewSettlementIds();
//	List<Integer> newRoadIds = receivedPlayerActionInfo.getNewRoadIds();
//	List<SettlementCorner> settlementCorners = this.cornerService.getSettlementCorners();
//	List<Edge> edges = this.edgeService.getEdges();
//						for (int i = 0; i < newSettlementIds.size(); i++) {
//		int settlementId = newSettlementIds.get(i);
//		settlementCorners.get(settlementId).buildHouse(color);
//		}
//		for (int i = 0; i < newRoadIds.size(); i++) {
//		int roadId = newRoadIds.get(i);
//		edges.get(roadId).buildRoad(color);
//		}
