package com.derekprovance.biometrics.biometricsapi.services.garmin;

import com.derekprovance.biometrics.biometricsapi.services.AbstractService;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyHeartRate;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyMovementData;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.DailyUserSummary;
import com.derekprovance.biometrics.biometricsapi.services.garmin.DTO.dailySleepData.DailySleepData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class GarminApiService extends AbstractService {

    private final RestTemplate restTemplate = new RestTemplate();
    private GarminConnectAuthService garminConnectAuthService;
    private HttpEntity<String> entity;

    private static final String GARMIN_API_URL = "https://connect.garmin.com/modern/proxy";
    private static final String DAILY_SLEEP_ENDPOINT = GARMIN_API_URL + "/wellness-service/wellness/dailySleepData/%s?date=%s&nonSleepBufferMinutes=60";
    private static final String DAILY_HR_ENDPOINT = GARMIN_API_URL + "/wellness-service/wellness/dailyHeartRate/%s?date=%s&_=1532359756927";
    private static final String DAILY_MOVEMENT_ENDPOINT = GARMIN_API_URL + "/wellness-service/wellness/dailyMovement/%s?calendarDate=%s&_=1532359756928";
    private static final String USER_SUMMARY_ENDPOINT = GARMIN_API_URL + "/usersummary-service/usersummary/daily/%s?calendarDate=%s&_=1532359756925";

    @Autowired
    public GarminApiService(GarminConnectAuthService garminConnectAuthService) {
        this.garminConnectAuthService = garminConnectAuthService;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", garminConnectAuthService.getSessionCookie());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<>(null, headers);
    }

    DailySleepData getDailySleepData(Date date) {
        return (DailySleepData) performApiCall(formatEndpoint(DAILY_SLEEP_ENDPOINT, date), DailySleepData.class);
    }

    DailyHeartRate getDailyHrData(Date date) {
        return (DailyHeartRate) performApiCall(formatEndpoint(DAILY_HR_ENDPOINT, date), DailyHeartRate.class);
    }

    DailyMovementData getDailyMovement(Date date) {
        return (DailyMovementData) performApiCall(formatEndpoint(DAILY_MOVEMENT_ENDPOINT, date), DailyMovementData.class);
    }

    DailyUserSummary getUserSummary(Date date) {
        return (DailyUserSummary) performApiCall(formatEndpoint(USER_SUMMARY_ENDPOINT, date), DailyUserSummary.class);
    }

    private String formatEndpoint(String endpoint, Date date) {
        return String.format(endpoint, garminConnectAuthService.getUserId(), convertDateToString(date));
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
