package com.ukma.springproject.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukma.springproject.services.EuroConversionRateService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EuroConversionRateServiceImpl implements EuroConversionRateService {
    private final RestTemplate restTemplate;

    public EuroConversionRateServiceImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public double getEuroConversionRate() throws JsonProcessingException {
        String json = restTemplate
                .getForObject("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=EUR&json",
                        String.class);
        List result =
                new ObjectMapper().readValue(json, List.class);
        return (Double) ((Map) result.get(0)).get("rate");
    }
}
