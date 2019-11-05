package com.derekprovance.biometrics.biometricsapi.services.garmin;

import com.derekprovance.biometrics.biometricsapi.api.utility.connectedApiAccess.AccessType;
import com.derekprovance.biometrics.biometricsapi.api.utility.connectedApiAccess.ConnectedApi;
import com.derekprovance.biometrics.biometricsapi.api.utility.connectedApiAccess.ConnectedApiAccess;
import com.derekprovance.biometrics.biometricsapi.api.utility.connectedApiAccess.ConnectedApiAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;

@Service
public class GarminConnectAuthService {
    private String session;
    private String userId;

    private final ConnectedApiAccessRepository connectedApiAccessRepository;

    private static final String CREDENTIAL_NOT_FOUND_ERROR = "Garmin API Token not set for user";

    @Autowired
    public GarminConnectAuthService(ConnectedApiAccessRepository connectedApiAccessRepository) throws CredentialNotFoundException {
        this.connectedApiAccessRepository = connectedApiAccessRepository;

        getSessionToken();
    }

    String getSessionCookie() throws CredentialNotFoundException {
        String session = getSessionToken();
        return String.format("SESSIONID=%s", session);
    }

    String getUserId() throws CredentialNotFoundException {
        if(this.userId != null) {
            return this.userId;
        }

        final ConnectedApiAccess accessEntity = connectedApiAccessRepository.findByApiAndType(ConnectedApi.GARMIN, AccessType.USER_ID);

        if(accessEntity != null) {
            this.userId = accessEntity.getToken();
        } else {
            throw new CredentialNotFoundException(CREDENTIAL_NOT_FOUND_ERROR);
        }

        return this.userId;
    }

    private String getSessionToken() throws CredentialNotFoundException {
        if(this.session != null) {
            return this.session;
        }

        final ConnectedApiAccess accessEntity = connectedApiAccessRepository.findByApiAndType(ConnectedApi.GARMIN, AccessType.ACCESS_TOKEN);

        if(accessEntity != null) {
            session = accessEntity.getToken();
        } else {
            throw new CredentialNotFoundException(CREDENTIAL_NOT_FOUND_ERROR);
        }

        return this.session;
    }
}
