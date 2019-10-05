package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.FitbitFoodEndpointDTO;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.water.WaterLogDTO;
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
public class FitbitAPIService {

    private static final Logger log = LoggerFactory.getLogger(FitbitAPIService.class);

    private FitBitAccessTokenService fitbitAccessTokenService;

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public FitbitAPIService(FitBitAccessTokenService fitbitAccessTokenService) {
        this.fitbitAccessTokenService = fitbitAccessTokenService;
    }

    FitbitFoodEndpointDTO performFoodApiCall(Date date) {
        String dateStr = dateFormat.format(date);
        String uri = String.format("https://api.fitbit.com/1/user/%s/foods/log/date/%s.json", fitbitAccessTokenService.getUserId(), dateStr);
        log.info("Calling FitBit API for food data entries on " + dateStr);

        return (FitbitFoodEndpointDTO) performApiCall(uri, FitbitFoodEndpointDTO.class);
    }

    WaterLogDTO performWaterLog(Date date) {
        String dateStr = dateFormat.format(date);
        String uri = String.format("https://api.fitbit.com/1/user/%s/foods/log/water/date/%s.json", fitbitAccessTokenService.getUserId(), dateStr);
        log.info("Calling FitBit API for food data entries on " + dateStr);

        return (WaterLogDTO) performApiCall(uri, WaterLogDTO.class);
    }

    private Object performApiCall(String uri, Class dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(fitbitAccessTokenService.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            final ResponseEntity<FitbitFoodEndpointDTO> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, dto);
            return exchange.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
