package com.derekprovance.biometrics.biometricsapi.services.garmin;

import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyHeartRate;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyMovementData;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyUserSummary;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData.DailySleepData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GarminApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private GarminConnectAuthService garminConnectAuthService;

    private static final String GARMIN_API_URL = "https://connect.garmin.com/modern/proxy";

    @Autowired
    public GarminApiService(GarminConnectAuthService garminConnectAuthService) {
        this.garminConnectAuthService = garminConnectAuthService;
    }

    public DailySleepData getDailySleepData() {
        String endpoint = GARMIN_API_URL + String.format("/wellness-service/wellness/dailySleepData/%s?date=2019-04-01&nonSleepBufferMinutes=60", garminConnectAuthService.getUserId());
        final DailySleepData dailySleepData = (DailySleepData) performApiCall(endpoint, DailySleepData.class);
        return dailySleepData;
    }

    public DailyHeartRate getDailyHrData() {
        String endpoint = GARMIN_API_URL + String.format("/wellness-service/wellness/dailyHeartRate/%s?date=2019-04-01&_=1532359756927", garminConnectAuthService.getUserId());
        final DailyHeartRate dailyHeartRate = (DailyHeartRate) performApiCall(endpoint, DailyHeartRate.class);
        return dailyHeartRate;
    }

    public DailyMovementData getDailyMovement() {
        String endpoint = GARMIN_API_URL + String.format("/wellness-service/wellness/dailyMovement/%s?calendarDate=2019-04-01&_=1532359756928", garminConnectAuthService.getUserId());
        final DailyMovementData dailyMovementData = (DailyMovementData) performApiCall(endpoint, DailyMovementData.class);
        return dailyMovementData;
    }

    public DailyUserSummary getUserSummary() {
        String endpoint = GARMIN_API_URL + String.format("/usersummary-service/usersummary/daily/%s?calendarDate=2019-04-01&_=1532359756925", garminConnectAuthService.getUserId());
        final DailyUserSummary dailyUserSummary = (DailyUserSummary) performApiCall(endpoint, DailyUserSummary.class);
        return dailyUserSummary;
    }

    private Object performApiCall(String uri, Class dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", garminConnectAuthService.getSessionCookie());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            final ResponseEntity<String> exchange = restTemplate.exchange(uri , HttpMethod.GET, entity, dto);
            return exchange.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }}
