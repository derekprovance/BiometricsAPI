package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.FitbitFoodEndpointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GetFoodLog {

    @Autowired
    private AccessTokenService accessTokenService;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private RestTemplate restTemplate = new RestTemplate();
    private HttpEntity<String> entity;

    @Scheduled(fixedRate = 5000)
    public FitbitFoodEndpointDTO getEntriesForDate() {
        return getEntriesForDate(new Date());
    }

    public FitbitFoodEndpointDTO getEntriesForDate(Date date) {
        return performCall(date);
    }

    private FitbitFoodEndpointDTO performCall(Date date) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessTokenService.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<>(null, headers);

        String uri = String.format("https://api.fitbit.com/1/user/%s/foods/log/date/%s.json", accessTokenService.getUserId(), dateFormat.format(date));
        try {
            final ResponseEntity<FitbitFoodEndpointDTO> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, FitbitFoodEndpointDTO.class);
            return exchange.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
