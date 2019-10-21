package com.derekprovance.biometrics.biometricsapi.services.garmin;

import com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess.AccessType;
import com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess.ConnectedApi;
import com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess.ConnectedApiAccess;
import com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess.ConnectedApiAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;

@Service
public class GarminConnectAuthService {
    private String session;
    private String userId;

    private ConnectedApiAccessRepository connectedApiAccessRepository;

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
            throw new CredentialNotFoundException("Garmin API Token not set for user");
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
            throw new CredentialNotFoundException("Garmin API Token not set for user");
        }

        return this.session;
    }
}
