package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.RefreshTokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
public class FitBitAccessTokenService {
    private static final Logger log = LoggerFactory.getLogger(FitBitAccessTokenService.class);

    private RefreshTokenDTO refreshTokenDTO;
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpEntity<String> entity;
    private final String initialRefreshToken;

    private static final String REFRESH_URI = "https://api.fitbit.com/oauth2/token?grant_type=refresh_token&refresh_token=";

    @Autowired
    public FitBitAccessTokenService(
            @Value("${fitbit.application.client.id}") String clientId,
            @Value("${fitbit.application.client.secret}") String clientSecret,
            @Value("${fitbit.access.refresh}") String initialRefreshToken
    ) {
        if(System.getenv("ACCESS_TOKEN_OVERRIDE") == null) {
            Assert.notNull(initialRefreshToken, "Error! Property fitbit.access.refresh not set");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<>(null, headers);

        this.initialRefreshToken = initialRefreshToken;
    }

    public void updateRefreshToken(String code) {
        log.info("Updating Refresh Token to " + code);
        refreshTokenDTO.setRefreshToken(code);
    }

    String getAccessToken() {
        return refreshTokenDTO.getAccessToken();
    }

    String getUserId() {
        return refreshTokenDTO.getUserId();
    }

    public void performTokenRefresh() {
        log.info("Refreshing access token");

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

        try {
            final ResponseEntity<RefreshTokenDTO> exchange = restTemplate.exchange(REFRESH_URI + refreshToken, HttpMethod.POST, entity, RefreshTokenDTO.class);
            return exchange.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            //TODO - handle manual refresh of token due to loss of token
            return new RefreshTokenDTO(); //Placeholder
        }
    }
}
