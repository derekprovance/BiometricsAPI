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
    private final String redirectUri;

    private static final String FITBIT_API = "https://api.fitbit.com/oauth2/token";
    private static final String FITBIT_REFRESH_URI = FITBIT_API + "?grant_type=refresh_token&refresh_token=%s";
    private static final String FITBIT_AUTH_CODE_URI = FITBIT_API + "?grant_type=authorization_code&redirect_uri=%s&code=%s";

    @Autowired
    public FitBitAccessTokenService(
            @Value("${fitbit.application.client.id}") String clientId,
            @Value("${fitbit.application.client.secret}") String clientSecret,
            @Value("${fitbit.access.refresh}") String initialRefreshToken,
            @Value("${fitbit.access.redirect_uri}") String redirectUri
    ) {
        if(System.getenv("ACCESS_TOKEN_OVERRIDE") == null) {
            Assert.notNull(initialRefreshToken, "Error! Property fitbit.access.refresh not set");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<>(null, headers);

        this.initialRefreshToken = initialRefreshToken;
        this.redirectUri = redirectUri;
    }

    public void updateRefreshToken(String code) {
        log.info("Resetting Refresh Tokens");
        refreshTokenDTO = performTokenRefresh(code, FitBitAuthType.AUTH_CODE);
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
        refreshTokenDTO = performTokenRefresh(refreshToken, FitBitAuthType.REFRESH_TOKEN);
    }

    private RefreshTokenDTO performTokenRefresh(String token, FitBitAuthType fitBitAuthType) {
        if(System.getenv("ACCESS_TOKEN_OVERRIDE") != null) {
            RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();
            refreshTokenDTO.setAccessToken(System.getenv("ACCESS_TOKEN_OVERRIDE"));

            return refreshTokenDTO;
        }

        String callUrl = null;
        switch(fitBitAuthType) {
            case AUTH_CODE:
                callUrl = String.format(FITBIT_AUTH_CODE_URI, redirectUri, token);
                break;
            case REFRESH_TOKEN:
                callUrl = String.format(FITBIT_REFRESH_URI, token);
                break;
        }

        try {
            final ResponseEntity<RefreshTokenDTO> exchange = restTemplate.exchange(callUrl, HttpMethod.POST, entity, RefreshTokenDTO.class);
            return exchange.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
