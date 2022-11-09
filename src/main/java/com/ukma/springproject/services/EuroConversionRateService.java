package com.ukma.springproject.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface EuroConversionRateService {
    double getEuroConversionRate() throws JsonProcessingException;
}
