package com.derekprovance.biometrics.biometricsapi.services.fitbit;

import com.derekprovance.biometrics.biometricsapi.services.AbstractService;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.meals.FitbitFoodEndpointDTO;
import com.derekprovance.biometrics.biometricsapi.services.fitbit.DTO.water.WaterLogDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.CredentialException;
import java.time.LocalDate;

@Service
public class FitBitAPIService extends AbstractService {
    private static final Logger log = LoggerFactory.getLogger(FitBitAPIService.class);
    private final FitBitAccessTokenService fitbitAccessTokenService;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String FOOD_LOG_API = "https://api.fitbit.com/1/user/%s/foods/log/date/%s.json";
    private static final String WATER_LOG_API = "https://api.fitbit.com/1/user/%s/foods/log/water/date/%s.json";

    @Autowired
    public FitBitAPIService(FitBitAccessTokenService fitbitAccessTokenService) {
        this.fitbitAccessTokenService = fitbitAccessTokenService;
    }

    FitbitFoodEndpointDTO performFoodApiCall(LocalDate date) throws CredentialException {
        String dateStr = convertDateToString(date);
        log.info("Calling FitBit API for food data entries on " + dateStr);

        return (FitbitFoodEndpointDTO) performApiCall(formatEndpoint(FOOD_LOG_API, dateStr), FitbitFoodEndpointDTO.class);
    }

    WaterLogDTO performWaterLog(LocalDate date) throws CredentialException {
        String dateStr = convertDateToString(date);
        log.info("Calling FitBit API for food data entries on " + dateStr);

        return (WaterLogDTO) performApiCall(formatEndpoint(WATER_LOG_API, dateStr), WaterLogDTO.class);
    }

    private String formatEndpoint(String endpoint, String dateStr) throws CredentialException {
        return String.format(endpoint, fitbitAccessTokenService.getUserId(), dateStr);
    }

    private Object performApiCall(String uri, Class dto) throws CredentialException {
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
