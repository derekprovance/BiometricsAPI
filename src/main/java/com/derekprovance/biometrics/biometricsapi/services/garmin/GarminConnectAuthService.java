package com.derekprovance.biometrics.biometricsapi.services.garmin;

import com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess.AccessType;
import com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess.ConnectedApi;
import com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess.ConnectedApiAccess;
import com.derekprovance.biometrics.biometricsapi.api.connectedApiAccess.ConnectedApiAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;

@Service
public class GarminConnectAuthService {
    private String session;
    private String userId;

    private ConnectedApiAccessRepository connectedApiAccessRepository;

    @Autowired
    public GarminConnectAuthService(
            @Value("${garmin.user.id}") String userId,
            ConnectedApiAccessRepository connectedApiAccessRepository
    ) throws CredentialNotFoundException {
        this.userId = userId;
        this.connectedApiAccessRepository = connectedApiAccessRepository;

        getSessionToken();
    }

    String getUserId() {
        return this.userId;
    }

    String getSessionCookie() {
        return String.format("SESSIONID=%s", this.session);
    }

    private void getSessionToken() throws CredentialNotFoundException {
        final ConnectedApiAccess accessEntity = connectedApiAccessRepository.findByApiAndType(ConnectedApi.GARMIN, AccessType.ACCESS_TOKEN);

        if(accessEntity != null) {
            session = accessEntity.getToken();
        } else {
            throw new CredentialNotFoundException("Garmin API Token not set for user");
        }
    }
}
