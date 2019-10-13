package com.derekprovance.biometrics.biometricsapi.services.garmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GarminConnectAuthService {
    private String session;
    private String userId;


    @Autowired
    public GarminConnectAuthService(
            @Value("${garmin.session") String session,
            @Value("${garmin.user.id}") String userId
    ) {
        this.session = session;
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getSessionCookie() {
        return String.format("SESSIONID=%s", this.session);
    }
}
