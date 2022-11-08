package com.ukma.springproject.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ukma.springproject.services.NameNationalityPredictorService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NameNationalityPredictorServiceImpl implements NameNationalityPredictorService {

    private final RestTemplate restTemplate;

    public NameNationalityPredictorServiceImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public String getNationality(String query) throws JsonProcessingException {
        String json = restTemplate
                .getForObject("https://api.nationalize.io?name=" + query, String.class);
        Map<String, Object> result =
                new ObjectMapper().readValue(json, HashMap.class);
        var listOfResults = (List) result.get("country");
        return listOfResults.get(0).toString();
    }
}
