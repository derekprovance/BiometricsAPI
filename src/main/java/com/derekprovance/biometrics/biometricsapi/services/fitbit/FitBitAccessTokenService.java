package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess.ConnectedApiAccess;
import com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess.AccessType;
import com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess.ConnectedApi;
import com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess.ConnectedApiAccessRepository;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.RefreshTokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.CredentialNotFoundException;

//TODO - handle AUTH_CODE type and scenario

@Service
public class FitBitAccessTokenService {
    private static final Logger log = LoggerFactory.getLogger(FitBitAccessTokenService.class);

    private RefreshTokenDTO refreshTokenDTO;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ConnectedApiAccessRepository connectedApiAccessRepository;
    private HttpEntity<String> entity;
    private final String redirectUri;

    private static final String FITBIT_API = "https://api.fitbit.com/oauth2/token";
    private static final String FITBIT_REFRESH_URI = FITBIT_API + "?grant_type=refresh_token&refresh_token=%s";
    private static final String FITBIT_AUTH_CODE_URI = FITBIT_API + "?grant_type=authorization_code&redirect_uri=%s&code=%s";

    @Autowired
    public FitBitAccessTokenService(
            @Value("${fitbit.application.client.id}") String clientId,
            @Value("${fitbit.application.client.secret}") String clientSecret,
            @Value("${fitbit.access.redirect_uri}") String redirectUri,
            ConnectedApiAccessRepository connectedApiAccessRepository
    ) {
        initializeHttpEntity(clientId, clientSecret);

        this.redirectUri = redirectUri;
        this.connectedApiAccessRepository = connectedApiAccessRepository;
    }

    public void updateRefreshToken(String code) {
        log.info("Resetting Refresh Tokens");
        refreshTokenDTO = callApiForAuthentication(getUrlForAuthentication(FitBitAuthType.AUTH_CODE, code));
        saveOrCreateAccessEntity();
    }

    String getAccessToken() throws CredentialNotFoundException {
        if(refreshTokenDTO != null) {
            return refreshTokenDTO.getAccessToken();
        } else {
            refreshAccessToken();
            return refreshTokenDTO.getAccessToken();
        }
    }

    String getUserId() throws CredentialNotFoundException {
        if(refreshTokenDTO != null) {
            return refreshTokenDTO.getUserId();
        } else {
            refreshAccessToken();
            return refreshTokenDTO.getUserId();
        }
    }

    public void refreshAccessToken() throws CredentialNotFoundException {
        log.info("Refreshing access token");

        ConnectedApiAccess apiCredentials = connectedApiAccessRepository.findByApiAndType(ConnectedApi.FITBIT, AccessType.REFRESH_TOKEN);

        if(apiCredentials != null) {
            refreshTokenDTO = callApiForAuthentication(getUrlForAuthentication(FitBitAuthType.REFRESH_TOKEN, apiCredentials.getToken()));
        } else {
            throw new CredentialNotFoundException("FitBit Credentials not set for user");
        }

        if (refreshTokenDTO != null && refreshTokenDTO.getAccessToken() != null) {
            apiCredentials.setToken(refreshTokenDTO.getRefreshToken());
            connectedApiAccessRepository.save(apiCredentials);
        }
    }

    private RefreshTokenDTO callApiForAuthentication(String callUrl) {
        try {
            final ResponseEntity<RefreshTokenDTO> exchange = restTemplate.exchange(callUrl, HttpMethod.POST, entity, RefreshTokenDTO.class);
            return exchange.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveOrCreateAccessEntity() {
        ConnectedApiAccess accessEntity = connectedApiAccessRepository.findByApiAndType(ConnectedApi.FITBIT, AccessType.REFRESH_TOKEN);
        if(accessEntity != null) {
            accessEntity.setToken(refreshTokenDTO.getRefreshToken());
            connectedApiAccessRepository.save(accessEntity);
        } else {
            accessEntity = new ConnectedApiAccess();
            accessEntity.setApi(ConnectedApi.FITBIT);
            accessEntity.setType(AccessType.REFRESH_TOKEN);
            accessEntity.setToken(refreshTokenDTO.getRefreshToken());
        }
    }

    private String getUrlForAuthentication(FitBitAuthType authType, String token) {
        String callUrl = null;

        switch(authType) {
            case AUTH_CODE:
                callUrl = String.format(FITBIT_AUTH_CODE_URI, redirectUri, token);
                break;
            case REFRESH_TOKEN:
                callUrl = String.format(FITBIT_REFRESH_URI, token);
                break;
        }

        return callUrl;
    }

    private void initializeHttpEntity(String clientId, String clientSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<>(null, headers);
    }
}
