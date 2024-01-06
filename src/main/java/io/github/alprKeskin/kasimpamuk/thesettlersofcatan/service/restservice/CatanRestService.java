package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.restservice;

import com.google.gson.Gson;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.request.RequestDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.InitialResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.ResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.ResponseType;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@Slf4j
public class CatanRestService {

	private final RestService restService = new RestService();
	private final String url = "http://localhost:8080/api/catan/initial-game-data";

	public InitialResponseDTO getInitialGameData() {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			return this.restService.sendGetRequest(client, this.getUri());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public ResponseDTO getGameInfo(URI uri, RequestDTO requestDTO) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			while (true) {
				ResponseDTO responseDTO = this.restService.sendPostRequest(client, this.getUri(), requestDTO);
				if (responseDTO.getResponseType() == ResponseType.WAIT) {
					log.info("Continue Polling for game info...");
					Thread.sleep(4000);
				}
				else {
					log.info("Finish Polling!");
					return responseDTO;
				}
			}
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private URI getUri() {
		try {
			return new URI(this.url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}
