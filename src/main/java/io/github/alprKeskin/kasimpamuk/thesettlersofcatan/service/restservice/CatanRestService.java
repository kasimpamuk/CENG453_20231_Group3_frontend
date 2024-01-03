package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.restservice;

import com.google.gson.Gson;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.request.RequestDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.InitialResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.ResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.enumeration.ResponseType;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

// TODO: Not applying SOLID principles
@Service
public class CatanRestService {

	private final Gson gson = new Gson();

	public InitialResponseDTO sendGetRequestByPolling(URI uri) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			while (true) {
				InitialResponseDTO response = sendGetRequest(client, uri);

				if (response.getResponseType() == ResponseType.WAIT) {
					System.out.println("Continue polling...");
					// Poll every 5 seconds
					Thread.sleep(5000);
				}
				else {
					System.out.println("Finish Polling!");
					return response;
				}
			}
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public ResponseDTO sendPostRequestByPolling(URI uri, RequestDTO entity) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			while (true) {
				ResponseDTO response = sendPostRequest(client, uri, entity);
				if (response.getResponseType() == ResponseType.WAIT) {
					System.out.println("Continue polling...");
					// Poll every 5 seconds
					Thread.sleep(5000);
				}
				else {
					System.out.println("Finish Polling!");
					return response;
				}
			}
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private InitialResponseDTO sendGetRequest(CloseableHttpClient client, URI uri) {
		HttpGet request = new HttpGet(uri);
		String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbHBlckBvdXRsb29rLmNvbSIsImV4cCI6MTkyMDI5NTkyMH0.0YKrSBvGyyaWFg1DU_wXS6f3ChNlJrCPM52DOI2fCdM";
		request.addHeader("Authorization", "Bearer " + jwtToken);

		String response = null;
		try {
			response = client.execute(request, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		if (response == null) throw new RuntimeException("GET response is null");

		System.out.println("JSON Response from server: " + response);

		InitialResponseDTO initialResponseDTO = gson.fromJson(response, InitialResponseDTO.class);
		System.out.println("Initial Response DTO received: " + initialResponseDTO.toString());

		return initialResponseDTO;
	}

	private ResponseDTO sendPostRequest(CloseableHttpClient client, URI uri, RequestDTO entity) {
		HttpPost request = new HttpPost(uri);
		String jsonEntity = gson.toJson(entity);
		System.out.println("JSON entity to be posted: " + jsonEntity);

		try {
			request.setEntity(new StringEntity(jsonEntity));
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");
			String jsonResponse = client.execute(request, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));
			System.out.println("JSON response from server: " + jsonResponse);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		ResponseDTO responseDTO = gson.fromJson(jsonEntity, ResponseDTO.class);
		System.out.println("Response DTO: " + responseDTO.toString());

		return responseDTO;
	}

}
