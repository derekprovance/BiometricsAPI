package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.RefreshTokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
public class FitbitAccessTokenService {
    private static final Logger log = LoggerFactory.getLogger(FitbitAccessTokenService.class);

    private RefreshTokenDTO refreshTokenDTO;
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpEntity<String> entity;
    private final String initialRefreshToken;

    @Autowired
    public FitbitAccessTokenService(
            @Value("${fitbit.application.client.id}") String clientId,
            @Value("${fitbit.application.client.secret}") String clientSecret
    ) {
        String initialRefreshToken = "test";

        if(System.getenv("ACCESS_TOKEN_OVERRIDE") == null) {
            Assert.notNull(initialRefreshToken, "Error! Environment Variable INITIAL_REFRESH_TOKEN not set");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<>(null, headers);

        this.initialRefreshToken = initialRefreshToken;
    }

    String getAccessToken() {
        return refreshTokenDTO.getAccessToken();
    }

    String getUserId() {
        return refreshTokenDTO.getUserId();
    }

    public void performTokenRefresh() {
        log.info("Performing refresh of the access token");

        String refreshToken = refreshTokenDTO != null && refreshTokenDTO.getRefreshToken() != null ? refreshTokenDTO.getRefreshToken() : initialRefreshToken;
        refreshTokenDTO = performTokenRefresh(refreshToken);
    }

    private RefreshTokenDTO performTokenRefresh(String refreshToken) {
        if(System.getenv("ACCESS_TOKEN_OVERRIDE") != null) {
            RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();
            refreshTokenDTO.setUserId("7SNSH6"); //TODO: boo
            refreshTokenDTO.setAccessToken(System.getenv("ACCESS_TOKEN_OVERRIDE"));

            return refreshTokenDTO;
        }

        String refreshUri = "https://api.fitbit.com/oauth2/token?grant_type=refresh_token&refresh_token=" + refreshToken;
        try {
            final ResponseEntity<RefreshTokenDTO> exchange = restTemplate.exchange(refreshUri, HttpMethod.POST, entity, RefreshTokenDTO.class);
            return exchange.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            //TODO - handle manual refresh of token due to loss of token
            return new RefreshTokenDTO(); //Placeholder
        }
    }
}
