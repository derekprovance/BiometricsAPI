package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.RefreshTokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

//TODO - remove hardcoded values

@Service
public class FitbitAccessTokenService {
    private RefreshTokenDTO refreshTokenDTO;
    private RestTemplate restTemplate = new RestTemplate();
    private HttpEntity<String> entity;

    private static final Logger log = LoggerFactory.getLogger(FitbitAccessTokenService.class);

    @Value("${fitbit.application.clientId}")
    private String clientId;

    @Value("${fitbit.application.clientSecret}")
    private String clientSecret;

    public FitbitAccessTokenService() {
        String initialRefreshToken = System.getenv("INITIAL_REFRESH_TOKEN");
        Assert.notNull(initialRefreshToken, "Error! Environment Variable INITIAL_REFRESH_TOKEN not set");

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<>(null, headers);

        refreshTokenDTO = performTokenRefresh(initialRefreshToken);
    }

    String getAccessToken() {
        return refreshTokenDTO.getAccessToken();
    }

    String getUserId() {
        return refreshTokenDTO.getUserId();
    }

    @Scheduled(fixedRate = (300 * 60000))
    private void performTokenRefresh() {
        log.info("Performing refresh of the access token");
        refreshTokenDTO = performTokenRefresh(refreshTokenDTO.getRefreshToken());
    }

    private RefreshTokenDTO performTokenRefresh(String refreshToken) {
        String refreshUri = "https://api.fitbit.com/oauth2/token?grant_type=refresh_token&refresh_token=" + refreshToken;
        final ResponseEntity<RefreshTokenDTO> exchange = restTemplate.exchange(refreshUri, HttpMethod.POST, entity, RefreshTokenDTO.class);
        return exchange.getBody();
    }
}
