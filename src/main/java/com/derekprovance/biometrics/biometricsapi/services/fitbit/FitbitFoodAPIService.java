package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.FitbitFoodEndpointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FitbitFoodAPIService {

    private static final Logger log = LoggerFactory.getLogger(FitbitFoodAPIService.class);

    @Autowired
    private FitbitAccessTokenService fitbitAccessTokenService;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private RestTemplate restTemplate = new RestTemplate();
    private HttpEntity<String> entity;

    public FitbitFoodEndpointDTO getEntriesForDate(Date date) {
        return performCall(date);
    }

    private FitbitFoodEndpointDTO performCall(Date date) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(fitbitAccessTokenService.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<>(null, headers);

        String dateStr = dateFormat.format(date);
        String uri = String.format("https://api.fitbit.com/1/user/%s/foods/log/date/%s.json", fitbitAccessTokenService.getUserId(), dateStr);
        try {
            log.info("Calling fitbit API for food data entries on " + dateStr);
            final ResponseEntity<FitbitFoodEndpointDTO> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, FitbitFoodEndpointDTO.class);

            return exchange.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
