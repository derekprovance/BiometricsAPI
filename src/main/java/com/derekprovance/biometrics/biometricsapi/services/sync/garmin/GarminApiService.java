package com.derekprovance.biometrics.biometricsapi.services.sync.garmin;

import com.derekprovance.biometrics.biometricsapi.services.AbstractService;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.DailyHeartRate;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.DailyMovementData;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.DailyUserSummary;
import com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO.dailySleepData.DailySleepData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.CredentialNotFoundException;
import java.time.LocalDate;

@Service
public class GarminApiService extends AbstractService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final GarminConnectAuthService garminConnectAuthService;
    private HttpEntity<String> entity;

    private static final String GARMIN_API_URL = "https://connect.garmin.com/modern/proxy";
    private static final String DAILY_SLEEP_ENDPOINT = GARMIN_API_URL + "/wellness-service/wellness/dailySleepData/%s?date=%s&nonSleepBufferMinutes=60";
    private static final String DAILY_HR_ENDPOINT = GARMIN_API_URL + "/wellness-service/wellness/dailyHeartRate/%s?date=%s&_=1532359756927";
    private static final String DAILY_MOVEMENT_ENDPOINT = GARMIN_API_URL + "/wellness-service/wellness/dailyMovement/%s?calendarDate=%s&_=1532359756928";
    private static final String USER_SUMMARY_ENDPOINT = GARMIN_API_URL + "/usersummary-service/usersummary/daily/%s?calendarDate=%s&_=1532359756925";

    @Autowired
    public GarminApiService(GarminConnectAuthService garminConnectAuthService) throws CredentialNotFoundException {
        this.garminConnectAuthService = garminConnectAuthService;

        initializeHttpEntity();
    }

    DailySleepData getDailySleepData(LocalDate date) throws CredentialNotFoundException {
        return (DailySleepData) performApiCall(formatEndpoint(DAILY_SLEEP_ENDPOINT, date), DailySleepData.class);
    }

    DailyHeartRate getDailyHrData(LocalDate date) throws CredentialNotFoundException {
        return (DailyHeartRate) performApiCall(formatEndpoint(DAILY_HR_ENDPOINT, date), DailyHeartRate.class);
    }

    DailyMovementData getDailyMovement(LocalDate date) throws CredentialNotFoundException {
        return (DailyMovementData) performApiCall(formatEndpoint(DAILY_MOVEMENT_ENDPOINT, date), DailyMovementData.class);
    }

    DailyUserSummary getUserSummary(LocalDate date) throws CredentialNotFoundException {
        return (DailyUserSummary) performApiCall(formatEndpoint(USER_SUMMARY_ENDPOINT, date), DailyUserSummary.class);
    }

    private String formatEndpoint(String endpoint, LocalDate date) throws CredentialNotFoundException {
        return String.format(endpoint, garminConnectAuthService.getUserId(), convertDateToString(date));
    }

    private void initializeHttpEntity() throws CredentialNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", garminConnectAuthService.getSessionCookie());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<>(null, headers);
    }

    private Object performApiCall(String uri, Class dto) {
        try {
            final ResponseEntity exchange = restTemplate.exchange(uri , HttpMethod.GET, entity, dto);
            return exchange.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
