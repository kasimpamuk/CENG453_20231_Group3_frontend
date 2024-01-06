package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.service.restservice;

import com.google.gson.Gson;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.request.RequestDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.InitialResponseDTO;
import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.gamedata.dto.response.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

@Slf4j
@Service
public class RestService {

    private final Gson gson = new Gson();
    private final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbHBlckBvdXRsb29rLmNvbSIsImV4cCI6MTkyMDI5NTkyMH0.0YKrSBvGyyaWFg1DU_wXS6f3ChNlJrCPM52DOI2fCdM";

    public InitialResponseDTO sendGetRequest(CloseableHttpClient client, URI uri) {
        HttpGet request = new HttpGet(uri);
        request.addHeader("Authorization", "Bearer " + JWT_TOKEN);

        String response = executeGetRequest(client, request);

        return gson.fromJson(response, InitialResponseDTO.class);
    }

    public ResponseDTO sendPostRequest(CloseableHttpClient client, URI uri, RequestDTO entity) {
        HttpPost request = new HttpPost(uri);
        request.addHeader("Authorization", "Bearer" + JWT_TOKEN);

        String jsonResponse = executePostRequest(client, request, gson.toJson(entity));

        return gson.fromJson(jsonResponse, ResponseDTO.class);
    }

    private String executeGetRequest(CloseableHttpClient client, HttpGet request) {
        String response = null;
        try {
            response = client.execute(request, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (response == null) throw new RuntimeException("GET response is null!");

        return response;
    }

    private String executePostRequest(CloseableHttpClient client, HttpPost request, String jsonRequestBody) {
        String response = null;
        try {
            request.setEntity(new StringEntity(jsonRequestBody));
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            response = client.execute(request, httpResponse -> EntityUtils.toString(httpResponse.getEntity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (response == null) throw new RuntimeException("Post response is null!");
        return response;
    }

}
