package com.derekprovance.biometrics.biometricsapi.services.garmin;

import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyHeartRate;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyMovementData;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyUserSummary;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData.DailySleepData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.util.Date;

@Service
public class GarminApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private GarminConnectAuthService garminConnectAuthService;
    private HttpEntity<String> entity;

    private static final String GARMIN_API_URL = "https://connect.garmin.com/modern/proxy";

    @Autowired
    public GarminApiService(GarminConnectAuthService garminConnectAuthService) {
        this.garminConnectAuthService = garminConnectAuthService;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", garminConnectAuthService.getSessionCookie());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<>(null, headers);
    }

    DailySleepData getDailySleepData(Date date) {
        String endpoint = GARMIN_API_URL + String.format("/wellness-service/wellness/dailySleepData/%s?date=%s&nonSleepBufferMinutes=60", garminConnectAuthService.getUserId(), convertDateToString(date));
        return (DailySleepData) performApiCall(endpoint, DailySleepData.class);
    }

    DailyHeartRate getDailyHrData(Date date) {
        String endpoint = GARMIN_API_URL + String.format("/wellness-service/wellness/dailyHeartRate/%s?date=%s&_=1532359756927", garminConnectAuthService.getUserId(), convertDateToString(date));
        return (DailyHeartRate) performApiCall(endpoint, DailyHeartRate.class);
    }

    DailyMovementData getDailyMovement(Date date) {
        String endpoint = GARMIN_API_URL + String.format("/wellness-service/wellness/dailyMovement/%s?calendarDate=%s&_=1532359756928", garminConnectAuthService.getUserId(), convertDateToString(date));
        return (DailyMovementData) performApiCall(endpoint, DailyMovementData.class);
    }

    DailyUserSummary getUserSummary(Date date) {
        String endpoint = GARMIN_API_URL + String.format("/usersummary-service/usersummary/daily/%s?calendarDate=%s&_=1532359756925", garminConnectAuthService.getUserId(), convertDateToString(date));
        return (DailyUserSummary) performApiCall(endpoint, DailyUserSummary.class);
    }

    private String convertDateToString(Date date) {
        return date.toInstant()
                .atZone(ZoneId.of("UTC"))
                .toLocalDate().toString();
    }

    private Object performApiCall(String uri, Class dto) {
        try {
            final ResponseEntity exchange = restTemplate.exchange(uri , HttpMethod.GET, entity, dto);
            return exchange.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }}
